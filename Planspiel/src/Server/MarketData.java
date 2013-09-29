package Server;

import java.util.ArrayList;

/**
 * Diese Klasse enthält alle Statistischen Marktdaten
 * @author Tarek
 *
 */
public class MarketData {
	
	// Singleton-Referenz
	private static MarketData data = null;
	
	// Referenz auf den Beschaffungsmarkt
	private SupplierMarket supplierMarket;
	
	// Referenz auf den Absatzmarkt
	private CustomerMarket customerMarket;
	
	// Liste mit Durchschnittslöhen
	private ArrayList<TWage> wages = null;
	
	// Runde, in der die Löhne gelten;
	private int round = 0;
	
	
	
	/**
	 * Liefert die Instanz auf den Markt zurück. (Singleton)
	 * @return Referenz auf MarketData
	 */
	public static MarketData getMarketData() {
		
		// Gibt das MarketData Objekt zurück und erstellt es gegebenenfalls.
		return data = ((data == null) ? new MarketData() : data);
	}
	
	/**
	 * Private Konstruktor zur Umsetzung des Singleton-Musters
	 */
	private MarketData() {
		supplierMarket = SupplierMarket.getMarket();
		customerMarket = CustomerMarket.getMarket();
	}
	
	/**
	 * Liefert den Peak im A-Makrt der nächsten Runde zurück
	 * @return Peak
	 */
	public int requestedQualitAMarketNextRound() {
		return customerMarket.getAMarketPeak();
	}
	
	/**
	 * Liefert den Peak im C-Makrt der nächsten Runde zurück
	 * @return Peak
	 */
	public int requestedQualitCMarketNextRound() {
		return customerMarket.getCMarketPeak();
	}
	
	public void addWage(TWage wage) {
		
	}
	
	
	

}
