/**
 * 
 */
package Server;

/**
 * Klasse f�r den Produktionsauftrag
 * 
 * @author Lars
 * 
 */
public class ProductionOrder {
	// Anzahl der insgesamt erzeugten Produktionen
	private static int counter = 1;
	// ID des auftrags (Spieler unabh�ngige ID!)
	private int id;
	// Speichert die verwendeten Ressourcen
	private Resource wafer;
	private Resource cases;

	// Gibt die Gewichtung als ganzzahlige Prozentzahl an
	private int impactWafer = 80;
	private int impactCases = 20;

	// Parameter f�r die Anzahl der Wafer pro Panel:
	private int waferPerPanel = 54; // 72 max, 36 min ~54 durchschnitt

	// maximaler Qualit�tszuwachs durch FE+Motivation+Land:
	private int maxAddition = 20;
	// Speichert hinterher das erzugte Panel:
	private FinishedGood panel;
	// Herzustellende Menge laut Auftrag
	private int quantityToProduce;
	// tats�chlich hergestellte Menge:
	private int quantityProduced;

	/**
	 * Erzeugt neue Production order
	 * 
	 * @param wafer
	 *            Resource f�r den Wafer
	 * @param cases
	 *            Resource f�r das case
	 * @param quantity
	 */
	public ProductionOrder(Resource wafer, Resource cases, int quantity) {
		this.id = counter;
		// TODO: sicherheitsabfragen
		this.wafer = wafer;
		this.cases = cases;
		this.quantityToProduce = quantity;
		counter++;

	}

	/**
	 * abgeleitetes attribut
	 * 
	 * @return int Ausschuss
	 */
	public int getWaste() {
		return quantityToProduce - quantityProduced;

	}

	/**
	 * 
	 * @return gibt den verwendeten Wafer wieder
	 */
	public Resource getWafer() {
		return this.wafer;
	}

	/**
	 * 
	 * @return gibt das verwendete Geh�use zur�ck
	 */
	public Resource getCase() {
		return this.cases;
	}

	/**
	 * 
	 * @return gibt das Fertige Erzeugnis zur�ck
	 */
	public FinishedGood getPanel() {
		return this.panel;

	}

	/**
	 * Gibt die Produzierte Menge an (die wird in produce gesetzt)
	 * 
	 * @return int produzierte Menge
	 */
	public int getProduced() {
		return quantityProduced;
	}

	/**
	 * Gibt die in auftragegebene Menge an
	 * 
	 * @return int Anzahl aus dem Konstruktor
	 */
	public int getRequested() {
		return quantityToProduce;

	}

	/**
	 * Returns the global ID
	 * 
	 * @return
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Wird jede Runde aufgerufen und fertigt den Produktionsauftrag
	 * 
	 * @param m
	 *            Maschine, auf der produziert wird
	 * @param s
	 *            Storage, in den die Fertigprodukte gebucht werden.
	 * @param Zuschlag
	 *            Der Zuschlagssatz aus Motivation, Land und Forschung
	 * 
	 */
	public void produce(Machinery m, Storage s, int Zuschlag) {
		// Pr�fe ob bereits produziert wurde:
		if (panel != null) {
			// panel bereits gesetzt, also exisitert das Produkt schon im
			// Storage
			return;
		}
		// Es wird in doubles gerechnet:
		double additionalFactor = Zuschlag / 100;
		// durchschnittsqualit�t der Produkte mit Gewichtung:
		double midQuality = (wafer.getQuality() * impactWafer + cases
				.getQuality() * impactCases) / 100;
		// neue Qualit�t (nicht mehr als double)
		int newQuality = (int) (midQuality * additionalFactor) * 10;

		// Pr�fe ob die neue Qualit�t durch den Zuschlag zu sehr ver�ndert wurde
		newQuality = (newQuality - midQuality > maxAddition) ? (int) (midQuality + maxAddition)
				: newQuality;

		// Berechne herstellkosten:
		int costs = wafer.getCosts() * quantityToProduce * waferPerPanel
				+ cases.getCosts() * quantityToProduce;
		// TODO: maschinenkosten einrechnen

		// Berechne die tats�chliche produzierte Menge (Ausschuss bereits
		// abgezogen)
		quantityProduced = m.produce(quantityToProduce);

		// Zieh die Storage elemente aus dem Storage ab:
		// hierbei wird quantityToProduce genutzt, da der ausschuss ja trotzdem
		// hinterher im lager fehlt
		s.unstore(wafer, waferPerPanel * quantityToProduce);
		s.unstore(cases, quantityToProduce);
		// Alle daten sind jetzt aus dem Storage. neues Panel erzeugen
		panel = FinishedGood.create(newQuality, costs);
		// speicher das panel in storage:
		s.store(panel, quantityProduced);

	}

}
