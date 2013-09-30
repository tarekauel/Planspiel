package Test;

import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.TWage;

public class TWageTest {
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
	public void TWageInvalidAmount() throws Exception {
	
		new TWage(-500, 2);
	
	}

	/********************************************************
	 * TWage Tests starten
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TWageInvalidAmountAndRound() throws Exception {
		new TWage(-500, -2);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TWageInvalidRound() throws Exception {
	
		new TWage(500, -2);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TWageRound0() throws Exception {
		new TWage(500, 0);
	
	}

	@Test
	public void TWageValid() throws Exception {
	
		new TWage(50, 2);
	
	}

}
