import java.util.ArrayList;

import Server.Company;
import Server.CustomerMarket;
import Server.Distribution;
import Server.GameEngine;
import Server.Location;
import Server.Production;
import Server.Purchase;
import Server.Request;
import Server.Resource;
import Server.Storage;
import Server.SupplierMarket;



public class GameTest {

	public static void main(String[] args) throws Exception {
		
		GameEngine g = GameEngine.getGameEngine();
		
		Location.initLocations();
	
		Company c = new Company(Location.getLocationByCountry("Deutschland"));
		
		Purchase p = c.getPurchase();
		Production pro = c.getProduction();
		Storage s = c.getStorage();
		Distribution d = c.getDistribution();
		
		p.createRequest( new Resource(50, "Wafer", 0));
		p.createRequest( new Resource(50, "Gehäuse", 0));
		
		SupplierMarket.getMarket().handleRequest();
		
		ArrayList<Request> listReq = p.getListOfLatesRequest();
		p.acceptSupplierOffer(listReq.get(0).getSupplierOffers()[0], 54*100); // 1000 Wafer kaufen
		p.acceptSupplierOffer(listReq.get(1).getSupplierOffers()[0], 100); // 1000 Gehäuse kaufen
		
		ArrayList<Resource> store = s.getAllResources();
		pro.createProductionOrder(store.get(0), store.get(1), 100);
		
		pro.produce();
		
		d.createOffer(s.getAllFinishedGoods().get(0).getQuality(), s.getAllStorageElements().get(2).getQuantity(), 100);
		
		CustomerMarket.getMarket().handleAllOffers();
		
		int i =1;		
		
	}
}
