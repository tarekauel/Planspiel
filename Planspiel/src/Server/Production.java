package Server;

import java.util.ArrayList;

public class Production extends DepartmentRoundSensitive {
	// Liste aller jemals erstellten Produktions auftr�ge
	ArrayList<ProductionOrder> listOfAllProductionOrders = new ArrayList<ProductionOrder>();
	// Liste aller offenen (noch nicht produzierten) Auftr�gen
	ArrayList<ProductionOrder> listOfOpenProductionOrders = new ArrayList<ProductionOrder>();
	// Referenz auf die Maschine (auf der wir ja produzieren m�ssen)
	Machinery machine;

	// Liste der Auslastungen f�r diesen Spieler
	ArrayList<TPercentOfUsage> listOfAllPercentOfUsage;

	// Parameter f�r die Anzahl der Wafer pro Panel:
	private int waferPerPanel = 54; // 72 max, 36 min ~54 durchschnitt

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
		machine = new Machinery();
	}
	public int getFixCosts(){
		return super.getFixCosts()+this.machine.getCosts();
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
	 * 
	 * @return gibt die Maschine der Produktion zur�ck
	 */
	public Machinery getMachine() {
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
	 */
	public void createProductionOrder(Resource wafer, Resource cases,
			int quantity) {
		// erzeuge den Auftrag:
		ProductionOrder po = new ProductionOrder(wafer, cases, quantity);
		// nimm ihn in die Liste ALLER Auftr�ge auf
		listOfAllProductionOrders.add(po);
		// nimm ihn in die Liste der noch nicht bearbeiteten Auftr�ge auf
		listOfOpenProductionOrders.add(po);
	}

	/**
	 * produziert alle offenen Produktionsauftr�ge und leert die Liste der
	 * offenen Auftr�ge am Ende. Das Abbuchen der Rohstoffe erfolgt in der
	 * Produktion, das gutschreiben der Panels im Auftrag
	 * 
	 */
	public void produce() throws Exception{
		// Gibt die Maximale Anzahl der Werkst�cke an
		int max = this.machine.getMaxCapacity();
		// Z�hlt mit, wieviele Werkst�cke auf der Maschine lagen
		int triedToProduce = 0;

		// Es muss sicher gestellt werden, dass nicht mehr Werkst�cke auf der
		// Maschine lagen, als diese kann.

		// Schleife �ber alle Production Orders
		for (int i = 0; i < listOfOpenProductionOrders.size(); i++) {
			// Darf �berhaupt noch jemand produzieren?
			if (triedToProduce >= max) {
				// scheinbar nicht
				break;
			}

			// Schleife �ber jede einzelne Position des Auftrages (Werkst�ck)
			for (int j = 0; j < listOfOpenProductionOrders.get(i)
					.getRequested(); j++) {
				// Darf �berhaupt noch jemand produzieren?
				if (triedToProduce >= max) {
					// scheinbar nicht
					break;
				}

				// Abbuchen der Ressourcen:
				// Zieh die Storage elemente aus dem Storage ab:
				// nutze daf�r das lager des spielers:
				// Wafer abbuchen (direkt in der Anzahl waferPerPanel)
				this.getCompany()
						.getStorage()
						.unstore(listOfOpenProductionOrders.get(i).getWafer(),
								waferPerPanel);
				// Geh�use abbuchen
				this.getCompany()
						.getStorage()
						.unstore(listOfOpenProductionOrders.get(i).getCase(), 1);

				// "Werkst�ck auf Maschine legen":
				triedToProduce++;
				// Gucken, ob wir tats�chlich produzieren:
				if (machine.isJunk()) {
					// Das Teil ist also Ausschuss
					continue;
				} else {
					// Produziere das fertige Panel
					// TODO: remove 100 , fester Wert f�r Motivation etc.
					listOfOpenProductionOrders.get(i).produce(100,
							this.getCompany().getStorage());
				}
			}
		}

		// summe aller auftr�ge
		int sum = 0;
		// hole Summe aller ProductionOrders dieser Runde
		for (ProductionOrder o : listOfOpenProductionOrders) {
			sum += o.getRequested();
		}
		try {
			listOfAllPercentOfUsage.add(new TPercentOfUsage(sum
					/ this.machine.getMaxCapacity(), GameEngine.getGameEngine()
					.getRound()));
		} catch (Exception e) {
			// Fehler bei der Erstellung einer Auslastung
			// TODO: Fehlerhandling?!
		}
		// Initialisiere die offenen Auftr�ge, damit keine Auftr�ge in die
		// n�chste Runde rutschen
		listOfOpenProductionOrders.clear();
	}

	/**
	 * 
	 * @return gibt die Liste aller bisher erstellten Produktionsauftr�ge seit
	 *         Spielbeginn zur�ck
	 */
	public ArrayList<ProductionOrder> getListOfAllProductionOrders() {
		return listOfAllProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller offenen Auftr�ge zur�ck, also die, die diese
	 *         Runde angelegt wurden
	 */
	public ArrayList<ProductionOrder> getListOfOpenProductionOrders() {
		return listOfOpenProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller Auslastungen f�r diesen Spieler wieder
	 */
	public ArrayList<TPercentOfUsage> getListOfAllPercentOfUsage() {
		return listOfAllPercentOfUsage;
	}
	
	@Override
	public void prepareForNewRound( int round ) {
		listOfOpenProductionOrders = new ArrayList<ProductionOrder>();
	}
}
