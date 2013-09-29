package Test;

import static org.junit.Assert.*;

import org.junit.*;
import Server.*;

/**
 * 
 * @author Lars this Test is for all TypClasses to check their constructors It
 *         will not test getters !
 */
public class TypeTest {
	/********************************************************
	 * TPercentOfUsage Tests starten
	 */
	@Test
	public void TPercentOfUsageInvalidPercentAndRound() {
		TPercentOfUsage test = null;
		try {
			test = new TPercentOfUsage(-500, -2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);
	}

	@Test
	public void TPercentOfUsageInvalidRound() {

		TPercentOfUsage test = null;
		try {
			test = new TPercentOfUsage(500, -2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TPercentOfUsageRound0() {
		TPercentOfUsage test = null;
		try {
			test = new TPercentOfUsage(500, 0);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TPercentOfUsageInvalidPercent() {
		TPercentOfUsage test = null;
		try {
			test = new TPercentOfUsage(-500, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TPercentOfUsageValid() {
		TPercentOfUsage test = null;
		try {
			test = new TPercentOfUsage(50, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test != null);

	}

	/********************************************************
	 * TPresentValue Tests starten
	 */
	@Test
	public void TPresentValueInvalidPresentValueAndRound() {
		TPresentValue test = null;
		try {
			test = new TPresentValue(-5000, -2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);
	}

	@Test
	public void TPresentValueInvalidRound() {

		TPresentValue test = null;
		try {
			test = new TPresentValue(5000, -2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TPresentValueRound0() {
		TPresentValue test = null;
		try {
			test = new TPresentValue(5000, 0);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TPresentValueInvalidValue() {
		TPresentValue test = null;
		try {
			test = new TPresentValue(-500, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TPresentValueValid() {
		TPresentValue test = null;
		try {
			test = new TPresentValue(5000, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test != null);

	}

	/*********************************************************
	 * TResourcePrice Tests starten
	 */
	@Test
	public void TResourcePriceInvalidLowQualityAndPrice() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(-50, -200);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);
	}

	@Test
	public void TResourcePriceInvalidHighQualityAndPrice() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(101, -200);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);
	}

	@Test
	public void TResourcePriceInvalidPrice() {

		TResourcePrice test = null;
		try {
			test = new TResourcePrice(50, -20);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TResourcePricePrice0() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(50, 0);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TResourcePriceQuality0() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(0, 1500);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TResourcePriceQuality0AndPrice0() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(0, 0);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TResourcePriceInvalidLowQuality() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(-50, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TResourcePriceInvalidHighQuality() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(101, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TResourcePriceValid() {
		TResourcePrice test = null;
		try {
			test = new TResourcePrice(50, 200);
		} catch (Exception e) {

		}
		assertEquals(true, test != null);

	}

	/*********************************************************
	 * TMotivation Tests starten
	 */
	@Test
	public void TMotivationInvalidLowMotivationAndRound() {
		TMotivation test = null;
		try {
			test = new TMotivation(-50, -200);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);
	}

	
	@Test
	public void TMotivationInvalidHighMotivationAndRound() {
		TMotivation test = null;
		try {
			test = new TMotivation(101, -200);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);
	}

	@Test
	public void TMotivationInvalidRound() {

		TMotivation test = null;
		try {
			test = new TMotivation(50, -20);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TMotivationRound0() {
		TMotivation test = null;
		try {
			test = new TMotivation(50, 0);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TMotivationMotivation0() {
		TMotivation test = null;
		try {
			test = new TMotivation(0, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TMotivationMotivation0AndRound0() {
		TMotivation test = null;
		try {
			test = new TMotivation(0, 0);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TMotivationInvalidLowMotivation() {
		TMotivation test = null;
		try {
			test = new TMotivation(-50, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TMotivationInvalidHighMotivation() {
		TMotivation test = null;
		try {
			test = new TMotivation(10000, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test == null);

	}

	@Test
	public void TMotivationValid() {
		TMotivation test = null;
		try {
			test = new TMotivation(100, 2);
		} catch (Exception e) {

		}
		assertEquals(true, test != null);

	}
	
}
