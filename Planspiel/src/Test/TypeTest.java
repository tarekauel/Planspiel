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
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPercentOfUsageInvalidPercentAndRound() throws Exception {
		new TPercentOfUsage(-500, -2);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPercentOfUsageInvalidRound() throws Exception {

		 new TPercentOfUsage(500, -2);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPercentOfUsageRound0() throws Exception {
		 new TPercentOfUsage(500, 0);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPercentOfUsageInvalidPercent() throws Exception {

		 new TPercentOfUsage(-500, 2);

	}

	@Test
	public void TPercentOfUsageValid() throws Exception {

		 new TPercentOfUsage(50, 2);

	}

	/********************************************************
	 * TPresentValue Tests starten
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueInvalidPresentValueAndRound() throws Exception {

		 new TPresentValue(-5000, -2);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueInvalidRound() throws Exception {

		 new TPresentValue(5000, -2);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueRound0() throws Exception {

		 new TPresentValue(5000, 0);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TPresentValueInvalidValue() throws Exception {

		 new TPresentValue(-500, 2);

	}

	@Test
	public void TPresentValueValid() throws Exception {

		 new TPresentValue(5000, 2);

	}

	/*********************************************************
	 * TResourcePrice Tests starten
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidLowQualityAndPrice() throws Exception {

		 new TResourcePrice(-50, -200);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidHighQualityAndPrice() throws Exception {

		 new TResourcePrice(101, -200);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidPrice() throws Exception {


		 new TResourcePrice(50, -20);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePricePrice0() throws Exception {
		 new TResourcePrice(50, 0);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceQuality0() throws Exception {
		 new TResourcePrice(0, 1500);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceQuality0AndPrice0() throws Exception {
		 new TResourcePrice(0, 0);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidLowQuality() throws Exception {
		 new TResourcePrice(-50, 2);

	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void TResourcePriceInvalidHighQuality() throws Exception {
		 new TResourcePrice(101, 2);

	}

	@Test
	public void TResourcePriceValid() throws Exception {
		 new TResourcePrice(50, 200);

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
