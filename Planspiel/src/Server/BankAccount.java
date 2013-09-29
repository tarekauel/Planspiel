package Server;

/**
 * Created by:
 * User: Lars Trey
 * Date: 28.09.13
 * Time: 17:24
 */

public class BankAccount {

    private int bankBalance;

    public BankAccount(int bankBalance){
        this.bankBalance = bankBalance;
    }

    public int getBankBalance(){
        return bankBalance;
    }

    private void setBankBalance(int bankBalance){
        this.bankBalance = bankBalance;
    }

    public void increaseBalance(int amount){

        int newBankBalance = getBankBalance() + amount;
        setBankBalance(newBankBalance);

    }

    public void decreaseBalance(int amount){

        int newBankBalance = getBankBalance() - amount;
        setBankBalance(newBankBalance);

    }

}
