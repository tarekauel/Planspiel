package Server;

import java.util.ArrayList;


import Message.GameDataMessageFromClient;
import Message.GameDataMessageToClient;

public class GameEngine {

	// Singleton referenz
	private static GameEngine engine;

	// Liste der Abteilungen, die zu Beginn jeder Runde für
	// Initialisierungszwecke aufgerufen werden müssen
	private ArrayList<DepartmentRoundSensitive> listSensitiveDepartments = new ArrayList<DepartmentRoundSensitive>();

	// Liste aller Unternehmen, die am Spiel teilnehmen
	private ArrayList<Company> listOfCompanys = new ArrayList<Company>();
	
	//Liste aller Unternehmen, die verloren haben
	private ArrayList<Company> listOfLosers = new ArrayList<Company>();



	// Rundennummer
	private int round = 1;

	public GameEngine() {
		
		engine = this;
		
	}

	/**
	 * Liefert die Instanz auf die Gameengine zurück, erstellt sie ggf
	 * 
	 * @return GameEngine Instanz auf die Gameengine
	 * 
	 */
	public static GameEngine getGameEngine() {
		if (engine == null) {
			engine = new GameEngine();
		}
		
		return engine;
	}

	/**
	 * @return aktuelle Runde
	 */
	public int getRound() {
		
		return round;
	}
	/**
	 * 
	 * @return gibt alles Companys der Spieler zurueck
	 */
	public ArrayList<Company> getListOfCompanys(){
		return this.listOfCompanys;
	}

	/**
	 * Startet die nächste Runde
	 * 
	 * @param gameDataList
	 *            Übergebene Eingabedaten der Spieler
	 * @throws Exception
	 */
	public ArrayList<GameDataMessageToClient> startNextRound(ArrayList<GameDataMessageFromClient> gameDataList)
			throws Exception {
		prepareAllDepartmentsForNewRound();
		parseClientData(gameDataList);

		// ArrayList<Player> allPlayer = Server.getServer().getPlayerList();
		for (Company company : listOfCompanys) {
			// ---------Storage---------------------
			company.getStorage().debitStorageCost();
			// ---------Production---------------------
			company.getProduction().produce();
			// ---------Fixkosten berechnen+abbuchen---------------------
			long costs = company.getMarketResearch().getFixCosts();
			costs += company.getDistribution().getFixCosts();
			costs += company.getHumanResources().getFixCosts();
			costs += company.getProduction().getFixCosts();
			costs += company.getPurchase().getFixCosts();
			costs += company.getStorage().getFixCosts();
			company.getBankAccount().decreaseBalance(costs);

		}
		CustomerMarket.getMarket().handleAllOffers();
		SupplierMarket.getMarket().handleRequest();
		//restliche Fachlogik (MaschineryLevel, Benefits) 
		
		// Creator: CustomerMarket.getMarket().getMarketShares();
		// Creator: CustomerMarket.getMarket().getAMarketPeak();
		// Creator: CustomerMarket.getMarket().getCMarketPeak();
		
		round++; // Runde hochzaehlen
		
		 return createDataForClient();		
	
		
	}

	private void parseClientData(ArrayList<GameDataMessageFromClient> gameDataList) throws Exception {
		GameDataTranslator.getGameDataTranslator().convertGameDataMessage2Objects(gameDataList);

	}
	
	private ArrayList<GameDataMessageToClient> createDataForClient() throws Exception {
		return GameDataTranslator.getGameDataTranslator().createGameDataMessages();

	}

	private void prepareAllDepartmentsForNewRound() throws Exception {
		for (DepartmentRoundSensitive d : listSensitiveDepartments) {
			d.prepareForNewRound(round);
		}
	}

	/**
	 * Für eine Abteilung hinzu, die zu Beginn der Runde einen
	 * Initialisierungsaufruf braucht
	 * 
	 * @param d
	 *            Abteilung, die der Aufrufliste hinzugefügt werden soll
	 */
	public void addSensitiveDepartment(DepartmentRoundSensitive d) {
		
		listSensitiveDepartments.add(d);
		
	}

	/**
	 * Fügt ein Unternehmen dem Spiel hinzu
	 * 
	 * @param c
	 *            Company, die hinzugefügt werden soll
	 */
	public void addCompany(Company c) {
		
		listOfCompanys.add(c);
		

	}
	/**
	 * Fuege einen Verlierer hinzu
	 * @param c die Firma die verloren hat
	 */
	public void addCompanyLost(Company c){
		listOfLosers.add(c);
		
	}
	/**
	 * 
	 * @return alle Verlierer
	 */
	public ArrayList<Company> getListOfLosers() {
		return listOfLosers;
	}

	@Override
	public String toString() {
		return "Gameengine Runde: " + this.round;
	}

}
