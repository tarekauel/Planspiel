package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;

public class TestTemplate {

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

	@Before
	public void initializeTests() {
	}

	@Test
	public void makeTests() {
	}

	@After
	public void resetTests() {

	}

}
