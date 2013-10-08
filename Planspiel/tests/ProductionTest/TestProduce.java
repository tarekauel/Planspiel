package ProductionTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		cases = new Resource(80, "Gehäuse", 100000);
		
	}

	@Test //so wird aber nicht die logik geprueft oder?
	public void produceValid() throws Exception {
		c.getStorage().store(wafer, 1080);
		c.getStorage().store(cases, 20);
		c.getProduction().createProductionOrder(wafer, cases, 10);
		c.getProduction().produce();
		assertEquals(1,st.getAllFinishedGoods().size());
	}
	
	@Test
	public void produceWithoutProdOrder() throws Exception {
		c.getProduction().produce();
		assertEquals(0,st.getAllFinishedGoods().size());
	}
	
	@Test
	public void produceMaxCapacityReached() throws Exception {
		c.getStorage().store(wafer, 600);
		c.getStorage().store(cases, 50);
		c.getProduction().createProductionOrder(wafer, cases, 500);
		c.getProduction().createProductionOrder(wafer, cases, 50);
		c.getProduction().produce();
		int quantity = 0;
		ArrayList<StorageElement> arrayListSE = st.getAllStorageElements();
		for(StorageElement storageElem: arrayListSE){
			Product prod = storageElem.getProduct();
			if(prod instanceof FinishedGood){
				quantity = storageElem.getQuantity();
			}
		}
		assertEquals(true,quantity == 500);
	}

	@After
	public void resetTests() {

	}

}
