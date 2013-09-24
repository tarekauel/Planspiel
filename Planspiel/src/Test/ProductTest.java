package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Server.FinishedGood;
import Server.Resource;

public class ProductTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void createValidWafer() {
		Resource wafer = Resource.create(7, 20, "Wafer");
		Boolean isNotNull = wafer!=null;
		assertEquals(true, isNotNull);

	}

	@Test
	public void createValidGehause() {
		Resource gehause = Resource.create(7, 20, "Gehäuse");
		Boolean isNotNull = gehause!=null;
		assertEquals(true, isNotNull);

	}

	@Test
	public void createNonValidRessource() {
		Resource wafer = Resource.create(7, 20, "Panel");
		Boolean isNotNull = wafer!=null;
		assertEquals(false, isNotNull);

	}

	@Test
	public void createValidFinishedGood() {
		FinishedGood fg = FinishedGood.create(7, 10);
		Boolean isNotNull = fg!=null;
		assertEquals(true, isNotNull);

	}

	@Test
	public void createNonValidProductByCosts() {
		FinishedGood fg = FinishedGood.create(7, -1);
		Boolean isNotNull = fg!=null;
		assertEquals(false, isNotNull);

	}

	@Test
	public void createNonValidProductByQuality() {
		FinishedGood fg = FinishedGood.create(101, 10);
		Boolean isNotNull = fg!=null;
		assertEquals(false, isNotNull);
		fg = FinishedGood.create(-1, 10);
		isNotNull = fg!=null;
		assertEquals(false, isNotNull);

	}

}
