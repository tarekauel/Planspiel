package Server;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 19:04
 */

public class TWage {

	private int amount;
	private int wageLevel;
	private int round;

	public TWage(int amount,int round) throws Exception {
		if (!checkAmount(amount)) {
			throw new IllegalArgumentException("Ung�ltiger Betrag");
		}
	
		if (checkRoundValid(round) == false) {
			// Runden check failed
			throw new IllegalArgumentException("Round invalid");
		}
		this.amount = amount;
		this.round = round;
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
		//TODO: Bearbeiten
	}

	public int getAmount() {
		return amount;
	}
	public int getRound(){
		return round;
	}

	public int getWageLevel() {
		return wageLevel;
	}
}