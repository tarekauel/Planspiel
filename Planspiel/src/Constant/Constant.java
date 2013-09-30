package Constant;

public class Constant {
	// Section BankAccount:
	public static final long BANK_MAX_CREDIT = 50000; // 500 00

	// Section Company:
	public static final long COMPANY_START_CAPITAL = 100000000; // 1 000 000 00
	public static final int FIXCOST_PURCHASE = 1000000; // 10 000 00
	public static final int FIXCOST_PRODUCTION = 1000000; // 10 000 00
	public static final int FIXCOST_STORAGE = 1000000; // 10 000 00
	public static final int FIXCOST_DISTRIBUTION = 1000000; // 10 000 00
	public static final int FIXCOST_HUMAN_RESOURCES = 1000000; // 10 000 00
	public static final int FIXCOST_MARKET_RESEARCH = 1000000; // 10 000 00

	// Section Machinery:
	public static final int[] MACHINERY_CAPACITY = { 500, // lvl 1
			1000, // lvl 2
			2000, // lvl 3
			4000, // lvl 4
			7000, // lvl 5
			10000, // lvl 6
			14000, // lvl 7
			19000, // lvl 8
			25000, // lvl 9
			35000 // lvl 10
	};
	public static final int MACHINERY_JUNK_INIT = 84; // 15 00
	public static final int MACHINERY_FIX_COST = 250000;// 2 500 00
	public static final int MACHINERY_PIECE_COST_BASIC = 1500; // 15 00

	// Section Production
	public static final int PRODUCTION_WAFERS_PER_PANEL = 54;
	public static final int PRODUCTION_WORKING_HOURS_PER_PANEL = 5;
	public static final int PRODUCTION_COST_PER_ORDER = 1000; // 10 00
	public static final int PRODUCTION_IMPACT_WAFER = 80;
	public static final int PRODUCTION_IMPACT_CASE = 20;
	public static final int PRODUCTION_MAX_QUALITY_ADDITION = 20;
	
	//Technical Constats
	public static final int TCP_PORT=11111;
	public static final int UDP_PORT=11111;
}
