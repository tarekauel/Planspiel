package StorageTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.Company;
import Server.Location;
import Server.Storage;

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
	public void StorageCreateValid() throws Exception{
		assertEquals(true,(new Storage(c,300)!=null));
	}
	
	@Test
	public void StorageCreateFixCost0() throws Exception{
		new Storage(c,0);
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void StorageCreateFixCostNegative() throws Exception{
		new Storage(c,-10);
	}
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void StorageCreateCompanyNull() throws Exception{
		new Storage(null,10);
	}
	@After
	public void resetTests() {
		c = null;
	}

}
