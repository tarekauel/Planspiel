package Logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	
	private static Logger log = Logger.getLogger("Planspiel");
	
	public static void warning(String msg) {
		log.log(Level.INFO, msg);
	}
	
	public static void verbose(String msg) {
		log.log(Level.INFO, msg);
	}
	
	public static void info(String msg) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		
		log.log(Level.INFO, "Class: " + className + " Method: " + method + " " + msg);
	}
	
	public static void newObj(Object[] param) {
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		
		String msg = "Class: " + className + " Param: ";
		
		for(int i=1; i<param.length; i++) {
			msg += String.valueOf(param[i]) + ", ";
		}
		msg = msg.substring(0, msg.length()-2);
		log.log(Level.INFO, msg);
	}
	
	public static void newObj(Object param) {
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		
		String msg = "Class: " + className + " Param: " + String.valueOf(param);
		log.log(Level.INFO, msg);
	}
	
	public static void method() {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Log.verbose("Class: " + className + " Method: " + method);
	}
	
	public static void get(Object param) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Log.verbose("Class: " + className + " Method: " + method + " GET: "  + String.valueOf(param));
	}
	public static void set(Object param) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Log.verbose("Class: " + className + " Method: " + method + " SET: "  + String.valueOf(param));
	}
}
