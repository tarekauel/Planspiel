package Server;

import java.util.ArrayList;

import Logger.Log;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 19:07
 */

public class BenefitBooking {

	private Benefit benefit;
	private int duration; // Dauer an gebuchten Runden
	private int startInRound; // Runde in der das Benefit beginnt
	private int costsSum; // Kosten insgesamt fuer das gebuchte Benefit
	private static ArrayList<BenefitBooking> bookedBenefits; // Alle derzeit
																// gebuchten
																// Benefits

	public BenefitBooking() {
	}

	public BenefitBooking(Benefit benefit, int duration) {
		Log.newObj(new Object[] { benefit, duration });
		this.benefit = benefit;
		this.duration = duration;
		this.startInRound = GameEngine.getGameEngine().getRound() + 1;
		this.costsSum = calcCostsSum();

	}

	public static void bookBenefit(Benefit benefit, int duration)
			throws Exception {
		Log.method();
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
		Log.methodExit();
	}

	public static int calcCostsSum() {

		int costssum = 0;

		for (BenefitBooking bB : bookedBenefits) {

			costssum += bB.getBenefit().getCostsPerRound() * bB.getDuration();

		}
		Log.get(costssum);
		return costssum;

	}

	public static ArrayList<BenefitBooking> getBookedBenefits() {
		Log.get(bookedBenefits);
		return bookedBenefits;
	}

	public Benefit getBenefit() {
		Log.get(benefit);
		return benefit;
	}

	public int getDuration() {
		Log.get(duration);
		return duration;
	}

	public int getStartInRound() {
		Log.get(startInRound);
		return startInRound;
	}

	public int getRemainingRounds() {
		Log.get((duration
				- (GameEngine.getGameEngine().getRound() - startInRound) < 0) ? 0
				: (duration - (GameEngine.getGameEngine().getRound() - startInRound)));

		return (duration
				- (GameEngine.getGameEngine().getRound() - startInRound) < 0) ? 0
				: (duration - (GameEngine.getGameEngine().getRound() - startInRound));

	}

	public int getCostsSum() {
		Log.get(costsSum);
		return costsSum;
	}
	
	@Override
	public String toString(){
		return this.benefit.toString() + " dauert noch " + this.getRemainingRounds();
	}

}
