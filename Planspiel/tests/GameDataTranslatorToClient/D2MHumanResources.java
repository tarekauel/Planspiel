package GameDataTranslatorToClient;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Message.GameDataMessageToClient;
import Server.Company;
import Server.CustomerMarket;
import Server.GameDataTranslator;
import Server.GameEngine;
import Server.Location;
import Server.Resource;

public class D2MHumanResources {
	
	Company c;
	int quantitySold;
	int panelQuality;
	int price;
	int quantityToSell;
	int costs;
	int round;
	
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
		GameEngine.getGameEngine();

		
	}

	@Before
	public void initializeTests() throws Exception {
		c = new Company(Location.getLocationByCountry("USA"),"OTTO");

		
		
		
		
	}

	@Test
	public void getCompleteProductionOrder() throws Exception {
		
		 ArrayList<GameDataMessageToClient> gameDataMessageToClients = GameDataTranslator.getGameDataTranslator().createGameDataMessages();
		 assertEquals(panelQuality,gameDataMessageToClients.get(0).distribution.offers.get(0).quality);
		 assertEquals(quantityToSell,gameDataMessageToClients.get(0).distribution.offers.get(0).quantityToSell);
		 assertEquals(quantityToSell,gameDataMessageToClients.get(0).distribution.offers.get(0).quantitySold);
		 assertEquals(price,gameDataMessageToClients.get(0).distribution.offers.get(0).price);
		 assertEquals(round,gameDataMessageToClients.get(0).distribution.offers.get(0).round);
		 assertEquals(costs,gameDataMessageToClients.get(0).distribution.offers.get(0).costs);
		

	}

	

}
