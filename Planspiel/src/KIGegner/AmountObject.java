package KIGegner;

public class AmountObject {
	public final long amount;
	public final int round;
	public AmountObject(long amount, int round){
		this.round = round;
		this.amount = amount;
	}
	
	@Override
	public String toString(){
		return "Runde:" + round + " - Guthaben:" + amount;
	}
	
}
