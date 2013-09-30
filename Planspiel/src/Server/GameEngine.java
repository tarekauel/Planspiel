package Server;

import java.util.ArrayList;

import Logger.Log;
import Message.GameDataMessage;


public class GameEngine {
	
	
	// Singleton referenz
	private static GameEngine engine;
	
	// Liste der Abteilungen, die zu Beginn jeder Runde f�r Initialisierungszwecke aufgerufen werden m�ssen
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
	 * Liefert die Instanz auf die Gameengine zur�ck, erstellt sie ggf
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
	 * Startet die n�chste Runde
	 * @param gameDataList �bergebene Eingabedaten der Spieler
	 */
	public void startNextRound(ArrayList<GameDataMessage> gameDataList) {
		Log.method();
		round++;
		
		for( DepartmentRoundSensitive d:listSensitiveDepartments) {
			d.prepareForNewRound(round);
		}
		Log.methodExit();
	}
	
	/**
	 * F�r eine Abteilung hinzu, die zu Beginn der Runde einen Initialisierungsaufruf braucht
	 * @param d Abteilung, die der Aufrufliste hinzugef�gt werden soll
	 */
	public void addSensitiveDepartment( DepartmentRoundSensitive d ) {
		Log.method(d);
		listSensitiveDepartments.add( d );
		Log.methodExit();
	}
	
	/**
	 * F�gt ein Unternehmen dem Spiel hinzu 
	 * @param c Company, die hinzugef�gt werden soll
	 */
	public void addCompany( Company c) {
		Log.method(c);
		listOfCompanys.add(c);
		Log.methodExit();
		
	}
	@Override
	public String toString(){
		return "Gameengine Runde: " + this.round;
	}
	
	
}
