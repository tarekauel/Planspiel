package Server;

import java.util.ArrayList;

import Message.GameDataMessage;


public class GameEngine {
	
	
	// Singleton referenz
	private static GameEngine engine;
	
	// Rundennummer
	private int round = 0;

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
	 * Startet die nächste Runde
	 * @param gameDataList Übergebene Eingabedaten der Spieler
	 */
	public void startNextRound(ArrayList<GameDataMessage> gameDataList) {
		round++;
	}
}
