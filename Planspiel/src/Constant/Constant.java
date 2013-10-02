package Constant;

import java.io.BufferedReader;
import java.io.FileReader;

public class Constant {
	//Pfad der zu nutzenden INI.
	public static final String PATH = "forTest.ini";
	/**
	 * Gibt ein int Array zurück
	 * 
	 * @param s
	 *            Name der Variablen
	 * @return String mit dem Inhalt
	 */
	private static int[] getIntArray(String s) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(PATH));
			String tmp = null;
			while ((tmp = r.readLine()) != null) {
				if (tmp.startsWith(s)) {
					r.close();
					tmp = tmp.substring(tmp.indexOf("=") + 2).replace("}", "");

					String[] help = tmp.split(",");
					int[] ret = new int[help.length];
					for (int i = 0; i < help.length; i++) {
						ret[i] = Integer.parseInt(help[i]);
					}
					return ret;

				}
			}

			r.close();

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * liest einfache Constanten aus, welche noch gecastet werden müssen
	 * 
	 * @param s
	 *            Name der Variablen
	 * @return String mit dem Inhalt
	 */
	private static String getConstant(String s) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(PATH));
			String tmp = null;
			while ((tmp = r.readLine()) != null) {
				if (tmp.startsWith(s)) {
					r.close();
					return tmp.substring(tmp.indexOf("=") + 1);
				}
			}
			r.close();

		} catch (Exception e) {

		}
		return null;
	}
	
	
	// Section Machinery:
	public static class Machinery{
		
		public static final int[] CAPACITY = getIntArray("MACHINERY_CAPACITY");
		public static final int[] BUILD_COSTS = getIntArray("MACHINERY_BUILD_COSTS");
		public static final int JUNK_INIT = Integer
		.parseInt(getConstant("MACHINERY_JUNK_INIT"));
		public static final int FIX_COST = Integer
		.parseInt(getConstant("MACHINERY_FIX_COST"));
		public static final int PIECE_COST_BASIC = Integer
		.parseInt(getConstant("MACHINERY_PIECE_COST_BASIC"));
		
	}
	
	public static class Production{

		// Section Production
		public static final int WAFERS_PER_PANEL = Integer
				.parseInt(getConstant("PRODUCTION_WAFERS_PER_PANEL"));
		public static final int WORKING_HOURS_PER_PANEL = Integer
		.parseInt(getConstant("PRODUCTION_WORKING_HOURS_PER_PANEL"));
		public static final int COST_PER_ORDER = Integer
		.parseInt(getConstant("PRODUCTION_COST_PER_ORDER"));
		public static final int IMPACT_WAFER = Integer
		.parseInt(getConstant("PRODUCTION_IMPACT_WAFER"));
		public static final int IMPACT_CASE = Integer
		.parseInt(getConstant("PRODUCTION_IMPACT_CASE"));
		public static final int MAX_QUALITY_ADDITION = Integer
		.parseInt(getConstant("PRODUCTION_MAX_QUALITY_ADDITION"));
		
	}
	
	public static class Log{

		// Section Logger
		public static final boolean GET = ((getConstant("LOG_GET")).toLowerCase()
				.equals("true"));
		public static final boolean INFO = ((getConstant("LOG_INFO")).toLowerCase()
		.equals("true"));
		public static final boolean METHOD_NO_PARAM = ((getConstant("LOG_METHOD_NO_PARAM"))
		.toLowerCase().equals("true"));
		public static final boolean METHOD_1_PARAM = ((getConstant("LOG_METHOD_1_PARAM"))
		.toLowerCase().equals("true"));
		public static final boolean METHOD_N_PARAM = ((getConstant("LOG_METHOD_N_PARAM"))
		.toLowerCase().equals("true"));
		public static final boolean METHOD_EXIT = ((getConstant("LOG_METHOD_EXIT"))
		.toLowerCase().equals("true"));
		public static final boolean NEWOBJ_1_PARAM = ((getConstant("LOG_NEWOBJ_1_PARAM"))
		.toLowerCase().equals("true"));
		public static final boolean NEWOBJ_N_PARAM = ((getConstant("LOG_NEWOBJ_N_PARAM"))
		.toLowerCase().equals("true"));
		public static final boolean SET = ((getConstant("LOG_SET")).toLowerCase()
		.equals("true"));
		public static final boolean VERBOSE = ((getConstant("LOG_VERBOSE")).toLowerCase()
		.equals("true"));
		public static final boolean WARNING = ((getConstant("LOG_WARNING")).toLowerCase()
		.equals("true"));
		
	}
	
	public static class Server{

		// Section Technical Constats
		public static final int TCP_PORT = Integer.parseInt(getConstant("TCP_PORT"));
		public static final int UDP_PORT = Integer.parseInt(getConstant("UDP_PORT"));
		
	}
	
	public static class DepartmentFixcost{

		public static final int PURCHASE = Integer
		.parseInt(getConstant("FIXCOST_PURCHASE"));
		public static final int PRODUCTION = Integer
		.parseInt(getConstant("FIXCOST_PRODUCTION"));
		public static final int STORAGE = Integer
		.parseInt(getConstant("FIXCOST_STORAGE"));
		public static final int DISTRIBUTION = Integer
		.parseInt(getConstant("FIXCOST_DISTRIBUTION"));
		public static final int HUMAN_RESOURCES = Integer
		.parseInt(getConstant("FIXCOST_HUMAN_RESOURCE"));
		public static final int MARKET_RESEARCH = Integer
		.parseInt(getConstant("FIXCOST_MARKET_RESEARCH"));
		
	}
	
	public static class Company{

		// Section Company:
		public static final long START_CAPITAL = Long
				.parseLong(getConstant("COMPANY_START_CAPITAL"));
		
	}
	
	public static class BankAccount{

		// Section BankAccount:
		public static final long MAX_CREDIT = Long
				.parseLong(getConstant("BANK_MAX_CREDIT"));
		
	}
	
	
}
