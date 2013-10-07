package ProductionTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.*;

public class TestCreateProductionOrder {
	
	Company c;
	Resource wafer;
	Resource cases;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
	}

	@Before
	public void initializeTests() throws Exception {
		c= new Company(Location.getLocationByCountry("USA"));
		 wafer = new Resource(80, "Wafer", 10000);
		 cases = new Resource(50, "Gehäuse", 10000);
		
		
	}

	@Test
	public void createProductionOrderValid() {
		assertEquals(true,c.getProduction().createProductionOrder(wafer, cases, 100));
	}
	
	@Test (expected = IllegalArgumentException.class )
	public void createProductionOrderInvalidWafer() {
		assertEquals(true,c.getProduction().createProductionOrder(null, cases, 100));
	}
	
	@Test (expected = IllegalArgumentException.class )
	public void createProductionOrderInvalidCases() {
		assertEquals(true,c.getProduction().createProductionOrder(wafer, null, 100));
	}
	
	@Test (expected = IllegalArgumentException.class )
	public void createProductionOrderQuantityEQZero() {
		assertEquals(true,c.getProduction().createProductionOrder(wafer, cases, 0));
	}
	@Test (expected = IllegalArgumentException.class )
	public void createProductionOrderQuantityLowerZero() {
		assertEquals(true,c.getProduction().createProductionOrder(wafer, cases, -1));
	}


}
