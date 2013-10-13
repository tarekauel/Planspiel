package GameDataTranslatorToClient;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Message.GameDataMessageToClient;
import Server.Company;
import Server.GameDataTranslator;
import Server.GameEngine;
import Server.Location;
import Server.Resource;
import Server.SupplierMarket;

public class D2MProduction {
	
	Company c;
	int acceptedSupplierOfferQuality;
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
		GameEngine.getGameEngine();

		
	}

	@Before
	public void initializeTests() throws Exception {
		c = new Company(Location.getLocationByCountry("USA"),"OTTO");
		Resource wafer = new Resource(80, "Wafer", 500);
		Resource cases = new Resource(50, "Gehäuse", 10000);
		c.getProduction().createProductionOrder(wafer, cases, 100)
		
		
		
	}

	@Test
	public void getRightName() throws Exception {
		
		 ArrayList<GameDataMessageToClient> gameDataMessageToClients = GameDataTranslator.getGameDataTranslator().createGameDataMessages();
		 assertEquals(80,gameDataMessageToClients.get(0).purchase.requests.get(0).quality);
		 assertEquals(acceptedSupplierOfferQuality,gameDataMessageToClients.get(0).purchase.requests.get(0).supplierOffers.get(0).quality);

	}

	@After
	public void resetTests() {

	}


}
