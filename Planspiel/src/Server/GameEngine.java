package Server;

public class GameEngine {
	// singleton konzept
	private static GameEngine ge;

	public GameEngine() {
		// TODO Auto-generated constructor stub
		ge = this;
	}

	/**
	 * erzeugt gegebenenfalls neue Instanz der GameEngine
	 * 
	 * @return GameEngine (singleton konzept, alle kriegen dieselbe)
	 * 
	 */
	public static GameEngine getGameEngine() {
		if (ge == null) {
			ge = new GameEngine();
		}
		return ge;
	}

	/**
	 * 
	 * @return gibt die aktuelle Runde zurück
	 */
	public int getRound() {
		return 1;

	}
}
