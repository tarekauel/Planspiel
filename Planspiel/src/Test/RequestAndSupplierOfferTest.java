package Test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.Request;
import Server.Resource;
import Server.SupplierOffer;

public class RequestAndSupplierOfferTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Logger deaktivieren
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

	@Test
	public void createValidRequest() throws Exception {
		Resource wafer = new Resource(8, "Wafer", 20);
		Request request = new Request(wafer);

		assertEquals(true, request != null);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void createNonValidRequest() {
		new Request(null);
	}

	@Test
	public void addValidSupplierOfferToReqest() throws Exception {
		Resource wafer = new Resource(80, "Wafer", 20);
		Request request = new Request(wafer);

		Resource supWafer = new Resource(78, "Wafer", 30);
		SupplierOffer offer = new SupplierOffer(supWafer);
		request.addSupplierOffer(offer);

		supWafer = new Resource(81, "Wafer", 40);
		offer = new SupplierOffer(supWafer);
		request.addSupplierOffer(offer);

		assertEquals(true, request.getSupplierOffers()[0] != null);
		assertEquals(true, request.getSupplierOffers()[1] != null);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void addNonValidSupplierOfferToReqest() throws Exception {

		Resource wafer = new Resource(80, "Wafer", 20);
		Request request = new Request(wafer);

		request.addSupplierOffer(null);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void addMoreThanThreeOffers() throws Exception {
		Request request = new Request(new Resource(80, "Wafer", 20));

		SupplierOffer offer = new SupplierOffer(new Resource(78, "Wafer", 30));

		request.addSupplierOffer(offer);
		request.addSupplierOffer(offer);
		request.addSupplierOffer(offer);
		request.addSupplierOffer(offer);

	}

}
