package Server;

import java.util.ArrayList;

import Constant.Constant;
import Logger.Log;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 18:11
 */

public class HumanResources extends DepartmentRoundSensitive {

	private TWage wagePerRound; // Lohn pro Runde pro Mitarbeiter
	private int countEmployees; // Anzahl Mitarbeiter
	private int wagesSum; // Gesamtkosten Löhne
	private ArrayList<BenefitBooking> benefitBooking;
	private int workingHoursPerRound = 0;

	public HumanResources(Company c) throws Exception {
		super(c, "Personal",Constant.DepartmentFixcost.HUMAN_RESOURCES);
		Log.method(new Object[]{c});
		setCountEmployees(100); // TODO: Anpassen & in ini-File auslagern
		setWagePerRound(new TWage(900, GameEngine.getGameEngine().getRound())); // TODO:
																				// Anpassen
																				// &
																				// in
																				// ini-File
																				// auslagern
		this.wagesSum = calcWagesSum();
//TODO: BENEFIT BOOKING!?
		//this.benefitBooking = new BenefitBooking();
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

}
