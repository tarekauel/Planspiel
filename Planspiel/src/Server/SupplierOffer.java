package Server;

import Logger.Log;

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
		Log.newObj(resource);
		if( resource == null)
			throw new IllegalArgumentException("Resource darf nicht null sein!");
		this.resource = resource;
		Log.methodExit();
	}

	public int getOrderedQuantity() {
		Log.get(orderedQuantity);
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
		Log.method(orderedQuantity);
		if (checkOrderedQuantityIsValid(orderedQuantity)) {
			this.orderedQuantity = orderedQuantity;
			Log.set(orderedQuantity);
			return true;
		}
		return false;
	}

	public Resource getResource() {
		Log.get(resource);
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
			Log.get(true);
			return true;

		}
		Log.get(false);
		return false;
	}

}
