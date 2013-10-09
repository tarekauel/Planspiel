package Aspect;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import AspectLogger.FakeRandom;

public class RandomTest {

	
	@Test
	@FakeRandom( newRandom =  { 1.0, 2.0 }, methodName = { "a", "b" } )
	public void meinTestA() {
		assertEquals((int) 1.0, (int) a() );
		assertEquals((int) 2.0, (int) b() );
	}
	
	@Test
	@FakeRandom( newRandom = { 7.0, 5.0 }, methodName = { "b", "a" } )
	public void meinTestB() {
		assertEquals((int) 5.0, (int) a() );
		assertEquals((int) 7.0, (int) b() );
	}
	
	@Test
	@FakeRandom( newRandom = { 7.0}, methodName = { "a" } )
	public void meinTestC() {
		assertEquals((int) 7.0, (int) c() );
		assertEquals(0.5, b(), 0.5);	     // Untouched!	
	}
	
	public double a() {
		return Math.random();
	}
	
	public double b() {
		return Math.random();
	}
	
	public double c() {
		return a();
	}
	
	

}
