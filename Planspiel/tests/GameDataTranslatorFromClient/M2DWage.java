package GameDataTranslatorFromClient;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Message.GameDataMessageFromClient;
import Message.GameDataMessageFromClient.DistributionFromClient;
import Message.GameDataMessageFromClient.HumanResourcesFromClient;
import Message.GameDataMessageFromClient.ProductionFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient;
import Message.GameDataMessageFromClient.DistributionFromClient.OfferFromClient;
import Message.GameDataMessageFromClient.HumanResourcesFromClient.BenefitBookingFromClient;
import Message.GameDataMessageFromClient.ProductionFromClient.ProductionOrderFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient.AcceptedSupplierOfferFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Server.Company;
import Server.GameDataTranslator;
import Server.GameEngine;
import Server.Location;

public class M2DWage {
	
	static Company c;
	
	PurchaseFromClient purchase;
	DistributionFromClient distribution;
	ProductionFromClient production;
	HumanResourcesFromClient  hr;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
		GameEngine.getGameEngine();
		c = new Company(Location.getLocationByCountry("USA"),"OTTO");
		
		
	}

	@Before
	public void initializeTests() throws Exception {

	
		//werden teilweise absichtlich leer gelassen da in anderem Test geprueft
		ArrayList<RequestFromClient> requests = new ArrayList<RequestFromClient>();
		ArrayList<AcceptedSupplierOfferFromClient> acceptedSupplierOffers = new ArrayList<AcceptedSupplierOfferFromClient>();
		ArrayList<ProductionOrderFromClient> orders = new ArrayList<ProductionOrderFromClient>();
		ArrayList<OfferFromClient> offers = new ArrayList<OfferFromClient>();
		ArrayList<BenefitBookingFromClient> benefits = new ArrayList<BenefitBookingFromClient>();
		
		//weitere Erstellung der Message
		 purchase = new PurchaseFromClient(requests, acceptedSupplierOffers);
		 production = new ProductionFromClient(orders);
		 distribution = new DistributionFromClient(offers);
		 hr = new HumanResourcesFromClient(benefits);
		 

		

		
	}
	@Test
	public void makeTests() throws Exception {
		int wageBefore = c.getHumanResources().getWagesPerHour().getAmount();
		int wage = 10;
		ArrayList<GameDataMessageFromClient> gameDataMessages = new ArrayList<GameDataMessageFromClient>();
		GameDataMessageFromClient gameDataMessage = new GameDataMessageFromClient(c.getName(), purchase, production, distribution, false, hr, wage,false);
		gameDataMessages.add(gameDataMessage);
		GameDataTranslator.getGameDataTranslator().convertGameDataMessage2Objects(gameDataMessages);
		int wageAfter = c.getHumanResources().getWagesPerHour().getAmount();
		System.out.println(wageBefore);
		System.out.println(wageAfter);
		assertEquals(true, wageBefore!=wageAfter);
		
	}

	@After
	public void resetTests() {

	}


}
