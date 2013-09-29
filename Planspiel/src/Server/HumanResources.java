package Server;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 18:11
 */

public class HumanResources extends Department {

    private TWage wagePerRound;                         //Lohn pro Runde pro Mitarbeiter
    private int countEmployees;                         //Anzahl Mitarbeiter
    private int wagesSum;                               //Gesamtkosten Löhne
    private BenefitBooking benefitBooking;

    public HumanResources(Company c, int fix) throws Exception {
        super(c,"Personal",fix);
        setCountEmployees(100);                         //TODO: Anpassen & in ini-File auslagern
        setWagePerRound(new TWage(100,GameEngine.getGameEngine().getRound()));                //TODO: Anpassen & in ini-File auslagern
        this.wagesSum = calcWagesSum();
        this.benefitBooking = new BenefitBooking();
        
    }

    private int calcWagesSum(){
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

    public BenefitBooking getBenefitBooking() {
        return benefitBooking;
    }

}
