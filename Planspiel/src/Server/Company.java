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
        initDepartments();

	}

    //TODO: Sinnvolle Fixkosten pro Abteilung setzen
    //Frage: WO sollen Fixkosten gesetzt werden? In den Abteilungen selbst wäre betriebswirtschaftlich sinnvoller!
    private void initDepartments(){
        try {
            this.purchase = new Purchase(this, 1000000);
            this.production = new Production(this, 1000000);
            this.storage = new Storage(this, 1000000);
            this.distribution = new Distribution(this, 1000000);
            this.humanResources = new HumanResources(this, 1000000);
            this.marketResearch = new MarketResearch(this, 1000000);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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
