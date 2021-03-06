package GameDataTranslatorToClient;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Message.GameDataMessageFromClient;
import Message.GameDataMessageToClient;
import Message.GameDataMessageFromClient.DistributionFromClient;
import Message.GameDataMessageFromClient.HumanResourcesFromClient;
import Message.GameDataMessageFromClient.ProductionFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient;
import Message.GameDataMessageFromClient.DistributionFromClient.OfferFromClient;
import Message.GameDataMessageFromClient.HumanResourcesFromClient.BenefitBookingFromClient;
import Message.GameDataMessageFromClient.ProductionFromClient.ProductionOrderFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient.AcceptedSupplierOfferFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Server.Benefit;
import Server.Company;
import Server.CustomerMarket;
import Server.GameDataTranslator;
import Server.GameEngine;
import Server.Location;
import Server.MarketData;
import Server.Resource;

public class D2MHumanResources {
	
	Company c;
	PurchaseFromClient purchase;
	DistributionFromClient distribution;
	ProductionFromClient production;
	HumanResourcesFromClient  hr;
	String name;
	int costsPerRound;
	ArrayList<GameDataMessageFromClient> gameDataMessages = new ArrayList<GameDataMessageFromClient>();
	
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
		Benefit.initBenefits();
		GameEngine.getGameEngine();
		

		
	}

	@Before
	public void initializeTests() throws Exception {
		c = new Company(Location.getLocationByCountry("USA"),"OTTO");
		
		//Benefits werden gebucht durch 
		ArrayList<RequestFromClient> requests = new ArrayList<RequestFromClient>();
		ArrayList<AcceptedSupplierOfferFromClient> acceptedSupplierOffers = new ArrayList<AcceptedSupplierOfferFromClient>();
		ArrayList<ProductionOrderFromClient> orders = new ArrayList<ProductionOrderFromClient>();
		ArrayList<OfferFromClient> offers = new ArrayList<OfferFromClient>();
		ArrayList<BenefitBookingFromClient> benefits = new ArrayList<BenefitBookingFromClient>();
		
		BenefitBookingFromClient benefit = new BenefitBookingFromClient("Sport", 5);
		benefits.add(benefit);
		
		//weitere Erstellung der Message
		 purchase = new PurchaseFromClient(requests, acceptedSupplierOffers);
		 production = new ProductionFromClient(orders);
		 distribution = new DistributionFromClient(offers);
		 hr = new HumanResourcesFromClient(benefits);
		 
		GameDataMessageFromClient gameDataMessage = new GameDataMessageFromClient(c.getName(), purchase, production, distribution, false, hr, 7, false);
		gameDataMessages.add(gameDataMessage);
		 
		 
		
	}

	@Test
	public void checkMessageToClientHR() throws Exception {
		
		 ArrayList<GameDataMessageToClient> gameDataMessageToClients = GameEngine.getGameEngine().startNextRound(gameDataMessages);
		 //get Data from Objects
		 name = c.getHumanResources().getBenefitBooking().get(0).getBenefit().getName();
		 costsPerRound = c.getHumanResources().getBenefitBooking().get(0).getBenefit().getCostsPerRound();
		 assertEquals(name,gameDataMessageToClients.get(0).humanResources.benefits.get(0).name);
		 assertEquals(5,gameDataMessageToClients.get(0).humanResources.benefits.get(0).remainingRounds);
		 assertEquals(costsPerRound,gameDataMessageToClients.get(0).humanResources.benefits.get(0).costsPerRound);
		 assertEquals(Benefit.getBookableBenefits().size(),gameDataMessageToClients.get(0).humanResources.possibleBenefits.size());
		 assertEquals(c.getHumanResources().getHistoryOfMotivation().size(),gameDataMessageToClients.get(0).humanResources.historyMotivation.size());
		 assertEquals( MarketData.getMarketData().getAvereageWage().getAmount()* (c.getLocation().getWageLevel() / 10000), gameDataMessageToClients.get(0).humanResources.averageWage);
		 assertEquals(c.getHumanResources().getWagesPerHour().getAmount(), gameDataMessageToClients.get(0).humanResources.myWage);
		 assertEquals(c.getHumanResources().getCountEmployees(), gameDataMessageToClients.get(0).humanResources.countEmployees);
		 assertEquals(c.getHumanResources().getWagesSum(), gameDataMessageToClients.get(0).humanResources.wageCosts);
		 
		

	}

	

}
