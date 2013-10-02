package Distribution;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.Company;
import Server.Distribution;
import Server.Location;

public class TestPrepareForNewRound {
	Company c;
	Distribution d;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();

	}

	@Before
	public void initializeTests() throws Exception {
		c = new Company(Location.getLocationByCountry("USA"));
		d = new Distribution(c);
		
		d.createOffer(50, 100, 050);
		
	}

	@Test 
	public void valid() {
		d.prepareForNewRound(5);
		assertEquals(0,d.getListOfOffers().size());
		
	}

	

}
