package Test;

import static org.junit.Assert.*;

import org.junit.*;

import Constant.Constant;
import Server.*;

public class StorageElementTest {
	private static FinishedGood panel;
	private static Resource wafer;
	private static Resource cases;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Rohstoffe initialisieren
		panel = FinishedGood.create(5, 40000);
		wafer = new Resource(60, "Wafer", 300);
		cases = new Resource(40,  "Gehäuse", 4500);
		
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

	@Test
	public void createValidStorageElement() throws Exception {
		// StorageElemente erstellen
		// Wafer erstellen
		StorageElement se = new StorageElement(5, wafer);
		assertEquals(true, se != null);
		// Case erstellen
		se = new StorageElement(2, cases);
		assertEquals(true, se != null);

		// Panel erstellen
		se = new StorageElement(50, panel);
		assertEquals(true, se != null);

	}

	@Test
	public void increaseValidElement() {
		// create the store element
		StorageElement se = null;
		try {
			se = new StorageElement(50, wafer);
		} catch (Exception e) {
		}
		assertEquals(true, se != null);
		// put something more on the store element
		se.increaseQuantity(500);

		assertEquals(550, se.getQuantity());

	}

	@Test
	public void decreaseValidElementTooMuch() {
		// create the store element
		StorageElement se = null;
		try {
			se = new StorageElement(50, wafer);
		} catch (Exception e) {
		}
		assertEquals(true, se != null);
		// take more from the store element then is in
		se.reduceQuantity(500);

		assertEquals(50, se.getQuantity());

	}
	
	@Test
	public void decreaseValidElementCompletely() {
		// create the store element
		StorageElement se = null;
		try {
			se = new StorageElement(500, wafer);
		} catch (Exception e) {
		}
		assertEquals(true, se != null);
		// get exactly the whole store element
		se.reduceQuantity(500);

		assertEquals(0, se.getQuantity());

	}
	@Test
	public void decreaseValidElement() {
		// create the store element
		StorageElement se = null;
		try {
			se = new StorageElement(500, wafer);
		} catch (Exception e) {
		}
		assertEquals(true, se != null);
		// get exactly the whole store element
		se.reduceQuantity(50);

		assertEquals(450, se.getQuantity());

	}
	@Test
	public void createInValidStorageElement() throws Exception {
		// StorageElemente erstellen
		// Wafer erstellen
		StorageElement se = null;
		try {
			se = new StorageElement(-5, wafer);
		} catch (Exception e) {
		}
		assertEquals(true, se == null);
		// Case erstellen
		se = null;
		try {
			se = new StorageElement(2, null);
		} catch (Exception e) {
		}
		assertEquals(true, se == null);

		// Panel erstellen
		se = null;
		try {
			se = new StorageElement(0, panel);
		} catch (Exception e) {
		}
		assertEquals(true, se == null);

	}
}
