package Server;

import java.util.ArrayList;

import Constant.Constant;
import Logger.Log;

public class Production extends DepartmentRoundSensitive {
	// Liste aller jemals erstellten Produktions auftrï¿½ge
	ArrayList<ProductionOrder>	listOfAllProductionOrders	= new ArrayList<ProductionOrder>();
	// Liste aller offenen (noch nicht produzierten) Auftrï¿½gen
	ArrayList<ProductionOrder>	listOfOpenProductionOrders	= new ArrayList<ProductionOrder>();
	// Referenz auf die Maschine (auf der wir ja produzieren mï¿½ssen)
	Machinery					machine;

	// Liste der Auslastungen fï¿½r diesen Spieler
	ArrayList<TPercentOfUsage>	listOfAllPercentOfUsage		= new ArrayList<TPercentOfUsage>();


	/**
	 * Regulï¿½rer Konstruktor der Produktion, erzeugt zeitgleich eine neue
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
	 * @return gibt die Fixkosten zurück
	 */
	public int getFixCosts() {
		Log.get(super.getFixCosts() + this.machine.getCosts());
		return super.getFixCosts() + this.machine.getCosts();
	}

	/**
	 * 
	 * @return gibt die Maschine der Produktion zurï¿½ck
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
	 *            Das Gehï¿½use mit dem die Produktion gestartet wird
	 * @param quantity
	 *            Die Anzahl der gewï¿½nschten Produktion
	 * @return true: Produktionsorder konnte angelegt werden
	 *         false: Produktionsorder konnte nicht angelegt werden (nicht
	 *         genügen Bargeld)
	 */
	public boolean createProductionOrder(Resource wafer, Resource cases, int quantity) {
		Log.method(new Object[]{wafer,cases,quantity});
		// Prüfen, ob genug Geld für die Order (Orderkosten) auf dem Konto ist
		if (getCompany().getBankAccount().decreaseBalance( Constant.Production.COST_PER_ORDER )) {

			// erzeuge den Auftrag:
			ProductionOrder po = new ProductionOrder(wafer, cases, quantity);
			// nimm ihn in die Liste ALLER Auftrï¿½ge auf
			listOfAllProductionOrders.add(po);
			// nimm ihn in die Liste der noch nicht bearbeiteten Auftrï¿½ge auf
			listOfOpenProductionOrders.add(po);
			Log.methodExit();
			return true;
		}
		Log.methodExit();
		return false;
	}

	/**
	 * produziert alle offenen Produktionsauftrï¿½ge und leert die Liste der
	 * offenen Auftrï¿½ge am Ende. Das Abbuchen der Rohstoffe erfolgt in der
	 * Produktion, das gutschreiben der Panels im Auftrag
	 * 
	 */
	public void produce() throws Exception {
		Log.method();
		// Gibt die Maximale Anzahl der Werkstï¿½cke an
		int max = this.machine.getMaxCapacity();
		// Zï¿½hlt mit, wieviele Werkstï¿½cke auf der Maschine lagen
		int triedToProduce = 0;

		// Es muss sicher gestellt werden, dass nicht mehr Werkstï¿½cke auf der
		// Maschine lagen, als diese kann.

		// Schleife ï¿½ber alle Production Orders

		for (ProductionOrder p : listOfOpenProductionOrders) {
			boolean innerBreak = false;
			// Darf ï¿½berhaupt noch jemand produzieren?
			if (triedToProduce >= max) {
				// scheinbar nicht
				break;
			}

			// Schleife ï¿½ber jede einzelne Position des Auftrages
			// (Werkstï¿½ck)
			for (int j = 0; j < p.getRequested(); j++) {
				// Darf ï¿½berhaupt noch jemand produzieren?
				if (triedToProduce >= max) {
					// scheinbar nicht
					break;
				}

				// Zieht den Lohn für die Produktion vom Konto ab
				if (!getCompany().getBankAccount().decreaseBalance(
						Constant.Production.WORKING_HOURS_PER_PANEL * getCompany().getHumanResources().getWagesPerHour().getAmount())) {
					innerBreak = true;
					break;					
				}

				// Abbuchen der Ressourcen:
				// Zieh die Storage elemente aus dem Storage ab:
				// nutze dafï¿½r das lager des spielers:
				// Wafer abbuchen (direkt in der Anzahl waferPerPanel)
				this.getCompany().getStorage().unstore(p.getWafer(), Constant.Production.WAFERS_PER_PANEL);
				// Gehï¿½use abbuchen
				this.getCompany().getStorage().unstore(p.getCase(), 1);

				// "Werkstï¿½ck auf Maschine legen":
				triedToProduce++;

				// Gucken, ob wir tatsï¿½chlich produzieren:
				if (machine.isJunk()) {
					// Das Teil ist also Ausschuss
					continue;
				} else {
					// Produziere das fertige Panel
					// TODO: remove 100 , fester Wert fï¿½r Motivation etc.
					p.produce(100, this.getCompany().getStorage(), this.machine);
				}
			}

			// Lager die Produktion ein und berechnet die Kosten neu
			p.storeProduction(this.getCompany().getStorage());

			// Wird true, wenn auf dem Konto kein Geld mehr für Produktion ist
			if (innerBreak)
				break;

		}

		// summe aller auftrï¿½ge
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
	 * @return gibt die Liste aller bisher erstellten Produktionsauftrï¿½ge seit
	 *         Spielbeginn zurï¿½ck
	 */
	public ArrayList<ProductionOrder> getListOfAllProductionOrders() {
		Log.get(listOfAllProductionOrders);
		return listOfAllProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller offenen Auftrï¿½ge zurï¿½ck, also die, die
	 *         diese
	 *         Runde angelegt wurden
	 */
	public ArrayList<ProductionOrder> getListOfOpenProductionOrders() {
		Log.get(listOfOpenProductionOrders);
		return listOfOpenProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller Auslastungen fï¿½r diesen Spieler wieder
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
