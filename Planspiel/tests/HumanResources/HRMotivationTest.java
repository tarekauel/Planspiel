package HumanResources;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.Company;
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
	}

	@Before
	public void initializeTests() throws Exception {
		h1 = new Company( Location.getLocationByCountry("Deutschland")).getHumanResources();
		h2 = new Company( Location.getLocationByCountry("Deutschland")).getHumanResources();		
	}

	@Test
	public void makeTests() throws Exception{
		h1.setWagePerRound(new TWage(1000, 1));
		h2.setWagePerRound(new TWage(2000, 1));
		TWage expected = new TWage((int) (1500/Location.getLocationByCountry("Deutschland").getWageLevel()),1);
		
		assertEquals(expected, MarketData.getMarketData().getAvereageWage());
	}
}
