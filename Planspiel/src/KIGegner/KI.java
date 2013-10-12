package KIGegner;
import java.util.ArrayList;

import Client.Connection.Client;
import Message.GameDataMessageToClient;
import Message.LoginConfirmationMessage;
import Message.LoginMessage;

public class KI extends Thread {
	private Client c;
	// Die KI versucht diese Qualit�t zu erreichen, oder zu �berbieten
	private int qualityTry;
	private String playerName;
	private ArrayList<AmountObject> bankAmounts = new ArrayList<AmountObject>();
	private int id = 1;

	public static void main(String[] args) {
		// in welchen Sektor soll die KI?
		// Je niedriger die Zahl, desto mehr ist es im billigen Secotr:
		new KI(10);
		
		
		

	}

	private KI(int sector) {
		System.out.println("KI wurde gestartet!");
		this.qualityTry = sector;
		this.playerName = "KI-Solar" + id;
		id++;
		
		if (Login()) {
			// Login erfolgreich
			System.out.println("Login erfolgreich.");
			//Starte in die Prozessierung
			this.start();
		}
		

	}

	/**
	 * k�mmert sich um den generellen Verbindungsaufbau und Login prozess
	 * 
	 * @return gibt den Loginstatus zur�ck
	 */

	private boolean Login() {
		// initialisiere den Client
		c = new Client();
		// erstelle die TCP-Verbindung
		c.connect("127.0.0.1", Constant.Constant.Server.TCP_PORT);
		// Sende die Daten an den Server
		c.writeMessage(new LoginMessage(playerName, "KI-Programmed",
				"usa"));
		// Empfange die Daten
		LoginConfirmationMessage msg = (LoginConfirmationMessage) c
				.readMessage();
		// Gib zur�ck ob eingeloggt wurde.
		return msg.getSuccess();

	}

	@Override
	public void run() {
		// Bereite Requests und sonstiges f�r die Erste Runde vor!
		doFirstRound();
		// zweite Runde

		doSecondRound((GameDataMessageToClient) c.readMessage());
		// nteRunde
		try {
			while (true) {
				doJob((GameDataMessageToClient) c.readMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < bankAmounts.size(); i++) {
			System.out.println(bankAmounts.get(i).toString());
		}
	}

	/**
	 * erstellt eine Antwort f�r die zweite Runde
	 * 
	 * @param readMessage
	 */

	private void doJob(GameDataMessageToClient readMessage) throws Exception {
		if (readMessage == null) {
			throw new Exception("Fehler bei der Nachricht");

		}
		// Protokoll zum Bankkonto
		bankAmounts.add(new AmountObject(readMessage.cash, readMessage.round));

		System.out.println("Momentane Runde:" + readMessage.round);
		System.out.println("Momentanes Guthaben:" + readMessage.cash);
		// Abbruchbedingung zum Testen
		// TODO: remove
		if (readMessage.cash < 0) {
			throw new Exception("Negatives Konto");
		}

		

		// Erzeuge neue KI-Message
		ClientToServerMessageCreator m = new ClientToServerMessageCreator(
				playerName);
		/******************************
		 * SECTION BESCHAFFUNG
		 */
		// Frage Wafer an:
		m.addRequest("Wafer", qualityTry);

		// Frage Geh�use an:
		m.addRequest("Geh�use", qualityTry);

		// Setze den Lohn:
		// Erste Runde brauchen wir ja kaum Lohn
		m.setWage(100);

		// Keine Maschinenerweiterung
		if (readMessage.round < 12) {
			m.setMachine(true);
		} else {
			m.setMachine(false);
		}
		// Maximal produzierende Menge immer ausnutzen
		int toBuy = readMessage.reporting.machinery.maxCapacity;

		// Kaufe keine MarketResearch
		m.setMarketResearch(false);
		/**
		 * SECTION: EINKAUF
		 */
		// Schleife �ber alle Anfragen:
		boolean waferBought = false;
		boolean caseBought = false;
		for (int i = 0; i < readMessage.purchase.requests.size(); i++) {
			// index des bisher besten angebots zur anfrage:
			int index = 0;
			// PreisLeistung des besten:
			double quotient = 0.0;
			// Schleife �ber alle Angebote des Marktes zu den Anfragen:
			for (int j = 0; j < readMessage.purchase.requests.get(i).supplierOffers
					.size(); j++) {
				double tmp = readMessage.purchase.requests.get(i).supplierOffers
						.get(j).quality
						/ (readMessage.purchase.requests.get(i).supplierOffers
								.get(j).price * 1.0);

				if (tmp > quotient) {
					quotient = tmp;
					index = j;
				}
			}

			// das beste liegt also an position "index"

			if (readMessage.purchase.requests.get(i).supplierOffers.get(index).name
					.equals("Wafer")&& !waferBought) {
				// Bestelle Wafer
				m.addAccepted(
						readMessage.purchase.requests.get(i).supplierOffers
								.get(index).name, readMessage.purchase.requests
								.get(i).supplierOffers.get(index).quality,
						toBuy * Constant.Constant.Production.WAFERS_PER_PANEL);
				waferBought=true;
			} else if(readMessage.purchase.requests.get(i).supplierOffers.get(index).name
					.equals("Geh�use")&& !caseBought) {
				// Bestelle Cases
				m.addAccepted(
						readMessage.purchase.requests.get(i).supplierOffers
								.get(index).name, readMessage.purchase.requests
								.get(i).supplierOffers.get(index).quality, toBuy);
				caseBought = true;
			}

		}

		/*************************************
		 * SECTION : PRODUCE + SELLING
		 */

		int indexWafer = -1;
		int noOfPanelsWafer = 0;
		int indexCase = -1;
		for (int i = 0; i < readMessage.storage.storageElements.size(); i++) {
			if (readMessage.storage.storageElements.get(i).type.equals("Panel")) {
				// Vertick die fertigen Panels
				
				m.addOffer(
						readMessage.storage.storageElements.get(i).quality,
						readMessage.storage.storageElements.get(i).quantity,
						(int) (readMessage.storage.storageElements.get(i).costs * 1.10));
			
				
				
			} else if (readMessage.storage.storageElements.get(i).type
					.equals("Wafer")) {

				// Absicherung, dass wenigsten fuer ein Panel genug Wafer da
				// sind!
				if ((int) (readMessage.storage.storageElements.get(i).quantity / Constant.Constant.Production.WAFERS_PER_PANEL) > noOfPanelsWafer) {

					noOfPanelsWafer = (int) (readMessage.storage.storageElements
							.get(i).quantity / Constant.Constant.Production.WAFERS_PER_PANEL);
					indexWafer = i;
				}

			} else if (readMessage.storage.storageElements.get(i).type
					.equals("Geh�use")) {
				indexCase = i;

			}
		}
		if (indexWafer != -1 && indexCase != -1) {
			// Es wurden also genug Wafer und Cases gefunden:
			int maxProduction = (noOfPanelsWafer > readMessage.storage.storageElements
					.get(indexCase).quantity) ? readMessage.storage.storageElements
					.get(indexCase).quantity : noOfPanelsWafer;

			m.addProductionOrder(
					readMessage.storage.storageElements.get(indexWafer).quality,
					readMessage.storage.storageElements.get(indexCase).quality,
					maxProduction);
		}

		// Sende daten zur�ck an Server:
		sendData(m);

	}

	/**
	 * k�mmert sich um die Antwort auf die erste Runde
	 * 
	 * @param readMessage
	 */
	private void doSecondRound(GameDataMessageToClient readMessage) {
		// Protokolliere das Bank konto mit
		bankAmounts.add(new AmountObject(readMessage.cash, readMessage.round));

		// Erzeuge neue KI-Message
		ClientToServerMessageCreator m = new ClientToServerMessageCreator(
				playerName);

		// Frage Wafer an:
		m.addRequest("Wafer", qualityTry);

		// Frage Geh�use an:
		m.addRequest("Geh�use", qualityTry);

		// Setze den Lohn:
		// Erste Runde brauchen wir ja kaum Lohn
		m.setWage(1000);

		// Keine Maschinenerweiterung
		m.setMachine(true);

		// Kaufe keine MarketResearch
		m.setMarketResearch(false);

		// Maximale Produktionsauslastung!:
		int toBuy = readMessage.reporting.machinery.maxCapacity;

		// Schleife �ber alle Anfragen:
		for (int i = 0; i < readMessage.purchase.requests.size(); i++) {
			// index des bisher besten angebots zur anfrage:
			int index = 0;
			// PreisLeistung des besten:
			double quotient = 0.0;
			// Schleife �ber alle Angebote des Marktes zu den Anfragen:
			for (int j = 0; j < readMessage.purchase.requests.get(i).supplierOffers
					.size(); j++) {
				double tmp = readMessage.purchase.requests.get(i).supplierOffers
						.get(j).quality
						/ (readMessage.purchase.requests.get(i).supplierOffers
								.get(j).price * 1.0);

				if (tmp > quotient) {
					quotient = tmp;
					index = j;
				}
			}

			// das beste liegt also an position "index"

			if (readMessage.purchase.requests.get(i).supplierOffers.get(index).name
					.equals("Wafer")) {
				// Bestelle Wafer
				m.addAccepted(
						readMessage.purchase.requests.get(i).supplierOffers
								.get(index).name, readMessage.purchase.requests
								.get(i).supplierOffers.get(index).quality,
						toBuy * Constant.Constant.Production.WAFERS_PER_PANEL);
			} else {
				// Bestelle Cases
				m.addAccepted(
						readMessage.purchase.requests.get(i).supplierOffers
								.get(index).name, readMessage.purchase.requests
								.get(i).supplierOffers.get(index).quality,
						toBuy);
			}

		}
		// Sende daten zur�ck an Server:
		sendData(m);

	}

	/**
	 * k�mmert sich um die User eingaben der ersten Runde
	 */
	private void doFirstRound() {
		// Erzeuge neue KI-Message
		ClientToServerMessageCreator m = new ClientToServerMessageCreator(
				playerName);

		// Frage Wafer an:
		m.addRequest("Wafer", qualityTry);

		// Frage Geh�use an:
		m.addRequest("Geh�use", qualityTry);

		// Setze den Lohn:
		// Erste Runde brauchen wir ja kaum Lohn
		m.setWage(1);

		// Erweitere die Maschine
		m.setMachine(true);

		// Kaufe keine MarketResearch
		m.setMarketResearch(false);

		// Sende daten zur�ck an Server:
		sendData(m);
	}

	private void sendData(ClientToServerMessageCreator s) {
		c.writeMessage(s.getSendMessage());
	}
}
