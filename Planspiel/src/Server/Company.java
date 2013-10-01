package Server;

import Constant.Constant;
import Logger.Log;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 17:22
 */

public class Company {

	private Location location;
	private BankAccount bankAccount;

	private Purchase purchase;
	private Production production;
	private Storage storage;
	private Distribution distribution;
	private HumanResources humanResources;
	private MarketResearch marketResearch;

	public Company(Location l) throws Exception {
		Log.newObj(l);
		if (!checkLocation(l)) {
			throw new IllegalArgumentException("Ungültiger Standort");
		}

		// erzeuge Bankkonto mit 1 Mio Kapital
		bankAccount = new BankAccount(Constant.COMPANY_START_CAPITAL);
		// setze Location

		this.location = l;
		// 'Kaufe die Location'
		bankAccount.decreaseBalance(l.getPurchasePrice());

		// Erzuege alle Abteilungen
		this.purchase = new Purchase(this, Constant.FIXCOST_PURCHASE);
		this.production = new Production(this, Constant.FIXCOST_PRODUCTION);
		this.storage = new Storage(this, Constant.FIXCOST_STORAGE);
		this.distribution = new Distribution(this,
				Constant.FIXCOST_DISTRIBUTION);
		this.humanResources = new HumanResources(this,
				Constant.FIXCOST_HUMAN_RESOURCES);
		this.marketResearch = new MarketResearch(this,
				Constant.FIXCOST_MARKET_RESEARCH);

		// Anmelden an der Gamengine
		GameEngine.getGameEngine().addCompany(this);

	}

	private boolean checkLocation(Location l) {
		if (l != null) {
			return Location.getLocationByCountry(l.getCountry()) != null;
		}
		return false;
	}

	public void initRound(int round) {

	}

	public Location getLocation() {
		Log.get(location);
		return this.location;
	}

	public BankAccount getBankAccount() {
		Log.get(bankAccount);
		return this.bankAccount;
	}

	public Purchase getPurchase() {
		Log.get(purchase);
		return this.purchase;
	}

	public Production getProduction() {
		Log.get(production);
		return this.production;
	}

	public Storage getStorage() {

		Log.get(storage);
		return this.storage;
	}

	public Distribution getDistribution() {
		Log.get(distribution);
		return this.distribution;
	}

	public HumanResources getHumanResources() {

		Log.get(humanResources);
		return this.humanResources;
	}

	public MarketResearch getMarketResearch() {
		Log.get(marketResearch);
		return this.marketResearch;
	}

	@Override
	public String toString() {
		return "Unternehmen in:" + this.location;

	}
}
