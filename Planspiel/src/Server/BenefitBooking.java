package Server;

import Constant.Constant.Log;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 19:07
 */

public class BenefitBooking {

	private final Benefit benefit;
	private final  int duration; // Dauer an gebuchten Runden
	private final int startInRound; // Runde in der das Benefit beginnt
	private final int costsSum; // Kosten insgesamt fuer das gebuchte Benefit
	

	public BenefitBooking(Benefit benefit, int duration) {
		
		this.benefit = benefit;
		this.duration = duration;
		this.startInRound = GameEngine.getGameEngine().getRound() + 1;
		this.costsSum = calcCostsSum();

	}

	public static void bookBenefit(Benefit benefit, int duration)
			throws Exception {
		
		boolean benefitAlreadyBooked = false;

		for (BenefitBooking bB : bookedBenefits) {

			if (bB.getBenefit().getName().equals(benefit.getName())) {
				benefitAlreadyBooked = true;
			}

		}

		if (benefitAlreadyBooked == false) {

			bookedBenefits.add(new BenefitBooking(benefit, duration));

		} else if (benefitAlreadyBooked == true) {
	
			throw new Exception("Benefit bereits gebucht.");

		}
		
	}

	public static int calcCostsSum() {

		int costssum = 0;

		for (BenefitBooking bB : bookedBenefits) {

			costssum += bB.getBenefit().getCostsPerRound() * bB.getDuration();

		}
		Log.get(costssum);
		return costssum;

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

	public int getCostsSum() {
		
		return costsSum;
	}
	
	@Override
	public String toString(){
		return this.benefit.toString() + " dauert noch " + this.getRemainingRounds();
	}

}
