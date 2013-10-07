/**
 * 
 */
package Server;

import Constant.Constant;

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
		if(!checkResource(wafer)){
			throw new IllegalArgumentException("Wafer ist null.");
		}
		if(!checkResource(cases)){
			throw new IllegalArgumentException("Gehaeuse ist null.");
		}
		if(!checkQuantity(quantity)){
			throw new IllegalArgumentException("Quantity ist nicht größer 0");
		}
		this.wafer = wafer;
		this.cases = cases;
		this.quantityToProduce = quantity;
		counter++;
	}

	private boolean checkQuantity(int quantity){
		return quantity > 0;
	}
	
	private boolean checkResource(Resource wafer){
		return wafer!=null;
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
	 * 
	 * @return int produzierte Menge
	 */
	public void increaseProduced() {
		//TODO: sinnfrei? war das ein geistesblitz von mir, oder hat hier wer unsinn verzapft?
		//Verwendungsnachweis negativ
		if (quantityProduced < quantityToProduce) {
			quantityProduced++;

		}
	}

	/**
	 * Gibt die in auftraggegebene Menge an
	 * 
	 * @return int Anzahl aus dem Konstruktor
	 */
	public int getRequested() {

		return quantityToProduce;

	}

	/**
	 * Returns the global ID
	 * 
	 * @return global ID
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Wird jede Runde aufgerufen und fertigt den Produktionsauftrag
	 * 
	 * @param s
	 *            Storage, in den die Fertigprodukte gebucht werden.
	 * @param advantage
	 *            Der Zuschlagssatz aus Motivation, Land und Forschung
	 * 
	 */
	public void produce(int advantage, Storage s, Machinery m) throws Exception {
		quantityProduced++;


		// Prüfe ob bereits produziert wurde:
		if (panel != null) {
			// panel bereits gesetzt, also muss das nicht mehr berechnet werden.
			return;
		}

		// Es wird in doubles gerechnet:
		double additionalFactor = advantage / 100;
		// durchschnittsqualität der Produkte mit Gewichtung:
		double midQuality = (wafer.getQuality()
				* Constant.Production.IMPACT_WAFER + cases.getQuality()
				* Constant.Production.IMPACT_CASE) / 100;
		// neue Qualität (nicht mehr als double)
		int newQuality = (int) (midQuality * additionalFactor) * 10;

		// Prüfe ob die neue Qualität durch den Zuschlag zu sehr verändert wurde
		newQuality = (newQuality - midQuality > Constant.Production.MAX_QUALITY_ADDITION) ? (int) (midQuality + Constant.Production.MAX_QUALITY_ADDITION)
				: newQuality;

		// Berechne herstellkosten (ohne Berücksichtigung vom Ausschuss):
		int costs = wafer.getCosts() * Constant.Production.WAFERS_PER_PANEL
				+ cases.getCosts() + m.getPieceCosts();

		// neues Panel erzeugen
		panel = FinishedGood.create(newQuality, costs);
	}

	public void storeProduction(Storage s) throws Exception {
		// Kosten pro Stück neu berechnen (Ausschuss berücksichtigen)
		this.panel = FinishedGood
				.create(panel.getQuality(),
						(int) (((double) panel.getCosts() * quantityToProduce
								+ Constant.Production.WORKING_HOURS_PER_PANEL + Constant.Production.WORKING_HOURS_PER_PANEL
								* quantityToProduce
								* s.getCompany().getHumanResources()
										.getWagesPerHour().getAmount()) / quantityProduced));
		s.store(this.panel, quantityProduced);
	}

}
