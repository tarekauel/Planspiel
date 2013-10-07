package Server;

import java.util.ArrayList;

import AspectLogger.LogThis;
import Constant.Constant;
import Logger.Log;

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
		Log.method(new Object[] { c });
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
		Log.methodExit();

	}

	public void bookBenefit(String name, int duration) throws Exception {
		Log.method();
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
		Log.methodExit();
	}

	private int calcWagesSum() {
		Log.get(wagePerRound.getAmount() * this.countEmployees);
		return wagePerRound.getAmount() * this.countEmployees;
	}

	public void setWagePerRound(TWage wagePerRound) {
		Log.method(wagePerRound);
		this.wagePerRound = wagePerRound;
	}

	public void setCountEmployees(int countEmployees) {
		Log.method(countEmployees);
		this.countEmployees = countEmployees;
	}

	public TWage getWagesPerHour() {
		Log.get(wagePerRound);
		return wagePerRound;
	}

	public int getCountEmployees() {
		Log.get(countEmployees);

		return countEmployees;
	}

	public int getWagesSum() {
		Log.get(wagesSum);
		return wagesSum;
	}

	public ArrayList<BenefitBooking> getBenefitBooking() {
		Log.get(benefitBooking);
		return benefitBooking;
	}

	public void increaseWorkingHour(int quantity) {
		Log.method(quantity);
		workingHoursPerRound++;
	}

	public int getWorkingHours() {
		Log.get(workingHoursPerRound);
		return workingHoursPerRound;
	}

	@Override
	public void prepareForNewRound(int round) {
		Log.method(round);
		workingHoursPerRound = 0;

	}

	/**
	 * Berechnet die Motivation in dieser Runde
	 * 
	 * @return Motivation in Prozent
	 * @throws Exception
	 */
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

		// Gehaltsunterschied zur Gruppe
		// ( Eigener Lohn - Durchschnitt) / Durchschnitt
		double diffWageToAverage = ((double) (((double) wagePerRound
				.getAmount() / wagePerRound.getWageLevel()) - MarketData
				.getMarketData().getAvereageWage().getAmount()) / MarketData
				.getMarketData().getAvereageWage().getAmount());
		
		
		return 0;
	}
}
