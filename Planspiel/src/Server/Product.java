package Server;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

/**
 * 
 * @author D059270 Die Klasse Product stellt die Superklasse der Produkttypen
 *         Ressorce und FinishedGood dar. Name, Kosten und Qalität werden von
 *         Product geführt.
 */
public abstract class Product {

	private String name; // Wafer/Gehäuse/Panel
	private int costs;
	private int quality;
	private int storageCostsPerRond;

	/**
	 * Erstellt ein neues Produkt, wenn die Qualität (Interval ]0;100] und die Kosten (>0)
	 * valide sind. Ansonsten wird eine Exception geworfen.
	 * 
	 * @param quality
	 * @param name
	 * @param costs
	 * @throws
	 */
	public Product(int quality, String name, int costs) throws Exception {
		if (checkCostsAreValid(costs) && checkQualityIsValid(quality)) {
			this.quality = quality;
			this.name = name;
			this.costs = costs;
			return;
		}
		throw new Exception("Not valid!");
	}

	public String getName() {
		return name;
	}

	public int getCosts() {
		return costs;
	}

	public int getQuality() {
		return quality;
	}

	public int getStorageCostsPerRond() {
		return storageCostsPerRond;
	}

	/**
	 * Wird einmal in jeder Runde aufgerfen, um die Kosten des Produktes um sein
	 * Lagerkosten der Runde zu erhöhen.
	 */
	public void calculateNewCosts() {
		costs += storageCostsPerRond;
	}

	/**
	 * Setzt die Kosten des Produktes, falls diese valide sind.
	 * 
	 * @param costs
	 * @return
	 */
	private Boolean setCosts(int costs) { //Brachen wir diese Methode???
		if (checkCostsAreValid(costs)) {
			this.costs = costs;
			return true;
		}
		return false; // nicht gesetzt

	}

	/**
	 * Prüft ob die Qualität zwischen 0 und einschließlich 100 liegt.
	 * 
	 * @param quality
	 * @return
	 */
	private static Boolean checkQualityIsValid(int quality) {
		if (quality > 0 && quality <= 100) {
			return true;
		}
		return false;
	}

	/**
	 * Prüft ob die Kosten positiv sind. *
	 * 
	 * @param costs
	 * @return
	 */
	private static Boolean checkCostsAreValid(int costs) {
		if (costs > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Prüft ob Produkte gleich sind. Zm Vergleich werden die Qualität und der
	 * Name herangezogen.
	 * 
	 * @param product
	 * @return
	 */
	public Boolean equals(Product product) {
		if(product==null){
			return false;
		}
		if (product.name.equals(this.name) && product.quality == this.quality) {
			return true;
		}
		return false;
	}
}
