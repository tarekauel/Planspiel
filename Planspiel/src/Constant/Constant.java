package Constant;

import java.io.BufferedReader;
import java.io.FileReader;

public class Constant {
	//Pfad der zu nutzenden INI.
	public static final String PATH = "forTest.ini";

	// Section BankAccount:
	public static final long BANK_MAX_CREDIT = Long
			.parseLong(c("BANK_MAX_CREDIT"));

	// Section Company:
	public static final long COMPANY_START_CAPITAL = Long
			.parseLong(c("COMPANY_START_CAPITAL"));
	public static final int FIXCOST_PURCHASE = Integer
			.parseInt(c("FIXCOST_PURCHASE"));
	public static final int FIXCOST_PRODUCTION = Integer
			.parseInt(c("FIXCOST_PRODUCTION"));
	public static final int FIXCOST_STORAGE = Integer
			.parseInt(c("FIXCOST_STORAGE"));
	public static final int FIXCOST_DISTRIBUTION = Integer
			.parseInt(c("FIXCOST_DISTRIBUTION"));
	public static final int FIXCOST_HUMAN_RESOURCES = Integer
			.parseInt(c("FIXCOST_HUMAN_RESOURCE"));
	public static final int FIXCOST_MARKET_RESEARCH = Integer
			.parseInt(c("FIXCOST_MARKET_RESEARCH"));

	// Section Machinery:

	public static final int[] MACHINERY_CAPACITY = a("MACHINERY_CAPACITY");
	public static final int MACHINERY_JUNK_INIT = Integer
			.parseInt(c("MACHINERY_JUNK_INIT"));
	public static final int MACHINERY_FIX_COST = Integer
			.parseInt(c("MACHINERY_FIX_COST"));
	public static final int MACHINERY_PIECE_COST_BASIC = Integer
			.parseInt(c("MACHINERY_PIECE_COST_BASIC"));

	// Section Production
	public static final int PRODUCTION_WAFERS_PER_PANEL = Integer
			.parseInt(c("PRODUCTION_WAFERS_PER_PANEL"));
	public static final int PRODUCTION_WORKING_HOURS_PER_PANEL = Integer
			.parseInt(c("PRODUCTION_WORKING_HOURS_PER_PANEL"));
	public static final int PRODUCTION_COST_PER_ORDER = Integer
			.parseInt(c("PRODUCTION_COST_PER_ORDER"));
	public static final int PRODUCTION_IMPACT_WAFER = Integer
			.parseInt(c("PRODUCTION_IMPACT_WAFER"));
	public static final int PRODUCTION_IMPACT_CASE = Integer
			.parseInt(c("PRODUCTION_IMPACT_CASE"));
	public static final int PRODUCTION_MAX_QUALITY_ADDITION = Integer
			.parseInt(c("PRODUCTION_MAX_QUALITY_ADDITION"));

	// Section Technical Constats
	public static final int TCP_PORT = Integer.parseInt(c("TCP_PORT"));
	public static final int UDP_PORT = Integer.parseInt(c("UDP_PORT"));

	// Section Logger
	public static final boolean LOG_GET = ((c("LOG_GET")).toLowerCase()
			.equals("true"));
	public static final boolean LOG_INFO = ((c("LOG_INFO")).toLowerCase()
			.equals("true"));
	public static final boolean LOG_METHOD_NO_PARAM = ((c("LOG_METHOD_NO_PARAM"))
			.toLowerCase().equals("true"));
	public static final boolean LOG_METHOD_1_PARAM = ((c("LOG_METHOD_1_PARAM"))
			.toLowerCase().equals("true"));
	public static final boolean LOG_METHOD_N_PARAM = ((c("LOG_METHOD_N_PARAM"))
			.toLowerCase().equals("true"));
	public static final boolean LOG_METHOD_EXIT = ((c("LOG_METHOD_EXIT"))
			.toLowerCase().equals("true"));
	public static final boolean LOG_NEWOBJ_1_PARAM = ((c("LOG_NEWOBJ_1_PARAM"))
			.toLowerCase().equals("true"));
	public static final boolean LOG_NEWOBJ_N_PARAM = ((c("LOG_NEWOBJ_N_PARAM"))
			.toLowerCase().equals("true"));
	public static final boolean LOG_SET = ((c("LOG_SET")).toLowerCase()
			.equals("true"));
	public static final boolean LOG_VERBOSE = ((c("LOG_VERBOSE")).toLowerCase()
			.equals("true"));
	public static final boolean LOG_WARNING = ((c("LOG_WARNING")).toLowerCase()
			.equals("true"));

	/**
	 * Gibt ein int Array zurück
	 * 
	 * @param s
	 *            Name der Variablen
	 * @return String mit dem Inhalt
	 */
	private static int[] a(String s) {
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
	private static String c(String s) {
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
}
