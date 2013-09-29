package Logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	
	private static Log logRef = null;
	
	private Logger log;
	
	public static Log getLogger() {
		if( logRef == null) {
			logRef = new Log();
		}
		return logRef;
	}
	
	public Log() {
		log = Logger.getLogger("Planspiel");		
	}
	
	public void warning(String msg) {
		log.log(Level.WARNING, msg);
	}
	
	public void verbose(String msg) {
		log.log(Level.FINE, msg);
	}
	
	public void info(String msg) {
		log.log(Level.FINER, msg);
	}
	
	public void newObj(String[] param) {
		String msg = "Class: " + param[0] + " Param: ";
		
		for(int i=1; i<param.length; i++) {
			msg += param[i] + ", ";
		}
		
		log.log(Level.INFO, msg);
	}
	
	
	
	

}
