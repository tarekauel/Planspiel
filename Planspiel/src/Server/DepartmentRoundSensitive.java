package Server;

import Logger.Log;

/**
 * @author Tarek
 *
 */
public abstract class DepartmentRoundSensitive extends Department {

	/**
	 * Konstruktor, der die Abteilung automatisch bei der GameEngine anmeldet
	 * @param c Firma, zu der die Abteilung geh�rt
	 * @param n Name der Abteilung
	 * @param f Fixkosten der Abteilung
	 * @throws Exception
	 */
	public DepartmentRoundSensitive(Company c, String n, int f) throws Exception {
		super(c, n, f);
		Log.method();
		GameEngine.getGameEngine().addSensitiveDepartment(this);
		Log.methodExit();
	}

	/**
	 * Gibt den Abteilungen die M�glichkeit Initialisierungen durchzuf�hren
	 * Wird zu Beginn der Runde von der GameEngine aufgerufen
	 * @param round Wird mit gegeben, um gegebenfalls Dinge zu pr�fen
	 */	
	public abstract void prepareForNewRound(int round);
	
}