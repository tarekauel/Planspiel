package Test;

import org.junit.BeforeClass;
import org.junit.Test;

import Server.TMotivation;
import Constant.Constant;

public class TMotivationTest {
	

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
	
	/*********************************************************
	 * TMotivation Tests starten
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationInvalidLowMotivationAndRound() throws Exception {

		new TMotivation(-50, -200);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationInvalidHighMotivationAndRound() throws Exception {

		new TMotivation(101, -200);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationInvalidRound() throws Exception {

		new TMotivation(50, -20);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationRound0() throws Exception {
		new TMotivation(50, 0);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationMotivation0() throws Exception {
		new TMotivation(0, 2);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationMotivation0AndRound0() throws Exception {
		new TMotivation(0, 0);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationInvalidLowMotivation() throws Exception {
		new TMotivation(-50, 2);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TMotivationInvalidHighMotivation() throws Exception {
		new TMotivation(10000, 2);

	}

	@Test
	public void TMotivationValid() throws Exception {
		new TMotivation(100, 2);

	}

}
