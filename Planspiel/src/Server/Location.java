package Server;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 17:16
 */

public class Location {

    private int advantage;
    private String country;
    private int purchasePrice;
    private int wageLevel;

    public Location(){

    }

    public int getAdvantage(){
        return this.advantage;
    }

    public String getCountry(){
        return this.country;
    }

    public int getPurchasePrice(){
        return this.purchasePrice;
    }

    public int getWageLevel(){
        return this.wageLevel;
    }

}
