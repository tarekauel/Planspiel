package Server;

import java.util.ArrayList;

import Logger.Log;

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
	
	// Liste der Personalabteilungen
	private ArrayList<HumanResources> listOfHr = new ArrayList<HumanResources>();
	
	
	
	/**
	 * Liefert die Instanz auf den Markt zurück. (Singleton)
	 * @return Referenz auf MarketData
	 */
	public static MarketData getMarketData() {
		data = ((data == null) ? new MarketData() : data);
		Log.get(data);
		// Gibt das MarketData Objekt zurück und erstellt es gegebenenfalls.
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
	 * Liefert den Peak im A-Makrt der nächsten Runde zurück
	 * @return Peak
	 */
	public int requestedQualitAMarketNextRound() {
		Log.get(customerMarket.getAMarketPeak());
		return customerMarket.getAMarketPeak();
	}
	
	/**
	 * Liefert den Peak im C-Makrt der nächsten Runde zurück
	 * @return Peak
	 */
	public int requestedQualitCMarketNextRound() {
		Log.get(customerMarket.getCMarketPeak());
		return customerMarket.getCMarketPeak();
	}
	
	/**
	 * Fuegt eine Personalabteilun zu den Makrtdaten hinzu, um den Durchschnittslohn zu berechnen
	 * @param hr Personalabteilung, die hinzugefügt werden soll
	 */
	public void addHR( HumanResources hr ) {
		if( hr == null)
			throw new IllegalArgumentException("HR-Referenz darf nicht null sein!");
		this.listOfHr.add(hr);
	}
	
	/**
	 * Liefert den Durchschnittslohn aller Abteilungen auf Niveau 100 umgerechnet zurück
	 * @return Durchschnittslohn auf Niveau 100
	 * @throws Exception
	 */
	public TWage getAvereageWage() throws Exception {
		// Summe aller WageAmounts (auf das Niveau 100 gerechnet)
		long sumWageAmounts = 0;
		
		// Anzahl der Abteilungen, die in der Summe kummuliert worden sind
		int numOfDepts = 0;
		
		// Liste aller HRs durchgehen
		for(HumanResources hr : listOfHr) {
			sumWageAmounts += (long) hr.getWagesPerHour().getAmount() / (long) hr.getWagesPerHour().getWageLevel();
			++numOfDepts;			
		}
		
		// Den Durchschnittslohn der HRs auf Niveau 100 umgerechnet zurueckgeben
		return new TWage((int) (sumWageAmounts / numOfDepts), 100);
	}
	
	
	

}
