package Server;

import java.util.ArrayList;

import Logger.Log;
import Message.GameDataMessage;


public class GameEngine {
	
	
	// Singleton referenz
	private static GameEngine engine;
	
	// Liste der Abteilungen, die zu Beginn jeder Runde für Initialisierungszwecke aufgerufen werden müssen
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
	 * @param gameDataList Übergebene Eingabedaten der Spieler
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
	 * Für eine Abteilung hinzu, die zu Beginn der Runde einen Initialisierungsaufruf braucht
	 * @param d Abteilung, die der Aufrufliste hinzugefügt werden soll
	 */
	public void addSensitiveDepartment( DepartmentRoundSensitive d ) {
		Log.method(d);
		listSensitiveDepartments.add( d );
		Log.methodExit();
	}
	
	/**
	 * Fügt ein Unternehmen dem Spiel hinzu 
	 * @param c Company, die hinzugefügt werden soll
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
