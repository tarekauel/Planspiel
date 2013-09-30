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
				Constant.PATH="forTest.ini";
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
