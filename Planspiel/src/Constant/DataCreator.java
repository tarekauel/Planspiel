package Constant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Server.Location;

public class DataCreator {

	

	public static void createLocationFile() {
		ArrayList<Location> listOfLocations = new ArrayList<Location>();
		listOfLocations.add(new Location(120, "Deutschland", 60000000, 9164));
		listOfLocations.add(new Location(110, "USA", 40000000, 8054));
		listOfLocations.add(new Location(90, "China", 20000000, 6175));
		listOfLocations.add(new Location(80, "Indien", 5000000, 3092));

		PrintWriter pw = new PrintWriter("locations.dat");
		for (Location location : listOfLocations) {
			pw.println();
			//Todo: ich mach noch weiter
		}
		
	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			DataCreator.createLocationFile();
	}

}
