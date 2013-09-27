package Server;

import java.util.ArrayList;

public class Production extends Department {
	// Liste aller jemals erstellten Produktions aufträge
	ArrayList<ProductionOrder> listOfAllProductionOrders;
	// Liste aller offenen (noch nicht produzierten) Aufträgen
	ArrayList<ProductionOrder> listOfOpenProductionOrders;
	// Referenz auf die Maschine (auf der wir ja produzieren müssen)
	Machinery machine;

	/**
	 * Regulärer Konstruktor der Produktion, erzeugt zeitgleich eine neue Maschine
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

	/**
	 * damit niemand eine Falsche Abteilung erzeugen kann (name falsch)
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
	public Machinery getMachine(){
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
	 * offenen Aufträge
	 */
	public void produce() {

		// Arbeite die Offenen aufträge ab
		while (listOfOpenProductionOrders.size() != 0) {
			// Stoße beim ersten element der Liste die Produktion an.....
			listOfOpenProductionOrders.get(0).produce(machine,
					this.getCompany().getStorage(),
					this.getCompany().getReporting().getCurrentMotivation());
			// ... und lösch das Objekt aus dieser Liste (Es existiert noch in
			// der Liste aller aufträge)
			listOfOpenProductionOrders.remove(0);

		}
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

}
