package ProductionTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.*;

public class TestProduce {
	
	Company c;
	Storage st;
	Resource wafer;
	Resource cases;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
	}

	@Before
	public void initializeTests() throws Exception {
		c = new Company(Location.getLocationByCountry("USA"));
		st = c.getStorage();
		wafer = new Resource(80, "Wafer", 100000);
		c.getStorage().store(wafer, 200);
		cases = new Resource(80, "Gehäuse", 100000);
		c.getStorage().store(cases, 200);
	}

	@Test
	public void produceValid() throws Exception {
		c.getProduction().createProductionOrder(wafer, cases, 5);
		c.getProduction().produce();
		assertEquals(0,st.getAllFinishedGoods().size());
	}
	
	@Test
	public void produceWithoutProdOrder() throws Exception {
		c.getProduction().produce();
		assertEquals(0,st.getAllFinishedGoods().size());
	}

	@After
	public void resetTests() {

	}

}
