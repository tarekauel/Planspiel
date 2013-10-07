package Server;

import java.util.ArrayList;

import AspectLogger.NoGet;
import Constant.Constant;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 18:11
 */
// TODO: ueberpruefung ob Benefits noch gueltig, entfernen aus bookedbenefits,
// kosten die benefits per round verursachen
public class HumanResources extends DepartmentRoundSensitive {

	// Lohn pro Runde pro Mitarbeiter
	private TWage wagePerRound;

	// Anzahl Mitarbeiter
	private int countEmployees;

	// Gesamtkosten Loehne
	private int wagesSum;

	private ArrayList<BenefitBooking> benefitBooking;
	private int workingHoursPerRound = 0;

	// ---------- Attribute zur Berechnung der Motivaiton
	// Lohn der Letzten Runde
	private TWage wageLastRound = null;

	// Summe der Benefitinvestitionen der letzten Runde
	private long sumBenefit = 0;

	public HumanResources(Company c) throws Exception {
		super(c, "Personal", Constant.DepartmentFixcost.HUMAN_RESOURCES);

		setCountEmployees(100); // TODO: Anpassen & in ini-File auslagern
		setWagePerRound(new TWage(900, GameEngine.getGameEngine().getRound())); // TODO:
																				// Anpassen
																				// &
																				// in
																				// ini-File
																				// auslagern
		this.wagesSum = calcWagesSum();
		// TODO: BENEFIT BOOKING!?
		// this.benefitBooking = new BenefitBooking();

		// HR bei MarketData registrieren, um den durchschnittslohn zu
		// uebermitteln
		MarketData.getMarketData().addHR(this);

	}

	public void bookBenefit(String name, int duration) throws Exception {

		Benefit benefit = Benefit.getBenefitByName(name);
		boolean benefitAlreadyBooked = false;

		for (BenefitBooking bB : benefitBooking) {

			if (bB.getBenefit().getName().equals(benefit.getName())) {
				benefitAlreadyBooked = true;
			}

		}

		if (benefitAlreadyBooked == false) {

			benefitBooking.add(new BenefitBooking(benefit, duration));

		} else if (benefitAlreadyBooked == true) {

			throw new Exception("Benefit bereits gebucht.");

		}

	}

	private int calcWagesSum() {

		return wagePerRound.getAmount() * this.countEmployees;
	}

	public void setWagePerRound(TWage wagePerRound) {

		this.wagePerRound = wagePerRound;
	}

	public void setCountEmployees(int countEmployees) {

		this.countEmployees = countEmployees;
	}

	public TWage getWagesPerHour() {

		return wagePerRound;
	}

	public int getCountEmployees() {

		return countEmployees;
	}

	public int getWagesSum() {

		return wagesSum;
	}

	public ArrayList<BenefitBooking> getBenefitBooking() {

		return benefitBooking;
	}

	public void increaseWorkingHour(int quantity) {

		workingHoursPerRound++;
	}

	public int getWorkingHours() {

		return workingHoursPerRound;
	}

	@Override
	public void prepareForNewRound(int round) {

		workingHoursPerRound = 0;

	}

	/**
	 * Berechnet die Motivation in dieser Runde
	 * 
	 * @return Motivation in Prozent
	 * @throws Exception
	 */
	@NoGet
	public int getMotivation() throws Exception {

		// Gehaltsunterschied zur Vorrunde
		double diffWageToLastRound = 0.0;

		// Nur ab der zweiten Runde git es einen Unterschied
		if (GameEngine.getGameEngine().getRound() > 1) {
			// Unterschied in Prozent berechnen
			// (Neuer Lohn - Alter Lohn) / (Alter Lohn)
			diffWageToLastRound = (wagePerRound.getAmount() - wageLastRound
					.getAmount()) / (double) wageLastRound.getAmount();
		}

		// Einfluss auf die Motivation durch den Lohn zur Vorrunde
		// Berechnung je nach Positiv oder Negativ unterscheiden
		double influenceWageToLastRound = ((diffWageToLastRound < 0.0) ? 
				1-Math.pow((diffWageToLastRound*Constant.HumanResources.IMPACT_DIFF_NEG), 2)
				: 1+Math.sqrt(diffWageToLastRound*Constant.HumanResources.IMPACT_DIFF_POS));
		

		// Gehaltsunterschied zur Gruppe
		// ( Eigener Lohn - Durchschnitt) / Durchschnitt
		double diffWageToAverage = ((double) (((double) wagePerRound
				.getAmount() / wagePerRound.getWageLevel()) - MarketData
				.getMarketData().getAvereageWage().getAmount()) / MarketData
				.getMarketData().getAvereageWage().getAmount());
		
		// Einfluss auf die Motivaiton durch den Unterschied zum Markt
		// Berechnung je nach Positiv oder Negative unterschiedlich
		double influenceWageToAverage = ((diffWageToAverage < 0.0) ? 
				1-Math.pow((diffWageToAverage*Constant.HumanResources.IMPACT_DIFF_NEG), 2)
				: 1+Math.sqrt(diffWageToAverage*Constant.HumanResources.IMPACT_DIFF_POS));

		// Motivation gewichtet berechnen
		double motivation = influenceWageToLastRound*Constant.HumanResources.IMPACT_DIFF_INTERNAL
							+ influenceWageToAverage*Constant.HumanResources.IMPACT_DIFF_MARKET;
		
		//TODO Benfitsberechnung noch hinzufuegen
		
		return (int) Math.floor(motivation);
	}
}
