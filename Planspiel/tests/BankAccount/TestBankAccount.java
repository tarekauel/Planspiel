package BankAccount;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Constant.Constant;
import Server.BankAccount;

public class TestBankAccount {

	BankAccount b ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void initializeTests() {
		b = new BankAccount();
	}

	@Test
	public void increase() {
		
		b.increaseBalance(500);
	}
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void increaseNegativeAmount() {
		b.increaseBalance(-5000000);
	}
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void increaseAmount0() {
		b.increaseBalance(0);
	}
	@Test
	public void decrease() {
		assertEquals(true,b.decreaseBalance(Constant.BankAccount.START_CAPITAL - 50));
	}
	@Test
	public void decreaseCompletely() {
		assertEquals(true,b.decreaseBalance(b.getBankBalance()));
	}
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void decreaseNegativeAmount() {
		b.decreaseBalance(-500);
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void decreaseAmount0() {
		b.decreaseBalance(0);
	}
	@Test
	public void decreaseTooMuch() {
		assertEquals(false,b.decreaseBalance(b.getBankBalance()+50));
	}
	
	@After
	public void resetTests() {

	}

}
