import Server.Company;
import Server.GameEngine;
import Server.*;



public class GameTest {

	public static void main(String[] args) throws Exception {
		
		GameEngine g = GameEngine.getGameEngine();
		
		Location.initLocations();
	
		Company c = new Company(Location.getLocationByCountry("Deutschland"));
		
		Purchase p = c.getPurchase();
		p.createRequest( new Resource(50, "Wafer", 0));
		
		
	}
}
