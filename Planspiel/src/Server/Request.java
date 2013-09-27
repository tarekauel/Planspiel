package Server;

import java.util.ArrayList;

/**
 * 
 * @author D059270 Stellt eine Nachfrage nach einem Rohstoff in einer bestimmten
 *         Qualität dar. Angebote hierzu können über getSpplierOffers abgerufen
 *         werden.
 */
public class Request {

	private Resource resorceReqested;
	private ArrayList<SupplierOffer> supplierOffers = new ArrayList<SupplierOffer>();

	/**
	 * Fachlicher Konstruktor: Prüft ob die Resource ungleich null ist und
	 * liefert nur dann den Request zurück. Sonst null.
	 * 
	 * @param resorce
	 * @return
	 */
	public static Request create(Resource resorce) {
		if (resorce != null) {
			Request request = new Request(resorce);
			return request;
		}
		return null;

	}

	/**
	 * Privater Konstruktor: Setzt das Attribut resorceRequested.
	 * 
	 * @param resorce
	 */
	private Request(Resource resorce) {
		this.resorceReqested = resorce;
	}

	/**
	 * Liefert alle Angebote (max. 3) zur gestellten Anfrage zurück. Der Spieler
	 * kann diese annehmen.
	 * 
	 * @return
	 */
	public SupplierOffer[] getSpplierOffers() {
		return supplierOffers.toArray(new SupplierOffer[supplierOffers.size()]);
	}

	/**
	 * Fügt ein Angebot zur gestellten Anfrage hinzu. Es können max. 3 Angebote
	 * hinzgefügt werden. Das Angebot mss vom gleichen Rohstofftyp sein.
	 * 
	 * @param supplierOffer
	 * @return
	 */
	public Boolean addSupplierOffer(SupplierOffer supplierOffer) {
		String nameOfSupplierProdct = supplierOffer.getResource().getName();
		String nameOfResourceProdct = resorceReqested.getName();
		if (supplierOffers.size() < 3
				&& nameOfResourceProdct.equals(nameOfSupplierProdct)) {
			supplierOffers.add(supplierOffer);
			return true;
		}
		return false;
	}

	/**
	 * Liefert die angefrage Resource zurück.
	 * 
	 * @return
	 */
	public Resource getRequestedResource() {
		return resorceReqested;
	}

}
