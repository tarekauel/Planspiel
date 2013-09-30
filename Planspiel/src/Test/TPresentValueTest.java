package Test;

import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.TPresentValue;

public class TPresentValueTest {

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
	/********************************************************
	 * TPresentValue Tests starten
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueInvalidPresentValueAndRound() throws Exception {
	
		new TPresentValue(-5000, -2);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueInvalidRound() throws Exception {
	
		new TPresentValue(5000, -2);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueInvalidValue() throws Exception {
	
		new TPresentValue(-500, 2);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueRound0() throws Exception {
	
		new TPresentValue(5000, 0);
	
	}

	@Test
	public void TPresentValueValid() throws Exception {
	
		new TPresentValue(5000, 2);
	
	}

	

}
