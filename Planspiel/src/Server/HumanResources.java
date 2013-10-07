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

	// Liste der gebuchten Benefits
	private ArrayList<BenefitBooking> benefitBooking = new ArrayList<BenefitBooking>();

	// Anzahl der bereits gebuchten Arbeiterstunden
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

	/**
	 * Bucht ein Benefit ueber den Namen
	 * @param name Name des Benefits 
	 * @param duration Dauer in Runden
	 * @throws Exception Name muss ungleich null und nicht leer sein, duraiton > 0 und Das Benefit noch nicht gebucht worden sein
	 */
	public void bookBenefit(String name, int duration) throws Exception {

		// Liefert das Benefit aus der Liste mit allen benefits zurueck
		Benefit benefit = Benefit.getBenefitByName(name);
		
		// Alle gebuchten Benefits durchsuchen
		for (BenefitBooking bB : benefitBooking) {

			// Abgelaufene Buchungen und welche, die nur in dieser Runde noch gueltig sind, sollen ignoriert werden
			if( bB.getRemainingRounds() <= 1 && bB.getStartInRound() <= GameEngine.getGameEngine().getRound()) 
				continue;
			
			// Auf Gleichheit ueberpruefne
			if (bB.getBenefit().equals(benefit)) {
				//TODO durch Nachricht ersetzen?!
				throw new IllegalArgumentException("Beneift bereits gebucht");
			}
		}
		
		// Benefit zur Liste der gebuchten hinzufuegen
		benefitBooking.add(new BenefitBooking(benefit, duration));

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
		
		// Lohn der aktuellen Runde als Lohn der letzten Runde deklarieren
		// Ab beginn der ersten Runde
		if( wagePerRound != null)
			wageLastRound = wagePerRound;
		
		// Benefits vom Konto abbuchen
		for( BenefitBooking bP:benefitBooking) {
			// Nur Buchungen beruecksichtigen, die noch eine Restlaufzeit haben und entweder diese Runde beginnen oder bereits laufen
			if( bP.getRemainingRounds() > 0 && bP.getStartInRound() <= GameEngine.getGameEngine().getRound()) {

				// Betrag für diese Runde vom Konto abbuchen
				getCompany().getBankAccount().decreaseBalance(bP.getBenefit().getCostsPerRound() );
				
			}
		}
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
