package BankAccount;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.BankAccount;

public class TestConstructor {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void initializeTests() {
	}

	@Test
	public void validCreate() {
		// Test durch keinen crash
		new BankAccount();
	}	
	
	@After
	public void resetTests() {

	}

}
