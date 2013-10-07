package Server;

import Constant.Constant;
import Logger.Log;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 17:24
 * TODO: checkmethoden!
 */

public class BankAccount {

	private long bankBalance;
/**
 * erstellt ein neues Bankkonto
 * @param bankBalance Startguthaben
 * @exception tritt auf, wenn bankBalance negativ oder 0
 */
	public BankAccount() {
		
		//CheckAmount wirft Exception falls negative Zahl
		checkAmount(Constant.BankAccount.START_CAPITAL);
					
		this.bankBalance = Constant.BankAccount.START_CAPITAL;
	}

	public long getBankBalance() {
		
		return bankBalance;
	}

	private void setBankBalance(long bankBalance) {
		
		
		this.bankBalance = bankBalance;
	}
	/**
	 * 
	 * @param amount der zugebucht werden soll
	 * @exception wirft exception falls amount <= 0 
	 */
	public void increaseBalance(long amount) {
		
		checkAmount(amount);
		long newBankBalance = getBankBalance() + amount;
		setBankBalance(newBankBalance);

	}

	/**
	 * Bucht den Betrag vom Konto des Spielers nach vorheriger Überprüfung ab
	 * 
	 * @param amount
	 *            Betrag der abgebucht werden soll ( > 0 )
	 * @return true: Betrag wurde abgebucht
	 *         false: Betrag konnte nicht abgebucht werden
	 * @exception falls amount negativ
	 */
	public boolean decreaseBalance(long amount) {
		
		checkAmount(amount);
		if (amount <= bankBalance) {
			long newBankBalance = getBankBalance() - amount;
			setBankBalance(newBankBalance);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Guthaben:" + this.bankBalance;
	}

	private void checkAmount(long amount) throws IllegalArgumentException {
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount darf nicht <= 0 sein");
		}
	}
}
