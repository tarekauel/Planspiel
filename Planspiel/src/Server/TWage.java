package Server;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 19:04
 */

public class TWage {

    private int amount;
    private int wageLevel;

    public TWage(int amount) {
        this.amount = amount;
    }

    private void calcWageLevel(){
        //Dazugehöriger Teil muss noch in GameEngine ausprogrammiert werden
        //Prinzip: Hole Gehälter aller Spieler aus HR und addiere
    }

    public int getAmount() {
        return amount;
    }

    public int getWageLevel() {
        return wageLevel;
    }
}
