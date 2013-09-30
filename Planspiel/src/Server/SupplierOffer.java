package Server;

/**
 * 
 * @author D059270 Stellt eine von max. 3 Angeboten zu einer Request dar.
 */

public class SupplierOffer {

	private int orderedQuantity;
	private Resource resource;

	/**
	 * Erstellt ein SupllierOffer (Angebot vom Beschaffungsmarkt an den Spieler)
	 * @param resource Produkt, dass Angeboten wird
	 * @throws IllegalArgumentException Resource darf nicht null sein
	 */
	public SupplierOffer(Resource resource) throws IllegalArgumentException {		
		if( resource == null)
			throw new IllegalArgumentException("Resource darf nicht null sein!");
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

	public Resource getResource() {
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
