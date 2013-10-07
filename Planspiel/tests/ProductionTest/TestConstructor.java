package ProductionTest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.*;

public class TestConstructor {
	
	Company c;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
	}

	@Before
	public void initializeTests() throws Exception {
		c = new Company(Location.getLocationByCountry("USA"));
	}

	@Test
	public void createProductionValid() throws Exception {
		new Production(c);
	}
	
	@Test (expected = IllegalArgumentException.class )
	public void createProductionInvalidCompanyNull() throws Exception {
		new Production(null);
	}



}
