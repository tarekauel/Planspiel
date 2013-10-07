package HumanResources;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.Benefit;
import Server.Company;
import Server.GameEngine;
import Server.HumanResources;
import Server.Location;
import Server.MarketData;
import Server.TWage;

public class HRMotivationTest {
	
	private HumanResources h1 = null;
	private HumanResources h2 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
		Benefit.initBenefits();
	}

	@Before
	public void initializeTests() throws Exception {
		h1 = new Company( Location.getLocationByCountry("Test")).getHumanResources();
		h2 = new Company( Location.getLocationByCountry("TestB")).getHumanResources();		
	}

	@Test
	public void testAverageWage() throws Exception{
		h1.setWagePerRound(new TWage(1000, 1, h1.getCompany().getLocation().getWageLevel()));
		h2.setWagePerRound(new TWage(1000, 1, h2.getCompany().getLocation().getWageLevel()));
		TWage expected = new TWage(1500,1, 10000 );
		TWage averageWage = MarketData.getMarketData().getAvereageWage();
		assertEquals(true, expected.equals(averageWage));
	}
	
	@Test
	public void testAverageBenefit() throws Exception {
		h1.bookBenefit("Sport", 10);
		h2.bookBenefit("Sport", 10);
		
		h1.prepareForNewRound(2);
		h2.prepareForNewRound(2);
		
		long bft = MarketData.getMarketData().getAverageBenefit();
		System.out.println(bft);
		
	}
}
