package Test;

import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.TPercentOfUsage;


public class TPercentOfUsageTest {
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
