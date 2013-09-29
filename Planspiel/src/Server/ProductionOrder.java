/**
 * 
 */
package Server;

/**
 * Klasse für den Produktionsauftrag
 * 
 * @author Lars
 * 
 */
public class ProductionOrder {
	// Anzahl der insgesamt erzeugten Produktionen
	private static int counter = 1;
	// ID des auftrags (Spieler unabhängige ID!)
	private int id = 0;
	// Speichert die verwendeten Ressourcen
	private Resource wafer = null;
	private Resource cases = null;

	// Gibt die Gewichtung als ganzzahlige Prozentzahl an
	private int impactWafer = 80;
	private int impactCases = 20;

	// Parameter für die Anzahl der Wafer pro Panel:
	private int waferPerPanel = 54; // 72 max, 36 min ~54 durchschnitt

	// maximaler Qualitätszuwachs durch FE+Motivation+Land:
	private int maxAddition = 20;
	// Speichert hinterher das erzugte Panel:
	private FinishedGood panel = null;
	// Herzustellende Menge laut Auftrag
	private int quantityToProduce = 0;
	// tatsächlich hergestellte Menge:
	private int quantityProduced = 0;

	/**
	 * Erzeugt neue Production order
	 * 
	 * @param wafer
	 *            Resource für den Wafer
	 * @param cases
	 *            Resource für das case
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
	 * @return gibt das verwendete Gehäuse zurück
	 */
	public Resource getCase() {
		return this.cases;
	}

	/**
	 * 
	 * @return gibt das Fertige Erzeugnis zurück
	 */
	public FinishedGood getPanel() {
		return this.panel;

	}

	/**
	 * Gibt die Produzierte Menge an
	 * 
	 * @return int produzierte Menge
	 */
	public int getProduced() {
		return quantityProduced;
	}

	/**
	 * Erhöht die Produzierte Menge um 1
	 * 
	 * @return int produzierte Menge
	 */
	public void increaseProduced() {
		if (quantityProduced < quantityToProduce) {
			quantityProduced++;

		}
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
	 * @param s
	 *            Storage, in den die Fertigprodukte gebucht werden.
	 * @param Zuschlag
	 *            Der Zuschlagssatz aus Motivation, Land und Forschung
	 * 
	 */
	public void produce(int Zuschlag, Storage s) {
		quantityProduced++;

		// Prüfe ob bereits produziert wurde:
		if (panel != null) {
			// panel bereits gesetzt, also muss das nicht mehr berechnet werden.
			store(s);
			return;
		}
		// Es wird in doubles gerechnet:
		double additionalFactor = Zuschlag / 100;
		// durchschnittsqualität der Produkte mit Gewichtung:
		double midQuality = (wafer.getQuality() * impactWafer + cases
				.getQuality() * impactCases) / 100;
		// neue Qualität (nicht mehr als double)
		int newQuality = (int) (midQuality * additionalFactor) * 10;

		// Prüfe ob die neue Qualität durch den Zuschlag zu sehr verändert wurde
		newQuality = (newQuality - midQuality > maxAddition) ? (int) (midQuality + maxAddition)
				: newQuality;

		// Berechne herstellkosten:
		int costs = wafer.getCosts() * quantityToProduce * waferPerPanel
				+ cases.getCosts() * quantityToProduce;
		// TODO: maschinenkosten einrechnen

		// neues Panel erzeugen
		panel = FinishedGood.create(newQuality, costs);

		store(s);
	}

	private void store(Storage s) {
		s.store(this.panel, 1);
	}

}
