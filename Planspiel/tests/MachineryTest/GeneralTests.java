package MachineryTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.BankAccount;
import Server.Machinery;

public class GeneralTests {

	Machinery m;
	BankAccount b;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void createMachinery() {
		// initialisiere
		m = new Machinery();
		b = new BankAccount(990000000);

	}

	@Test
	public void getTest() {
		assertEquals(1, m.getLevel());
	}

	@Test
	public void increaseWithTooLowMoney() {
		//noch 1 auf Bankkonto:
		b.decreaseBalance(b.getBankBalance()-1);
		
		assertEquals(false, m.increaseLevel(b));
	}
	
	@Test
	public void increaseLevel() {
		// Testing all increases
		m.increaseLevel(b);
		assertEquals(2, m.getLevel());

		m.increaseLevel(b);
		assertEquals(3, m.getLevel());

		m.increaseLevel(b);
		assertEquals(4, m.getLevel());

		m.increaseLevel(b);
		assertEquals(5, m.getLevel());

		m.increaseLevel(b);
		assertEquals(6, m.getLevel());

		m.increaseLevel(b);
		assertEquals(7, m.getLevel());

		m.increaseLevel(b);
		assertEquals(8, m.getLevel());

		m.increaseLevel(b);
		assertEquals(9, m.getLevel());

		m.increaseLevel(b);
		assertEquals(10, m.getLevel());
	}

	@Test
	public void decreaseLevel() {
		// Testing a bit increase and then decrease to 1
		m.increaseLevel(b);
		assertEquals(2, m.getLevel());

		m.increaseLevel(b);
		assertEquals(3, m.getLevel());

		m.decreaseLevel();
		assertEquals(2, m.getLevel());

		m.decreaseLevel();
		assertEquals(1, m.getLevel());

	}

	@Test
	public void decreaseLevelTo0() {
		// this one needs to be false, caused by definition
		assertEquals(false, m.decreaseLevel());
		assertEquals(1, m.getLevel());
	}

	@Test
	public void increaseLevelTo11() {
		// increase to 11. it has to be 10 anyways.
		m.increaseLevel(b);
		assertEquals(2, m.getLevel());

		m.increaseLevel(b);
		assertEquals(3, m.getLevel());

		m.increaseLevel(b);
		assertEquals(4, m.getLevel());

		m.increaseLevel(b);
		assertEquals(5, m.getLevel());

		m.increaseLevel(b);
		assertEquals(6, m.getLevel());

		m.increaseLevel(b);
		assertEquals(7, m.getLevel());

		m.increaseLevel(b);
		assertEquals(8, m.getLevel());

		m.increaseLevel(b);
		assertEquals(9, m.getLevel());

		m.increaseLevel(b);
		assertEquals(10, m.getLevel());
		// This one needs to be false(failed to increase)
		assertEquals(false, m.increaseLevel(b));
		assertEquals(10, m.getLevel());

	}

	@Test
	public void randomCheckLevel1() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 1 darf nicht mehr als 15% aussschuss haben
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 15000);
	}

	@Test
	public void randomCheckLevel2() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 2 darf nicht mehr als 14% aussschuss haben
		m.increaseLevel(b);
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 14000);
	}
	
	@Test
	public void randomCheckLevel3() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 3 darf nicht mehr als 13% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 13000);
	}
	
	@Test
	public void randomCheckLevel4() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 4 darf nicht mehr als 12% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 12000);
	}
	
	@Test
	public void randomCheckLevel5() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 5 darf nicht mehr als 10% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 10000);
	}

	@Test
	public void randomCheckLevel6() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 6 darf nicht mehr als 9% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 9000);
	}

	@Test
	public void randomCheckLevel7() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 7 darf nicht mehr als 8% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 8000);
	}
	@Test
	public void randomCheckLevel8() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 8 darf nicht mehr als 7% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 7000);
	}
	
	@Test
	public void randomCheckLevel9() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 9 darf nicht mehr als 6% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 6000);
	}
	
	@Test
	public void randomCheckLevel10() {
		int counter = 0;
		// Beweis über Wahrscheinlichkeit:
		// Maschine auf Stufe 10 darf nicht mehr als 5% aussschuss haben
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		m.increaseLevel(b);
		
		for (int i = 0; i < 100000; i++) {
			counter = (m.isJunk()) ? counter : counter + 1;

		}
		assertEquals(false, counter < 5000);
	}
	
	@After
	public void resetMachine() {
		// clear machine
		m = null;
	}

}
