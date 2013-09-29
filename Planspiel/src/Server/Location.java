package Server;

import java.util.*;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 17:16
 */

public class Location {

	private int advantage;
	private String country;
	private int purchasePrice;
	private int wageLevel;
	private ArrayList<Location> listOfLocations;

	/**
	 * muss vor Spielstart aufgerufen werden. Erzeugt die verschiedenen
	 * Locations
	 */
	public static void initLocations() {
		new Location(120,"Deutschland",60000000,9164);
		new Location(110,"USA",40000000,8054);
		new Location(90,"China",20000000,6175);
		new Location(80,"Indien",5000000,3092);
		
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
	public Location(int a, String c, int p, int w) {
		this.advantage = a;
		this.country = c;
		this.purchasePrice = p;
		this.wageLevel = w;
		
		listOfLocations.add(this);	
		
	}

	public ArrayList<Location> getListOfLocations() {
		return listOfLocations;
	}
	/**
	 * Sucht nach einer Location über das Land
	 * @param c Country: das zu suchende Land
	 * @return null, falls der Name nicht gefunden wurde
	 */
	public Location getLocationByCountry(String c){
		//Suche in der internen Liste:
		for(Location o:listOfLocations){
			//Vergleiche (ignoriere Groß/Kleinschreibung)
			if(o.getCountry().toLowerCase().equals(c.toLowerCase())){
				//Strings stimmen überein, also zurückgeben
				return o;
			}
		}
		return null;
	}

	public int getAdvantage() {
		return this.advantage;
	}

	public String getCountry() {
		return this.country;
	}

	public int getPurchasePrice() {
		return this.purchasePrice;
	}

	public int getWageLevel() {
		return this.wageLevel;
	}

}
