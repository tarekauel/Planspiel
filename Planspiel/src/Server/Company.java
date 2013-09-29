package Server;

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
		//erzeuge Bankkonto mit 1 Mio Kapital
		bankAccount = new BankAccount(100000000);
		// setze Location
		this.location = l;
		// 'Kaufe die Location'
		bankAccount.decreaseBalance(l.getPurchasePrice());
		
		//Erzuege alle Abteilungen
		this.purchase = new Purchase(this, 1000000);
		this.production = new Production(this, 1000000);
		this.storage = new Storage(this, 1000000);
		this.distribution = new Distribution(this, 1000000);
		this.humanResources = new HumanResources(this, 1000000);
		this.marketResearch = new MarketResearch(this, 1000000);

	}
	
	public void initRound(int round){
		purchase.prepareForNewRound(round);
		production.prepareForNewRound(round);
		storage.prepareForNewRound(round);
		distribution.prepareForNewRound(round);
		humanResources.prepareForNewRound(round);
		marketResearch.prepareForNewRound(round);
		
	}
	
	
	public Location getLocation() {
		return this.location;
	}

	public BankAccount getBankAccount() {
		return this.bankAccount;
	}

	public Purchase getPurchase() {
		return this.purchase;
	}

	public Production getProduction() {
		return this.production;
	}

	public Storage getStorage() {
		return this.storage;
	}

	public Distribution getDistribution() {
		return this.distribution;
	}

	public HumanResources getHumanResources() {
		return this.humanResources;
	}

	public MarketResearch getMarketResearch() {
		return this.marketResearch;
	}

}
