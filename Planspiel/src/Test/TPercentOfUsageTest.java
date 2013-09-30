package Test;

import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.TPercentOfUsage;


public class TPercentOfUsageTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPercentOfUsageInvalidPercent() throws Exception {
	
		new TPercentOfUsage(-500, 2);
	
	}

	/********************************************************
	 * TPercentOfUsage Tests starten
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPercentOfUsageInvalidPercentAndRound() throws Exception {
		new TPercentOfUsage(-500, -2);
	
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPercentOfUsageInvalidRound() throws Exception {
	
		new TPercentOfUsage(500, -2);
	
	}

	@Test
	public void TPercentOfUsageRound0() throws Exception {
		new TPercentOfUsage(500, 0);
	
	}

	@Test
	public void TPercentOfUsageValid() throws Exception {
	
		new TPercentOfUsage(50, 2);
	
	}

	
}
