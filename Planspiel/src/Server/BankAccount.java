package Server;

import Logger.Log;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 17:24
 */

public class BankAccount {

	private int bankBalance;

	public BankAccount(int bankBalance) {
		Log.newObj(bankBalance);
		this.bankBalance = bankBalance;
	}

	public int getBankBalance() {
		Log.get(bankBalance);
		return bankBalance;
	}

	private void setBankBalance(int bankBalance) {
		Log.set(bankBalance);
		this.bankBalance = bankBalance;
	}

	public void increaseBalance(int amount) {
		Log.method();
		int newBankBalance = getBankBalance() + amount;
		setBankBalance(newBankBalance);

	}

	public void decreaseBalance(int amount) {
		Log.method();
		int newBankBalance = getBankBalance() - amount;
		setBankBalance(newBankBalance);

	}
@Override
	public String toString(){
		return "Guthaben:" + this.bankBalance;
	}
}
