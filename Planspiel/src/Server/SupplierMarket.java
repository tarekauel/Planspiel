package Server;

import java.util.ArrayList;

/**
 * Der Beschaffungsmarkt ist f�r alle Spieler gleich und eins. Die Preise f�r
 * die Rohstoffe �ndern sich nur rundenweise und nicht innerhalb einer Runde.
 * 
 * @author Tarek
 * 
 */
public class SupplierMarket {

	// Singleton Instanz
	private static SupplierMarket		market			= null;

	// -------------------- Verbindung zu anderen Abteilungen
	private ArrayList<Purchase>			listOfPurchase	= new ArrayList<Purchase>();

	// -------------------- Preislisten
	// Preisliste der Wafer
	private ArrayList<TResourcePrice>	waferPricelist	= null;							;

	// Preisliste der Geh�use
	private ArrayList<TResourcePrice>	casePricelist	= null;
	
	// ------------------ Verkaufsinformationen

	/**
	 * Liefert die Instanz auf den Markt zur�ck. (Singleton)
	 * 
	 * @return SupplierMarket: Instanz des Marktes
	 */
	// TODO:
	public static SupplierMarket getMarket() {

		// Pruefe, ob der Markt bereits erstellt worden ist und geben es zur�ck oder erstelle es neu
		return market = ((market == null) ? new SupplierMarket():market);
	}

	/**
	 * Private Konstruktor zur Umsetzung des Singleton-Musters
	 */
	// TODO
	private SupplierMarket() {
		waferPricelist = new ArrayList<TResourcePrice>();

		// Startwerte der Waferpreisliste
		for (int i = 1; i <= 100; i++) {
			waferPricelist.add(new TResourcePrice(i, i * 125));
		}

		// Startwerte der Geh�usepreisliste
		for (int i = 1; i <= 100; i++) {
			casePricelist.add(new TResourcePrice(i, i * 1000));
		}

	}

	/**
	 * Registriert eine Einkaufsabteilung beim Markt
	 * 
	 * @param p
	 *            die zuregistrierende Einkaufsabteilung
	 * @return true: Wenn die Abteilung hinzugef�gt wurde
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

			// Abteilung muss hinzugef�gt werden
			listOfPurchase.add(p);

			return true;

		} else {
			// Abteilung exisitert beretis
			return false;
		}
	}

	/**
	 * Liefert die Preisliste der Wafer zur�ck
	 * 
	 * @return
	 */
	public ArrayList<TResourcePrice> getWaferPricelist() {
		return waferPricelist;
	}

	/**
	 * Liefert die Preisliste der Geh�use zur�ck
	 * 
	 * @return
	 */
	public ArrayList<TResourcePrice> getCasePricelist() {
		return casePricelist;
	}
}
