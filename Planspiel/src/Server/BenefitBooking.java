package Server;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 19:07
 */

public class BenefitBooking {

	// gebuchter Benefit
	private final Benefit benefit;
	
	// Dauer an gebuchten Runden
	private final  int duration; 
	
	// Runde in der das Benefit beginnt
	private final int startInRound; 	

	public BenefitBooking(Benefit benefit, int duration) {
		
		this.benefit = benefit;
		this.duration = duration;
		this.startInRound = GameEngine.getGameEngine().getRound() + 1;
	}

	/**
	 * Liefet die Gesamtkosten für das Benefit zurueck
	 * @return
	 */
	public int getCostsSum() {
		return duration * benefit.getCostsPerRound();
	}	

	public Benefit getBenefit() {
		
		return benefit;
	}

	public int getDuration() {
		
		return duration;
	}

	public int getStartInRound() {
		
		return startInRound;
	}

	public int getRemainingRounds() {

		return (duration
				- (GameEngine.getGameEngine().getRound() - startInRound) < 0) ? 0
				: (duration - (GameEngine.getGameEngine().getRound() - startInRound));

	}
	
	@Override
	public String toString(){
		return this.benefit.toString() + " Restdauer: (" + this.getRemainingRounds()+")";
	}

}
