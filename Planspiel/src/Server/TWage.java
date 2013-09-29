package Server;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 19:04
 */

public class TWage {

	private int amount;
	private int wageLevel;
	private int round;

	public TWage(int amount) throws Exception {
		if (!checkAmount(amount)) {
			throw new IllegalArgumentException("Ung�ltiger Betrag");
		}
		if (checkRoundValid(round) == false) {
			// Runden check failed
			throw new IllegalArgumentException("Round invalid");
		}
	}

	private boolean checkAmount(int amount) {
		return (amount > 0);
	}

	private boolean checkRoundValid(int round) {

		return (round > 0);
	}

	private void calcWageLevel() {
		// Dazugehöriger Teil muss noch in GameEngine ausprogrammiert werden
		// Prinzip: Hole Gehälter aller Spieler aus HR und addiere
	}

	public int getAmount() {
		return amount;
	}

	public int getWageLevel() {
		return wageLevel;
	}
}
