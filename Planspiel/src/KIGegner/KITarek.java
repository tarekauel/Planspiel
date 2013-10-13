package KIGegner;

import Client.Connection.Client;
import Constant.Constant;
import Message.GameDataMessageToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient.SupplierOfferToClient;
import Message.LoginConfirmationMessage;
import Message.LoginMessage;

public class KITarek extends Thread {
		
	private Client c;
	
	private String playerName;
	
	private int quality = 60;
	
	private GameDataMessageToClient reply; 
	ClientToServerMessageCreator m;
	
	public static void main(String[] args) {
		new KITarek();
	}
	
	public KITarek() {

		if( login()) {
			System.out.println("Login-OK");
			start();
		}
	}
	
	private boolean login() {
		this.c = new Client();
		this.c.connect("127.0.0.1", Constant.Server.TCP_PORT);
		playerName = "KiTarek";
		// Sende die Daten an den Server
		c.writeMessage(new LoginMessage(playerName, "KI-Programmed",
				"usa"));
		// Empfange die Daten
		LoginConfirmationMessage msg = (LoginConfirmationMessage) c
				.readMessage();
		// Gib zurück ob eingeloggt wurde.
		return msg.getSuccess();

	}
	
	@Override
	public void run() {
		doFirstRound();	
		reply = (GameDataMessageToClient) c.readMessage();
		m = new ClientToServerMessageCreator(playerName);
		doSecondRound();
		reply = (GameDataMessageToClient) c.readMessage();
	}
	
	private void doFirstRound() {
		// Erzeuge neue KI-Message
		m = new ClientToServerMessageCreator(
				playerName);

		// Frage Wafer an:
		m.addRequest("Wafer", quality);

		// Frage Gehäuse an:
		m.addRequest("Gehäuse", quality);

		// Setze den Lohn:
		// Erste Runde brauchen wir ja kaum Lohn
		m.setWage(1);

		// Erweitere die Maschine
		m.setMachine(true);

		// Kaufe keine MarketResearch
		m.setMarketResearch(false);

		// Sende daten zurück an Server:
		sendData(m);
	}
	
	private void doSecondRound() {
		acceptOffer();
		sendData(m);
	}
	
	private void acceptOffer() {
		RequestToClient reqA = reply.purchase.requests.get(0);
		RequestToClient reqB = reply.purchase.requests.get(0);
		
		int toProduce = 1000;
		double plA = 0.0;
		SupplierOfferToClient toAcceptA = null;
		for( SupplierOfferToClient offer : reqA.supplierOffers ) {
			if( ((double) offer.price) / offer.quality  > plA ) {
				toAcceptA = offer;
				plA = ((double) offer.price) / offer.quality;
			}
		}
		
		int quantityA = (toAcceptA.name.equals("Wafer")) ? toProduce * Constant.Production.WAFERS_PER_PANEL : toProduce; 
		m.addAccepted(toAcceptA.name, toAcceptA.quality, quantityA);
		
		double plB = 0.0;
		SupplierOfferToClient toAcceptB = null;
		for( SupplierOfferToClient offer : reqB.supplierOffers ) {
			if( ((double) offer.price) / offer.quality  > plB ) {
				toAcceptB = offer;
				plB = ((double) offer.price) / offer.quality;
			}
		}
		
		int quantityB = (toAcceptB.name.equals("Wafer")) ? toProduce * Constant.Production.WAFERS_PER_PANEL : toProduce; 
		m.addAccepted(toAcceptB.name, toAcceptB.quality, quantityB);
		
	}
	
	private void sendData(ClientToServerMessageCreator s) {
		c.writeMessage(s.getSendMessage());
	}

}
