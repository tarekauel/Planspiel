package Server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
	 * @throws IOException 
	 */
	
	public static void initLocations() throws IOException {
		FileReader reader = new FileReader("locations.dat");
		BufferedReader buffReader= new BufferedReader(reader);
		String line = buffReader.readLine();
		while(line !=null){
			String [] attributes =line.split(":");
			Location l= new Location(Integer.parseInt(attributes[1]), attributes[0], Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3]));
			listOfLocations.add(l);
			line = buffReader.readLine();
		}
		buffReader.close();
			
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
	 * Sucht nach einer Location über das Land
	 * @param c Country: das zu suchende Land
	 * @return null, falls der Name nicht gefunden wurde
	 */
	public static Location getLocationByCountry(String c){
		
		
		//Suche in der internen Liste:
		
		for(Location o:listOfLocations){
			//Vergleiche (ignoriere Groß/Kleinschreibung)
			if(o.getCountry().toLowerCase().equals(c.toLowerCase())){
				//Strings stimmen überein, also zurückgeben
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
