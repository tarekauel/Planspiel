package Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.FinishedGood;
import Server.Resource;

public class ProductTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Logger deaktivieren
		
		Constant.LOG_GET = true;
		Constant.LOG_INFO = true;
		Constant.LOG_METHOD_NO_PARAM = true;
		Constant.LOG_METHOD_1_PARAM = true;
		Constant.LOG_METHOD_N_PARAM = true;
		Constant.LOG_METHOD_EXIT = true;
		Constant.LOG_NEWOBJ_1_PARAM = true;
		Constant.LOG_NEWOBJ_N_PARAM = true;
		Constant.LOG_SET = true;
		Constant.LOG_VERBOSE = true;
		Constant.LOG_WARNING = true;
	}

	@Test
	public void createValidWafer() throws Exception{
		Resource wafer = new Resource(7, "Wafer",20);
		Boolean isNotNull = wafer!=null;
		assertEquals(true, isNotNull);

	}

	@Test
	public void createValidGehause()  throws Exception{
		Resource gehause = new Resource(7, "Gehäuse", 20);
		Boolean isNotNull = gehause!=null;
		assertEquals(true, isNotNull);

	}

	@Test (expected = IllegalArgumentException.class )
	public void createNonValidRessource()  throws Exception{
		Resource wafer = new Resource(7, "Panel", 20);

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
