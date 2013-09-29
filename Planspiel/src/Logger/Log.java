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
		log = Logger.getGlobal();		
	}
	
	public void logWarning(String msg) {
		log.log(Level.WARNING, msg);
	}
	
	public void logVerbose(String msg) {
		log.log(Level.FINE, msg);
	}
	
	public void logInfo(String msg) {
		log.log(Level.FINER, msg);
	}
	
	public void logNewObj(String msg) {
		log.log(Level.INFO, msg);
	}
	
	
	
	

}
