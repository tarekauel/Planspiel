package Company;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Server.Company;
import Server.Location;

public class TestConstructor {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Location.initLocations();
	}

	@Before
	public void initializeTests() {
	}

	@Test
	public void createCompany() throws Exception {
		new Company(Location.getLocationByCountry("Deutschland"));
	}

	@Test (expected = java.lang.IllegalArgumentException.class)
	public void createInvalidByWrongName() throws Exception {
		new Company(Location.getLocationByCountry("Deutschlsd"));
	}
	@Test (expected = java.lang.IllegalArgumentException.class)
	public void createInvalidByNull() throws Exception {
		new Company(null);
	}


}
