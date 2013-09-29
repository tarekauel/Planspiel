package Server;

import java.util.ArrayList;

import Logger.Log;

/**
 * Diese Klasse enth�lt alle Statistischen Marktdaten
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
	
	// Liste mit Durchschnittsl�hen
	private ArrayList<TWage> wages = null;
	
	// Runde, in der die L�hne gelten;
	private int round = 0;
	
	
	
	/**
	 * Liefert die Instanz auf den Markt zur�ck. (Singleton)
	 * @return Referenz auf MarketData
	 */
	public static MarketData getMarketData() {
		data = ((data == null) ? new MarketData() : data);
		Log.get(data);
		// Gibt das MarketData Objekt zur�ck und erstellt es gegebenenfalls.
		return data  ;
	}
	
	/**
	 * Private Konstruktor zur Umsetzung des Singleton-Musters
	 */
	private MarketData() {
		Log.method();
		supplierMarket = SupplierMarket.getMarket();
		customerMarket = CustomerMarket.getMarket();
		Log.methodExit();
	}
	
	/**
	 * Liefert den Peak im A-Makrt der n�chsten Runde zur�ck
	 * @return Peak
	 */
	public int requestedQualitAMarketNextRound() {
		Log.get(customerMarket.getAMarketPeak());
		return customerMarket.getAMarketPeak();
	}
	
	/**
	 * Liefert den Peak im C-Makrt der n�chsten Runde zur�ck
	 * @return Peak
	 */
	public int requestedQualitCMarketNextRound() {
		Log.get(customerMarket.getCMarketPeak());
		return customerMarket.getCMarketPeak();
	}
	
	public void addWage(TWage wage) {
		
	}
	
	
	

}
