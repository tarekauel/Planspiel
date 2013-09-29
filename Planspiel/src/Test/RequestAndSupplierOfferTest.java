package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Server.FinishedGood;
import Server.Request;
import Server.Resource;
import Server.SupplierOffer;

public class RequestAndSupplierOfferTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void createValidRequest()  throws Exception{
		Resource wafer = new Resource(8, "Wafer", 20);
		Request request = new Request(wafer);

		assertEquals(true, request != null);

	}

	@Test (expected = java.lang.IllegalArgumentException.class)
	public void createNonValidRequest() {
		new Request (null);
	}

	@Test
	public void addValidSupplierOfferToReqest() throws Exception {
		Resource wafer = new Resource (80,"Wafer", 20 );
		Request request = new Request(wafer);

		Resource supWafer = new Resource(78, "Wafer", 30);
		SupplierOffer offer = new SupplierOffer (supWafer);
		request.addSupplierOffer(offer);

		supWafer = new Resource (81,  "Wafer", 40);
		offer = new SupplierOffer(supWafer);
		request.addSupplierOffer(offer);

		assertEquals(true, request.getSupplierOffers()[0] != null);
		assertEquals(true, request.getSupplierOffers()[1] != null);
	}

	@Test ( expected = java.lang.IllegalArgumentException.class )
	public void addNonValidSupplierOfferToReqest() throws Exception {
		
		Resource wafer = new Resource(80,  "Wafer", 20);
		Request request = new Request( wafer);

		request.addSupplierOffer(null);

		
	}
	
	@Test ( expected = java.lang.IllegalArgumentException.class )
	public void addMoreThanThreeOffers() throws Exception {
		
		Resource wafer = new Resource(80,  "Wafer", 20);
		Request request = new Request( wafer);

		Resource supGehause = new Resource(78, "Gehäuse", 30);
		SupplierOffer offer = new SupplierOffer(supGehause);
		Boolean added = request.addSupplierOffer(offer);

		assertEquals(false, added);

		Resource supWafer = new Resource(78,  "Wafer", 30);
		offer = new SupplierOffer(supWafer);

		request.addSupplierOffer(offer);
		request.addSupplierOffer(offer);
		request.addSupplierOffer(offer);
		request.addSupplierOffer(offer);
		
	}


}
