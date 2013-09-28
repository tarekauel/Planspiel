package Server;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 17:22
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

	public Company() {

        this.location = new Location();
        this.bankAccount = new BankAccount(0); //TODO: Startwert des Vermögens setzen

	}
	
	public Purchase getPurchase(){
        return this.purchase;
    }

    public Production getProduction(){
        return this.production;
    }

    public Storage getStorage(){
        return this.storage;
    }

    public Distribution getDistribution(){
        return this.distribution;
    }

    public HumanResources getHumanResources(){
        return this.humanResources;
    }

    public MarketResearch getMarketResearch(){
        return this.marketResearch;
    }
	
}
