package Server;

import java.util.ArrayList;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 19:07
 */

public class Benefit {

    private String name;                                    //Bezeichnung
    private int costsPerRound;                              //Kosten pro Runde
    private static ArrayList<Benefit> bookableBenefits;     //Alle buchbaren Benefits

    private Benefit(String name, int costsPerRound){

        this.name = name;
        this.costsPerRound = costsPerRound;

    }

    public static void createBenefit(String name, int costsPerRound) throws Exception {

        boolean benefitExisting = false;

        for(Benefit b : bookableBenefits){

            if (b.getName().equals(name)){
                benefitExisting = true;
            }

        }

        if (benefitExisting == false){

            Benefit benefit = new Benefit(name, costsPerRound);
            bookableBenefits.add(benefit);

        } else if (benefitExisting == true){

            throw new Exception("Benefit existiert bereits.");

        }

    }

    public static ArrayList<Benefit> getBookableBenefits(){

        return bookableBenefits;

    }

    public static void initBenefits() throws Exception {

        //TODO: In ini-File auslagern
        createBenefit("Sportangebote", 10000);
        createBenefit("Kostenloses Essen", 10000);

    }

    public String getName() {
        return name;
    }

    public int getCostsPerRound() {
        return costsPerRound;
    }
}
