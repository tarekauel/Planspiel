package Server;

import java.util.ArrayList;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 19:07
 */

public class BenefitBooking {

    private Benefit benefit;
    private int duration;                                           //Dauer an gebuchten Runden
    private int startInRound;                                       //Runde in der das Benefit beginnt
    private int remainingRounds;                                    //Verbleibende Runden | Muss jede Runde neu berechnet werden
    private int costsSum;                                           //Kosten insgesamt für das gebuchte Benefit
    private static ArrayList<BenefitBooking> bookedBenefits;        //Alle derzeit gebuchten Benefits

    private BenefitBooking(Benefit benefit, int duration) {

        this.benefit = benefit;
        this.duration = duration;
        this.startInRound = GameEngine.getGameEngine().getRound()+1;
        this.remainingRounds = duration;
        this.costsSum = calcCostsSum();

    }

    public static void bookBenefit(Benefit benefit, int duration) throws Exception {

        boolean benefitAlreadyBooked = false;

        for(BenefitBooking bB : bookedBenefits){

            if (bB.getBenefit().getName().equals(benefit.getName())){
                benefitAlreadyBooked = true;
            }

        }

        if (benefitAlreadyBooked == false){

            bookedBenefits.add(new BenefitBooking(benefit, duration));

        } else if (benefitAlreadyBooked == true){

            throw new Exception("Benefit bereits gebucht.");

        }

    }

    public static void calculateRemainingRounds(){

        for(BenefitBooking bB : bookedBenefits){

            if (GameEngine.getGameEngine().getRound() == bB.getStartInRound()){

                bB.remainingRounds = bB.duration-1;

            } else if (GameEngine.getGameEngine().getRound() > bB.getStartInRound()){

                bB.remainingRounds = ( ( GameEngine.getGameEngine().getRound() - bB.getStartInRound() ) - ( bB.duration-1 ) ) * (-1);

            }

        }

    }

    public static int calcCostsSum(){

        int costssum = 0;

        for(BenefitBooking bB : bookedBenefits){

            costssum += bB.getBenefit().getCostsPerRound() * bB.getDuration();

        }

        return costssum;

    }

    public static ArrayList<BenefitBooking> getBookedBenefits(){
        return bookedBenefits;
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
        return remainingRounds;
    }

    public int getCostsSum() {
        return costsSum;
    }

}
