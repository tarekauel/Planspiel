package Server;

/**
 * 
 * @author D059270 Stellt eine von max. 3 Angeboten zu einer Request dar.
 */

public class SupplierOffer {

	private int orderedQuantity;
	private Resource resource;

	/**
	 * Fachlicher Konstruktor: Liefert eine SupplierOffer zurück wenn die
	 * Resource ungleich null ist.
	 * 
	 * @param resorce
	 * @return
	 */
	public SupplierOffer create(Resource resorce) {
		if (resource != null) {
			return new SupplierOffer(resorce);
		}
		return null;
	}

	/**
	 * Privater Konstrktor: Setzt das Resource Attribut
	 * 
	 * @param resource
	 */
	private SupplierOffer(Resource resource) {
		this.resource = resource;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	/**
	 * Setzt die bestellte Menge des Spielers. Wird erst in der Runde nach der
	 * Erstellng des SupplierOffers gesetzt.
	 * 
	 * @param orderedQuantity
	 * @return
	 */
	public Boolean setOrderedQuantity(int orderedQuantity) {
		if (checkOrderedQuantityIsValid(orderedQuantity)) {
			this.orderedQuantity = orderedQuantity;
			return true;
		}
		return false;
	}

	public Resource getResorce() {
		return resource;
	}

	/**
	 * Prüft ob die Quantity positiv ist.
	 * 
	 * @param orderedQuantity
	 * @return
	 */
	private static Boolean checkOrderedQuantityIsValid(int orderedQuantity) {
		if (orderedQuantity >= 0) {
			return true;

		}
		return false;
	}

}
