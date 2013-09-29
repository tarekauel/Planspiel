package Server;

import java.util.ArrayList;

import Logger.Log;

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
Log.newObj(new Object[] {name,costsPerRound});
//TODO: CHECKs einbauen
        this.name = name;
        this.costsPerRound = costsPerRound;

    }

    public static void createBenefit(String name, int costsPerRound) throws Exception {
Log.method();
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
        Log.methodExit();

    }

    public static ArrayList<Benefit> getBookableBenefits(){
    	Log.get(bookableBenefits);
        return bookableBenefits;

    }

    public static void initBenefits() throws Exception {
Log.method();

        //TODO: In ini-File auslagern
        createBenefit("Sportangebote", 10000);
        createBenefit("Kostenloses Essen", 10000);
        
        Log.methodExit();

    }

    public String getName() {
    	Log.get(name);
    	return name;
    }

    public int getCostsPerRound() {
    	Log.get(costsPerRound);
        return costsPerRound;
    }
    
    @Override
    public String toString(){
    	return name + " " + costsPerRound;
    }
    
}
