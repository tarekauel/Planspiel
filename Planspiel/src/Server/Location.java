package Server;

import java.util.ArrayList;

import Logger.Log;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 17:16
 */

public class Location {

	private int advantage;
	private String country;
	private int purchasePrice;
	private int wageLevel;
	private static ArrayList<Location> listOfLocations = new ArrayList<Location>();

	/**
	 * muss vor Spielstart aufgerufen werden. Erzeugt die verschiedenen
	 * Locations
	 */
	public static void initLocations() {
		Log.method();
		new Location(120,"Deutschland",60000000,9164);
		new Location(110,"USA",40000000,8054);
		new Location(90,"China",20000000,6175);
		new Location(80,"Indien",5000000,3092);
		Log.methodExit();
	}

	/**
	 * Erzeugt eine neue Location
	 * 
	 * @param a
	 *            Advantage/ Standortvorteil
	 * @param c
	 *            Country / Produktionsstandort
	 * @param p
	 *            Price /Anschaffungskosten des Standorts
	 * @param w
	 *            WageLevel / Lohn niveau
	 */
	private Location(int a, String c, int p, int w) {
		Log.newObj(new Object[]{c,p,w});
		this.advantage = a;
		this.country = c;
		this.purchasePrice = p;
		this.wageLevel = w;
		
		listOfLocations.add(this);	
		
	}

	public static ArrayList<Location> getListOfLocations() {
		Log.get(listOfLocations);
		return listOfLocations;
	}
	/**
	 * Sucht nach einer Location �ber das Land
	 * @param c Country: das zu suchende Land
	 * @return null, falls der Name nicht gefunden wurde
	 */
	public static Location getLocationByCountry(String c){
		
		
		//Suche in der internen Liste:
		
		for(Location o:listOfLocations){
			//Vergleiche (ignoriere Gro�/Kleinschreibung)
			if(o.getCountry().toLowerCase().equals(c.toLowerCase())){
				//Strings stimmen �berein, also zur�ckgeben
				Log.get(o);
				return o;
			}
		}
		return null;
	}

	public int getAdvantage() {
		Log.get(advantage);
		return this.advantage;
	}

	public String getCountry() {
		Log.get(country);
		return this.country;
	}

	public int getPurchasePrice() {
		Log.get(purchasePrice);
		return this.purchasePrice;
	}

	public int getWageLevel() {
		Log.get(wageLevel) ;
		return this.wageLevel;
	}
	
	
	@Override
	public String toString(){
		
		return this.country;
	}

}