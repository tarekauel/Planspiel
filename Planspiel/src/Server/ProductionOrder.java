/**
 * 
 */
package Server;

import Logger.Log;
import Constant.Constant;

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
	private int id = 0;
	// Speichert die verwendeten Ressourcen
	private Resource wafer = null;
	private Resource cases = null;

	// Speichert hinterher das erzugte Panel:
	private FinishedGood panel = null;
	// Herzustellende Menge laut Auftrag
	private int quantityToProduce = 0;
	// tats�chlich hergestellte Menge:
	private int quantityProduced = 0;

	// Paramerter f�r die Anzahl der Stunden pro Panel
	private int workingHoursPerPanel = 5;

	// Kosten pro Order
	private int costsPerOrder = 1000;

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
		Log.newObj(new Object[] { wafer, cases, quantity });
		this.id = counter;
		// TODO: sicherheitsabfragen
		this.wafer = wafer;
		this.cases = cases;
		this.quantityToProduce = quantity;
		counter++;
		Log.methodExit();
	}

	/**
	 * abgeleitetes attribut
	 * 
	 * @return int Ausschuss
	 */
	public int getWaste() {
		Log.get(quantityToProduce - quantityProduced);
		return quantityToProduce - quantityProduced;

	}

	/**
	 * 
	 * @return gibt den verwendeten Wafer wieder
	 */
	public Resource getWafer() {
		Log.get(wafer);
		return this.wafer;
	}

	/**
	 * 
	 * @return gibt das verwendete Geh�use zur�ck
	 */
	public Resource getCase() {
		Log.get(cases);
		return this.cases;
	}

	/**
	 * 
	 * @return gibt das Fertige Erzeugnis zur�ck
	 */
	public FinishedGood getPanel() {
		Log.get(panel);
		return this.panel;

	}

	/**
	 * Gibt die Produzierte Menge an
	 * 
	 * @return int produzierte Menge
	 */
	public int getProduced() {
		Log.get(quantityProduced);
		return quantityProduced;
	}

	/**
	 * Erh�ht die Produzierte Menge um 1
	 * 
	 * @return int produzierte Menge
	 */
	public void increaseProduced() {
		if (quantityProduced < quantityToProduce) {
			quantityProduced++;
			Log.set(quantityProduced);

		}
	}

	/**
	 * Gibt die in auftragegebene Menge an
	 * 
	 * @return int Anzahl aus dem Konstruktor
	 */
	public int getRequested() {
		Log.get(quantityToProduce);
		
		return quantityToProduce;

	}

	/**
	 * Returns the global ID
	 * 
	 * @return
	 */
	public int getID() {
		Log.get(id);
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
	public void produce(int Zuschlag, Storage s, Machinery m) throws Exception {
		Log.method(new Object[] { Zuschlag, s, m });
		quantityProduced++;

		// Pr�fe ob bereits produziert wurde:
		if (panel != null) {
			// panel bereits gesetzt, also muss das nicht mehr berechnet werden.
			return;
		}

		// Es wird in doubles gerechnet:
		double additionalFactor = Zuschlag / 100;
		// durchschnittsqualit�t der Produkte mit Gewichtung:
		double midQuality = (wafer.getQuality()
				* Constant.PRODUCTION_IMPACT_WAFER + cases.getQuality()
				* Constant.PRODUCTION_IMPACT_CASE) / 100;
		// neue Qualit�t (nicht mehr als double)
		int newQuality = (int) (midQuality * additionalFactor) * 10;

		// Pr�fe ob die neue Qualit�t durch den Zuschlag zu sehr ver�ndert wurde
		newQuality = (newQuality - midQuality > Constant.PRODUCTION_MAX_QUALITY_ADDITION) ? (int) (midQuality + Constant.PRODUCTION_MAX_QUALITY_ADDITION)
				: newQuality;

		// Berechne herstellkosten:
		int costs = wafer.getCosts() * Constant.PRODUCTION_WAFERS_PER_PANEL
				+ cases.getCosts() + m.getPieceCosts();

		// neues Panel erzeugen
		panel = FinishedGood.create(newQuality, costs);
		Log.methodExit();
	}

	public void storeProduction(Storage s) throws Exception {
		Log.method(s);
		// Kosten pro St�ck neu berechnen (Ausschuss ber�cksichtigen)
		this.panel = FinishedGood.create(panel.getQuality(),
				(int) (((double) panel.getCosts() * quantityToProduce
						+ costsPerOrder + workingHoursPerPanel
						* quantityToProduce
						* s.getCompany().getHumanResources().getWagesPerHour()
								.getAmount()) / quantityProduced));
		s.store(this.panel, quantityProduced);
		Log.methodExit();
	}

}
