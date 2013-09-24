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
	public void createValidRequest() {
		Resource wafer = Resource.create(8, 20, "Wafer");
		Request request = Request.create(wafer);

		assertEquals(true, request != null);

	}

	@Test
	public void createNonValidRequest() {

		Request request = Request.create(null);
		assertEquals(true, request == null);

	}

	@Test
	public void addValidSpplierOfferToReqest() {
		Resource wafer = Resource.create(80, 20, "Wafer");
		Request request = Request.create(wafer);

		Resource supWafer = Resource.create(78, 30, "Wafer");
		SupplierOffer offer = SupplierOffer.create(supWafer);
		request.addSupplierOffer(offer);

		supWafer = Resource.create(81, 40, "Wafer");
		offer = SupplierOffer.create(supWafer);
		request.addSupplierOffer(offer);

		assertEquals(true, request.getSpplierOffers()[0] != null);
		assertEquals(true, request.getSpplierOffers()[1] != null);
	}

	@Test
	public void addNonValidSpplierOfferToReqest() {
		Resource wafer = Resource.create(80, 20, "Wafer");
		Request request = Request.create(wafer);

		Resource supGehause = Resource.create(78, 30, "Gehäuse");
		SupplierOffer offer = SupplierOffer.create(supGehause);
		Boolean added = request.addSupplierOffer(offer);

		assertEquals(false, added);

		Resource supWafer = Resource.create(78, 30, "Wafer");
		offer = SupplierOffer.create(supWafer);

		added = request.addSupplierOffer(offer);
		assertEquals(true, added);

		added = request.addSupplierOffer(offer);
		assertEquals(true, added);

		added = request.addSupplierOffer(offer);
		assertEquals(true, added);

		added = request.addSupplierOffer(offer);
		assertEquals(false, added);

	}

}
