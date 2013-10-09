package Aspect;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import AspectLogger.FakeRandom;

public class RandomTest {

	
	@Test
	public void test() {
		double fakeRandom = Math.random(); // Immer zwischen 0 und 1
		assertEquals(0.5, fakeRandom,0.5);
	}
	
	@Test
	@FakeRandom(newRandom = 2.0 )
	public void testA() {
		int fakeRandom = (int) Math.random(); // Immer 2
		assertEquals(2, fakeRandom);
	}
	
	@Test
	@FakeRandom(newRandom = 5.0 )
	public void testB() {
		int fakeRandom = (int) Math.random(); // Immer 5
		assertEquals(5, fakeRandom);
	}

}
