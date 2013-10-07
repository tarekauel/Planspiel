package StorageTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.*;

public class TestDebitStorageCosts {
	
	Company c;
	Storage st;
	FinishedGood fg;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
	}

	@Before
	public void initializeTests() throws Exception {
		c = new Company(Location.getLocationByCountry("USA"));
		c.getBankAccount().increaseBalance(100000000);
		st = c.getStorage();
		for(int i = 0;i<10;i++){
			fg = FinishedGood.create(80+i, 20000);
			st.store(fg,15);
		}
		
	}

	@Test
	public void debitStorageCostsValid() throws Exception {
		assertEquals(true,st.debitStorageCost());
		
		
				
	}
	
	@Test
	public void debitStorageCostsIfBankbalanceIsTooLow() {
		
	}

	@After
	public void resetTests() {

	}

}
