package Distribution;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.Company;
import Server.Distribution;
import Server.FinishedGood;
import Server.Location;
import Server.Storage;

public class TestPrepareForNewRound {
	Location l;
	Company c;
	Distribution d;
	Storage st;
	FinishedGood fg;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Location.initLocations();

	}

	@Before
	public void initializeTests() throws Exception {
		l = new Location(110, "USA", 4000000, 750);
		c = new Company(l);
		d = new Distribution(c);
		st = new Storage(c);
		fg = FinishedGood.create(8, 20000);
		st.store(fg,100);
		d.createOffer(8,10,100);
		d.createOffer(8,10,101);
		d.createOffer(8,10,102);
		d.createOffer(8,10,103);
		
	}

	
	@Test 
	public void valid() {
		assertEquals(4,d.getListOfLatestOffers().size());
		d.prepareForNewRound(5);
		assertEquals(0,d.getListOfLatestOffers().size());
		
	}

	

}
