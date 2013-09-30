package StorageTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.Company;
import Server.Location;
import Server.Storage;

import Constant.Constant;

public class TestConstructor {
Company c;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Logger deaktivieren

		Constant.LOG_GET = false;
		Constant.LOG_INFO = false;
		Constant.LOG_METHOD_NO_PARAM = false;
		Constant.LOG_METHOD_1_PARAM = false;
		Constant.LOG_METHOD_N_PARAM = false;
		Constant.LOG_METHOD_EXIT = false;
		Constant.LOG_NEWOBJ_1_PARAM = false;
		Constant.LOG_NEWOBJ_N_PARAM = false;
		Constant.LOG_SET = false;
		Constant.LOG_VERBOSE = false;
		Constant.LOG_WARNING = false;
	}

	@Before
	public void initializeTests() throws Exception {
		Location.initLocations();
		
		c = new Company(Location.getLocationByCountry("USA"));
	}

	@Test
	public void StorageCreateValid() throws Exception{
		assertEquals(true,(new Storage(c,300)!=null));
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
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
