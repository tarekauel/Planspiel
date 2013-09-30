package MachineryTest;

import static org.junit.Assert.*;

import org.junit.*;

import Constant.Constant;
import Server.*;

public class GeneralTests {

	Machinery m;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Logger deaktivieren
		Constant.PATH="forTest.ini";
	}

	@Before
	public void createMachinery() {
		// initialisiere
		m = new Machinery();

	}

	@Test
	public void initializeTest() {
		assertEquals(1, m.getLevel());
	}

	@Test
	public void increaseLevel() {
		// Testing all increases
		m.increaseLevel();
		assertEquals(2, m.getLevel());

		m.increaseLevel();
		assertEquals(3, m.getLevel());

		m.increaseLevel();
		assertEquals(4, m.getLevel());

		m.increaseLevel();
		assertEquals(5, m.getLevel());

		m.increaseLevel();
		assertEquals(6, m.getLevel());

		m.increaseLevel();
		assertEquals(7, m.getLevel());

		m.increaseLevel();
		assertEquals(8, m.getLevel());

		m.increaseLevel();
		assertEquals(9, m.getLevel());

		m.increaseLevel();
		assertEquals(10, m.getLevel());
	}

	@Test
	public void decreaseLevel() {
		// Testing a bit increase and then decrease to 1
		m.increaseLevel();
		assertEquals(2, m.getLevel());

		m.increaseLevel();
		assertEquals(3, m.getLevel());

		m.decreaseLevel();
		assertEquals(2, m.getLevel());

		m.decreaseLevel();
		assertEquals(1, m.getLevel());

	}

	@Test
	public void decreaseLevelTo0() {
		//this one needs to be false, caused by definition
		assertEquals(false,m.decreaseLevel());
		assertEquals(1, m.getLevel());
	}

	@Test
	public void increaseLevelTo11() {
		// increase to 11. it has to be 10 anyways.
		m.increaseLevel();
		assertEquals(2, m.getLevel());

		m.increaseLevel();
		assertEquals(3, m.getLevel());

		m.increaseLevel();
		assertEquals(4, m.getLevel());

		m.increaseLevel();
		assertEquals(5, m.getLevel());

		m.increaseLevel();
		assertEquals(6, m.getLevel());

		m.increaseLevel();
		assertEquals(7, m.getLevel());

		m.increaseLevel();
		assertEquals(8, m.getLevel());

		m.increaseLevel();
		assertEquals(9, m.getLevel());

		m.increaseLevel();
		assertEquals(10, m.getLevel());
		// This one needs to be false(failed to increase)
		assertEquals(false, m.increaseLevel());
		assertEquals(10, m.getLevel());

	}

	@Test
public void randomCheck(){
		int counter = 0;
		//Beweis über Wahrscheinlichkeit:
		//Maschine auf Stufe 1 darf nicht mehr als 15% aussschuss haben
		for(int i = 0; i<100000; i++){
			counter = (m.isJunk())? counter : counter + 1;
						
		}
		assertEquals(false, counter < 15000);
	}
	
	@After
	public void resetMachine() {
		// clear machine
		m = null;
	}

}
