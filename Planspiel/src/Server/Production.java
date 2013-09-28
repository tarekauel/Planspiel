package Server;

import java.util.ArrayList;

public class Production extends Department {
	// Liste aller jemals erstellten Produktions aufträge
	ArrayList<ProductionOrder> listOfAllProductionOrders;
	// Liste aller offenen (noch nicht produzierten) Aufträgen
	ArrayList<ProductionOrder> listOfOpenProductionOrders;
	// Referenz auf die Maschine (auf der wir ja produzieren müssen)
	Machinery machine;

	// Liste der Auslastungen für diesen Spieler
	ArrayList<TPercentOfUsage> listOfAllPercentOfUsage;

	// Parameter für die Anzahl der Wafer pro Panel:
	private int waferPerPanel = 54; // 72 max, 36 min ~54 durchschnitt

	/**
	 * Regulärer Konstruktor der Produktion, erzeugt zeitgleich eine neue
	 * Maschine
	 * 
	 * @param c
	 *            Referenz des Unternehmens
	 * @param fix
	 *            Fixkosten
	 * @throws Exception
	 *             falls Abteilung nicht erstellt werden konnte
	 */
	public Production(Company c) throws Exception {
		super(c, "Produktion", 0);
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
	 * @return gibt die Maschine der Produktion zurück
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
	 *            Das Gehäuse mit dem die Produktion gestartet wird
	 * @param quantity
	 *            Die Anzahl der gewünschten Produktion
	 */
	public void createProductionOrder(Resource wafer, Resource cases,
			int quantity) {
		// erzeuge den Auftrag:
		ProductionOrder po = new ProductionOrder(wafer, cases, quantity);
		// nimm ihn in die Liste ALLER Aufträge auf
		listOfAllProductionOrders.add(po);
		// nimm ihn in die Liste der noch nicht bearbeiteten Aufträge auf
		listOfOpenProductionOrders.add(po);
	}

	/**
	 * produziert alle offenen Produktionsaufträge und leert die Liste der
	 * offenen Aufträge am Ende. Das Abbuchen der Rohstoffe erfolgt in der
	 * Produktion, das gutschreiben der Panels im Auftrag
	 * 
	 */
	public void produce() {
		// Gibt die Maximale Anzahl der Werkstücke an
		int max = this.machine.getMaxCapacity();
		// Zählt mit, wieviele Werkstücke auf der Maschine lagen
		int triedToProduce = 0;

		// Es muss sicher gestellt werden, dass nicht mehr Werkstücke auf der
		// Maschine lagen, als diese kann.

		// Schleife über alle Production Orders
		for (int i = 0; i < listOfOpenProductionOrders.size(); i++) {
			// Darf überhaupt noch jemand produzieren?
			if (triedToProduce >= max) {
				// scheinbar nicht
				break;
			}

			// Schleife über jede einzelne Position des Auftrages (Werkstück)
			for (int j = 0; j < listOfOpenProductionOrders.get(i)
					.getRequested(); j++) {
				// Darf überhaupt noch jemand produzieren?
				if (triedToProduce >= max) {
					// scheinbar nicht
					break;
				}

				// Abbuchen der Ressourcen:
				// Zieh die Storage elemente aus dem Storage ab:
				// nutze dafür das lager des spielers:
				// Wafer abbuchen (direkt in der Anzahl waferPerPanel)
				this.getCompany()
						.getStorage()
						.unstore(listOfOpenProductionOrders.get(i).getWafer(),
								waferPerPanel);
				// Gehäuse abbuchen
				this.getCompany()
						.getStorage()
						.unstore(listOfOpenProductionOrders.get(i).getCase(), 1);

				// "Werkstück auf Maschine legen":
				triedToProduce++;
				// Gucken, ob wir tatsächlich produzieren:
				if (machine.isJunk()) {
					// Das Teil ist also Ausschuss
					continue;
				} else {
					// Produziere das fertige Panel
					// TODO: remove 100 , fester Wert für Motivation etc.
					listOfOpenProductionOrders.get(i).produce(100,
							this.getCompany().getStorage());
				}
			}
		}

		// summe aller aufträge
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
		// Initialisiere die offenen Aufträge, damit keine Aufträge in die
		// nächste Runde rutschen
		listOfOpenProductionOrders.clear();
	}

	/**
	 * 
	 * @return gibt die Liste aller bisher erstellten Produktionsaufträge seit
	 *         Spielbeginn zurück
	 */
	public ArrayList<ProductionOrder> getListOfAllProductionOrders() {
		return listOfAllProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller offenen Aufträge zurück, also die, die diese
	 *         Runde angelegt wurden
	 */
	public ArrayList<ProductionOrder> getListOfOpenProductionOrders() {
		return listOfOpenProductionOrders;
	}

	/**
	 * 
	 * @return gibt die Liste aller Auslastungen für diesen Spieler wieder
	 */
	public ArrayList<TPercentOfUsage> getListOfAllPercentOfUsage() {
		return listOfAllPercentOfUsage;
	}
}
