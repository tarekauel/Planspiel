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

	/**
	 * Legt ein neues Unternehmen an
	 * 
	 * @param l
	 *            Location, Unternehmensstandort
	 * @throws Exception
	 *             falls falsche eingabe werte
	 * @exception IllegalArgumentException
	 */
	public Company(Location l) throws Exception {

		if (!checkLocation(l)) {
			throw new IllegalArgumentException("Ungültiger Standort");
		}

		// erzeuge Bankkonto mit 1 Mio Kapital
		bankAccount = new BankAccount(this);
		// setze Location

		this.location = l;
		// 'Kaufe die Location'
		bankAccount.decreaseBalance(l.getPurchasePrice());

		// Erzuege alle Abteilungen
		this.purchase = new Purchase(this);
		this.production = new Production(this);
		this.storage = new Storage(this);
		this.distribution = new Distribution(this);
		this.humanResources = new HumanResources(this);
		this.marketResearch = new MarketResearch(this);

		// Anmelden an der Gamengine
		GameEngine.getGameEngine().addCompany(this);

	}

	/**
	 * ueberprueft eine location
	 * 
	 * @param l
	 *            Location welche geprueft wird
	 * @return true, falls der Standort gefunden wurde
	 */
	private boolean checkLocation(Location l) {
		if (l != null) {
			return Location.getLocationByCountry(l.getCountry()) != null;
		}
		return false;
	}

	/**
	 * initialisiert eine neue Runde
	 * 
	 * @param round
	 *            Runde auf die sich vorbereitet wird
	 */
	public void initRound(int round) {

	}

	/**
	 * 
	 * @return gibt den Standort des Unternehmens zurueck
	 */
	public Location getLocation() {

		return this.location;
	}

	/**
	 * 
	 * @return liefert das Bank Konto
	 */
	public BankAccount getBankAccount() {

		return this.bankAccount;
	}

	/**
	 * 
	 * @return liefert den Einkauf
	 */
	public Purchase getPurchase() {

		return this.purchase;
	}

	/**
	 * 
	 * @return liefert die Produktion
	 */
	public Production getProduction() {

		return this.production;
	}

	/**
	 * 
	 * @return liefert das Lager
	 */
	public Storage getStorage() {

		return this.storage;
	}

	/**
	 * 
	 * @return Liefert den Verkauf
	 */
	public Distribution getDistribution() {

		return this.distribution;
	}

	/**
	 * 
	 * @return Liefert das Personal Management
	 */
	public HumanResources getHumanResources() {
		return this.humanResources;
	}

	/**
	 * 
	 * @return Liefert die Abteilung Marktforschung
	 */
	public MarketResearch getMarketResearch() {

		return this.marketResearch;
	}

	@Override
	public String toString() {
		return "Unternehmen in:" + this.location;

	}
}
