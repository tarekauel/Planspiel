package Test;

import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.TResourcePrice;

public class TResourcePriceTest {
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
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidHighQuality() throws Exception {
		new TResourcePrice(101, 2);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidHighQualityAndPrice() throws Exception {
	
		new TResourcePrice(101, -200);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidLowQuality() throws Exception {
		new TResourcePrice(-50, 2);
	
	}

	/*********************************************************
	 * TResourcePrice Tests starten
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidLowQualityAndPrice() throws Exception {
	
		new TResourcePrice(-50, -200);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidPrice() throws Exception {
	
		new TResourcePrice(50, -20);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePricePrice0() throws Exception {
		new TResourcePrice(50, 0);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceQuality0() throws Exception {
		new TResourcePrice(0, 1500);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceQuality0AndPrice0() throws Exception {
		new TResourcePrice(0, 0);
	
	}

	@Test
	public void TResourcePriceValid() throws Exception {
		new TResourcePrice(50, 200);
	
	}

}
