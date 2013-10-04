package Server;

import java.util.ArrayList;

import Logger.Log;
import Message.GameDataMessage;
import Server.Connection.Server;

public class GameEngine {

	// Singleton referenz
	private static GameEngine engine;

	// Liste der Abteilungen, die zu Beginn jeder Runde für
	// Initialisierungszwecke aufgerufen werden müssen
	private ArrayList<DepartmentRoundSensitive> listSensitiveDepartments = new ArrayList<DepartmentRoundSensitive>();

	// Liste aller Unternehmen, die am Spiel teilnehmen
	private ArrayList<Company> listOfCompanys = new ArrayList<Company>();

	// Rundennummer
	private int round = 1;

	public GameEngine() {
		Log.method();
		engine = this;
		Log.methodExit();
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
		Log.get(engine);
		return engine;
	}

	/**
	 * @return aktuelle Runde
	 */
	public int getRound() {
		Log.get(round);
		return round;
	}

	/**
	 * Startet die nächste Runde
	 * 
	 * @param gameDataList
	 *            Übergebene Eingabedaten der Spieler
	 * @throws Exception
	 */
	public void startNextRound(ArrayList<GameDataMessage> gameDataList)
			throws Exception {
		Log.method();
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

		// Creator: CustomerMarket.getMarket().getMarketShares();
		// Creator: CustomerMarket.getMarket().getAMarketPeak();
		// Creator: CustomerMarket.getMarket().getCMarketPeak();

		round++; // Runde hochzaehlen
		prepareAllDepartmentsForNewRound();
		Log.methodExit();
	}

	private void parseClientData(ArrayList<GameDataMessage> gameDataList) {
		// TODO Auto-generated method stub

	}

	private void prepareAllDepartmentsForNewRound() {
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
		Log.method(d);
		listSensitiveDepartments.add(d);
		Log.methodExit();
	}

	/**
	 * Fügt ein Unternehmen dem Spiel hinzu
	 * 
	 * @param c
	 *            Company, die hinzugefügt werden soll
	 */
	public void addCompany(Company c) {
		Log.method(c);
		listOfCompanys.add(c);
		Log.methodExit();

	}

	@Override
	public String toString() {
		return "Gameengine Runde: " + this.round;
	}

}
