package AspectLogger;

import java.io.PrintWriter;



public class Test {

	@LogThis
	private int x;
	
	public static void main(String[] args) {
		Test t = new Test();
		t.doIt();
		t.getThis(5);
	}
	
	public Test() {
		
	}
	
	public void doIt() {
		this.x = 10;
	}
	
	@LogThis
	public void getThis( int x ) {
		this.x= x;
	}
	
}
