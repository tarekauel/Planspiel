package Server;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Der Beschaffungsmarkt ist für alle Spieler gleich und eins. Die Preise für
 * die Rohstoffe ändern sich nur rundenweise und nicht innerhalb einer Runde.
 * 
 * @author Tarek
 * 
 */
public class SupplierMarket {

	// Singleton Instanz
	private static SupplierMarket		market			= null;

	// -------------------- Verbindung zu anderen Abteilungen
	private ArrayList<Purchase>			listOfPurchase	= new ArrayList<Purchase>();
	
	// ------------- Liste aller Angebote
	private ArrayList<Request> listOfRequest = null;

	// -------------------- Preislisten
	// Preisliste der Wafer
	private TreeSet<TResourcePrice>	waferPricelist	= null;							;

	// Preisliste der Gehäuse
	private TreeSet<TResourcePrice>	casePricelist	= null;
	
	// ------------------ Verkaufsinformationen

	/**
	 * Liefert die Instanz auf den Markt zurück. (Singleton)
	 * 
	 * @return SupplierMarket: Instanz des Marktes
	 */
	// TODO:
	public static SupplierMarket getMarket() {

		// Pruefe, ob der Markt bereits erstellt worden ist und geben es zurück oder erstelle es neu
		return market = ((market == null) ? new SupplierMarket():market);
	}

	/**
	 * Private Konstruktor zur Umsetzung des Singleton-Musters
	 */
	// TODO
	private SupplierMarket() {
		waferPricelist = new TreeSet<TResourcePrice>();
		casePricelist = new TreeSet<TResourcePrice>();

		// Startwerte der Waferpreisliste
		for (int i = 1; i <= 100; i++) {
			waferPricelist.add(new TResourcePrice(i, i * 125));
		}

		// Startwerte der Gehäusepreisliste
		for (int i = 1; i <= 100; i++) {
			casePricelist.add(new TResourcePrice(i, i * 1000));
		}
	}

	/**
	 * Registriert eine Einkaufsabteilung beim Markt
	 * 
	 * @param p
	 *            die zuregistrierende Einkaufsabteilung
	 * @return true: Wenn die Abteilung hinzugefügt wurde
	 *         false: Wenn die Abteilung bereits in der Liste existert
	 */
	public boolean addPurchase(Purchase p) {
		// Null-Referenzen abfangen
		if (p == null) {
			throw new NullPointerException("Pruchase-Referenz darf nicht null sein!");
		}

		// Pruefe ob Purchase noch nicht in der Lsite steht
		if (listOfPurchase.indexOf(p) == -1) { // TODO: indexOf geht auf
			// equals (muss ggf. noch
			// implementiert werden)

			// Abteilung muss hinzugefügt werden
			listOfPurchase.add(p);

			return true;

		} else {
			// Abteilung exisitert beretis
			return false;
		}
	}
	
	public void handleRequest() throws Exception{
		
		listOfRequest = new ArrayList<Request>();
		
		for(Purchase p:listOfPurchase) {
			for(Request r:p.getListOfLatesRequest()) {
				listOfRequest.add(r);
			}
		}
		
		for(Request r:listOfRequest) {
			int reqQuality = r.getRequestedResource().getQuality();
			String reqName =  r.getRequestedResource().getName();
			//TODO: Später durch Zufallslogik ersetzen. Daran denken, dass nicht Quality von -1 abegefragt wird.
			if( reqName.equals("Gehäuse")) {
				r.addSupplierOffer(new SupplierOffer(new Resource( --reqQuality, reqName, casePricelist.ceiling(new TResourcePrice(reqQuality, 1)).getPrice())));
				r.addSupplierOffer(new SupplierOffer(new Resource( ++reqQuality, reqName, casePricelist.ceiling(new TResourcePrice(reqQuality, 1)).getPrice())));
				r.addSupplierOffer(new SupplierOffer(new Resource( ++reqQuality, reqName, casePricelist.ceiling(new TResourcePrice(reqQuality, 1)).getPrice())));
			} else {
				r.addSupplierOffer(new SupplierOffer(new Resource( --reqQuality, reqName, waferPricelist.ceiling(new TResourcePrice(reqQuality, 1)).getPrice())));
				r.addSupplierOffer(new SupplierOffer(new Resource( ++reqQuality, reqName, waferPricelist.ceiling(new TResourcePrice(reqQuality, 1)).getPrice())));
				r.addSupplierOffer(new SupplierOffer(new Resource( ++reqQuality, reqName, waferPricelist.ceiling(new TResourcePrice(reqQuality, 1)).getPrice())));				
			}
			
		}
	}
	
	public void recalculatePrices() {
		ArrayList<SupplierOffer> acceptedSupplierOffer = new ArrayList<SupplierOffer>();
		for( Purchase p:listOfPurchase) {
			for(SupplierOffer s:p.getListOfAcceptedSupplierOffer() ) {
				acceptedSupplierOffer.add(s);
			}
		}
		
		// TODO: recalculate price lists
	}

	/**
	 * Liefert die Preisliste der Wafer zurück
	 * 
	 * @return
	 */
	public TreeSet<TResourcePrice> getWaferPricelist() {
		return waferPricelist;
	}

	/**
	 * Liefert die Preisliste der Gehäuse zurück
	 * 
	 * @return
	 */
	public TreeSet<TResourcePrice> getCasePricelist() {
		return casePricelist;
	}
}
