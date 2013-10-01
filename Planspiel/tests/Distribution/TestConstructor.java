package Distribution;

import org.junit.Before;
import org.junit.Test;

import Server.Company;
import Server.Distribution;
import Server.Location;
import Constant.Constant;

public class TestConstructor {
	Company c;

	@Before
	public void initTests() throws Exception {
		Location.initLocations();
		c = new Company(Location.getLocationByCountry("Deutschland"));

	}

	@Test
	public void createDistribution() throws Exception {
		new Distribution(c, Constant.FIXCOST_DISTRIBUTION);
	}
	@Test (expected = java.lang.IllegalArgumentException.class)
	public void createInvalidCompanyNull() throws Exception {
		new Distribution(null, Constant.FIXCOST_DISTRIBUTION);
	}
	
	@Test (expected = java.lang.IllegalArgumentException.class)
	public void createInvalidCompanyNullFixCost0() throws Exception {
		new Distribution(null, 0);
	}
	
	@Test (expected = java.lang.IllegalArgumentException.class)
	public void createInvalidCompanyNullFixCostNegative() throws Exception {
		new Distribution(null, -500);
	}
	
	@Test
	public void createValidFixCost0() throws Exception {
		new Distribution(c, 0);
	}
	@Test (expected = java.lang.IllegalArgumentException.class)
	public void createInvalidFixCostNegative() throws Exception {
		new Distribution(c, -500);
	}
	
	
	

}
