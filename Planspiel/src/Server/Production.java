package Server;

import java.util.ArrayList;

import Constant.Constant;
import Logger.Log;

public class Production extends DepartmentRoundSensitive {
	// Liste aller jemals erstellten Produktions auftr�ge
	ArrayList<ProductionOrder>	listOfAllProductionOrders	= new ArrayList<ProductionOrder>();
	// Liste aller offenen (noch nicht produzierten) Auftr�gen
	ArrayList<ProductionOrder>	listOfOpenProductionOrders	= new ArrayList<ProductionOrder>();
	// Referenz auf die Maschine (auf der wir ja produzieren m�ssen)
	Machinery					machine;

	// Liste der Auslastungen f�r diesen Spieler
	ArrayList<TPercentOfUsage>	listOfAllPercentOfUsage		= new ArrayList<TPercentOfUsage>();


	/**
	 * Regul�rer Konstruktor der Produktion, erzeugt zeitgleich eine neue
	 * Maschine
	 * 
	 * @param c
	 *            Referenz des Unternehmens
	 * @param fix
	 *            Fixkosten
	 * @throws Exception
	 *             falls Abteilung nicht erstellt werden konnte
	 */
	public Production(Company c, int fix) throws Exception {
		super(c, "Produktion", fix);
		Log.method(new Object[]{c,fix});
		machine = new Machinery();
		Log.methodExit();
	}

	/**
	 * privater Konstruktor damit niemand eine Falsche Abteilung erzeugen kann
	 * (name falsch)
	 * 
	 * @param c
	 *            Referenz des Unternehmens
	 * @param n
	 *            Name der Abteilung
	 * @param f
	 *            Fixkosten der Abteilung
	 * @throws Exception
	 */
	private Production(Company c, String n, int f) throws Exception {
		super(c, n, f);
		// Hier ist nichts mehr zu tun

	}

	/**
	 * Liefer die Fixkosten
	 * 
	 * @return gibt die Fixkosten zur�ck
	 */
	public int getFixCosts() {
		Log.get(super.getFixCosts() + this.machine.getCosts());
		return super.getFixCosts() + this.machine.getCosts();
	}

	/**
	 * 
	 * @return gibt die Maschine der Produktion zur�ck
	 */
	public Machinery getMachine() {
		Log.get(machine);
		return this.machine;
	}

	/**
	 * Erzeuge einen neuen Produktionsauftrag und pflege ihn in die Listen ein.
	 * 
	 * @param wafer
	 *            Der Wafer mit dem die Produktion gestartet wird
	 * @param cases
	 *            Das Geh�use mit dem die Produktion gestartet wird
	 * @param quantity
	 *            Die Anzahl der gew�nschten Produktion
	 * @return true: Produktionsorder konnte angelegt werden
	 *         false: Produktionsorder konnte nicht angelegt werden (nicht
	 *         gen�gen Bargeld)
	 */
	public boolean createProductionOrder(Resource wafer, Resource cases, int quantity) {
		Log.method(new Object[]{wafer,cases,quantity});
		// Pr�fen, ob genug Geld f�r die Order (Orderkosten) auf dem Konto ist
		if (getCompany().getBankAccount().decreaseBalance( Constant.Production.COST_PER_ORDER )) {

			// erzeuge den Auftrag:
			ProductionOrder po = new ProductionOrder(wafer, cases, quantity);
			// nimm ihn in die Liste ALLER Auftr�ge auf
			listOfAllProductionOrders.add(po);
			// nimm ihn in die Liste der noch nicht bearbeiteten Auftr�ge auf
			listOfOpenProductionOrders.add(po);
			Log.methodExit();
			return true;
		}
		Log.methodExit();
		return false;
	}

	/**
	 * produziert alle offenen Produktionsauftr�ge und leert die Liste der
	 * offenen Auftr�ge am Ende. Das Abbuchen der Rohstoffe erfolgt in der
	 * Produktion, das gutschreiben der Panels im Auftrag
	 * 
	 */
	public void produce() throws Exception {
		Log.method();
		// Gibt die Maximale Anzahl der Werkst�cke an
		int max = this.machine.getMaxCapacity();
		// Z�hlt mit, wieviele Werkst�cke auf der Maschine lagen
		int triedToProduce = 0;

		// Es muss sicher gestellt werden, dass nicht mehr Werkst�cke auf der
		// Maschine lagen, als diese kann.

		// Schleife �ber alle Production Orders

		for (ProductionOrder p : listOfOpenProductionOrders) {
			boolean innerBreak = false;
			// Darf �berhaupt noch jemand produzieren?
			if (triedToProduce >= max) {
				// scheinbar nicht
				break;
			}

			// Schleife �ber jede einzelne Position des Auftrages
			// (Werkst�ck)
			for (int j = 0; j < p.getRequested(); j++) {
				// Darf �berhaupt noch jemand produzieren?
				if (triedToProduce >= max) {
					// scheinbar nicht
					break;
				}

				// Zieht den Lohn f�r die Produktion vom Konto ab
				if (!getCompany().getBankAccount().decreaseBalance(
						Constant.Production.WORKING_HOURS_PER_PANEL * getCompany().getHumanResources().getWagesPerHour().getAmount())) {
					innerBreak = true;
					break;					
				}

				// Abbuchen der Ressourcen:
				// Zieh die Storage elemente aus dem Storage ab:
				// nutze daf�r das lager des spielers:
				// Wafer abbuchen (direkt in der Anzahl waferPerPanel)
				this.getCompany().getStorage().unstore(p.getWafer(), Constant.Production.WAFERS_PER_PANEL);
				// Geh�use abbuchen
				this.getCompany().getStorage().unstore(p.getCase(), 1);

				// "Werkst�ck auf Maschine legen":
				triedToProduce++;

				// Gucken, ob wir tats�chlich produzieren:
				if (machine.isJunk()) {
					// Das Teil ist also Ausschuss
					continue;
				} else {
					// Produziere das fertige Panel
					// TODO: remove 100 , fester Wert f�r Motivation etc.
					p.produce(100, this.getCompany().getStorage(), this.machine);
				}
			}

			// Lager die Produktion ein und berechnet die Kosten neu
			p.storeProduction(this.getCompany().getStorage());

			// Wird true, wenn auf dem Konto kein Geld mehr f�r Produktion ist
			if (innerBreak)
				break;

		}

		// summe aller auftr�ge
		int sum = 0;
		// hole Summe aller ProductionOrders dieser Runde
		for (ProductionOrder o : listOfOpenProductionOrders) {
			sum += o.getRequested();
		}

		listOfAllPercentOfUsage.add(new TPercentOfUsage(sum / this.machine.getMaxCapacity(), GameEngine.getGameEngine()
				.getRound()));
		Log.methodExit();
	}

	/**
	 * 
	 * @return gibt die Liste aller bisher erstellten Produktionsauftr�ge seit
	 *         Spielbeginn zur�ck
	 */
	public ArrayList<ProductionOrder> getListOfAllProductionOrders() {
		Log.get(listOfAllProductionOrders);
		return listOfAllProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller offenen Auftr�ge zur�ck, also die, die
	 *         diese
	 *         Runde angelegt wurden
	 */
	public ArrayList<ProductionOrder> getListOfOpenProductionOrders() {
		Log.get(listOfOpenProductionOrders);
		return listOfOpenProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller Auslastungen f�r diesen Spieler wieder
	 */
	public ArrayList<TPercentOfUsage> getListOfAllPercentOfUsage() {
		Log.get(listOfAllPercentOfUsage);
		return listOfAllPercentOfUsage;
	}

	@Override
	public void prepareForNewRound(int round) {
		Log.method(round);
		listOfOpenProductionOrders = new ArrayList<ProductionOrder>();
		Log.methodExit();
	}
}
