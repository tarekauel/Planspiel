package Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Constant.Constant;

public class Log {
	
	private static Logger log = Logger.getLogger("Planspiel");
	 
	static {
		try {
			log.addHandler( new FileHandler( "log.txt"));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void warning(String msg) {
		if(! Constant.Log.WARNING){
			return;
		}
		log.log(Level.INFO, msg);
	}
	
	public static void verbose(String msg) {
		if(! Constant.Log.VERBOSE){
			return;
		}
		log.log(Level.INFO, msg);
	}
	
	public static void info(String msg) {
		if(! Constant.Log.INFO){
			return;
		}
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		
		log.log(Level.INFO, "Class: " + className + " Method: " + method + " " + msg);
	}
	
	public static void newObj(Object[] param) {
		if(! Constant.Log.NEWOBJ_N_PARAM){
			return;
		}
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		
		String msg = "Class: " + className + " Param: ";
		
		for(int i=1; i<param.length; i++) {
			msg += String.valueOf(param[i]) + ", ";
		}
		msg = msg.substring(0, msg.length()-2);
		log.log(Level.INFO, msg);
	}
	
	public static void newObj(Object param) {
		if(! Constant.Log.NEWOBJ_1_PARAM){
			return;
		}
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		
		String msg = "Class: " + className + " Param: " + String.valueOf(param);
		log.log(Level.INFO, msg);
	}
	
	public static void method() {
		if(! Constant.Log.METHOD_NO_PARAM){
			return;
		}
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Log.verbose("Class: " + className + " Method: " + method);
	}
	
	public static void method(Object[] param) {
		if(! Constant.Log.METHOD_N_PARAM){
			return;
		}
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String msg = "Class: " + className + " Method: " + method +  " Param: ";
		
		for(int i=1; i<param.length; i++) {
			msg += String.valueOf(param[i]) + ", ";
		}
		msg = msg.substring(0, msg.length()-2);
		log.log(Level.INFO, msg);
	}
	
	public static void method(Object param) {
		if(! Constant.Log.METHOD_1_PARAM){
			return;
		}
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String msg = "Class: " + className + " Method: " + method +  " Param: " + param;
		log.log(Level.INFO, msg);
	}
	
	public static void methodExit() {
		if(! Constant.Log.METHOD_EXIT){
			return;
		}
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Log.verbose("Class: " + className + " Method: " + method);
	}
	
	public static void get(Object param) {
		if(! Constant.Log.SET){
			return;
		}
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Log.verbose("Class: " + className + " Method: " + method + " GET: "  + String.valueOf(param));
	}
	public static void set(Object param) {
		if(! Constant.Log.GET){
			return;
		}
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		Log.verbose("Class: " + className + " Method: " + method + " SET: "  + String.valueOf(param));
	}
}
