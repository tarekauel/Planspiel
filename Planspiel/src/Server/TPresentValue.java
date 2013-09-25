package Server;
/**
 * Typ f�r PresentValue
 * Speichert ein Key-Value Pair von PresentValue und Runde
 * @author Lars
 *
 */

public class TPresentValue {
	final int round;
	final int presentValue;
	/**
	 * Konstruktor speichert bereits alle Daten.
	 * Ein nachtr�gliches setzen ist nicht m�glich
	 * 
	 * @param round aktuelle Runde
	 * @param PresentValue aktueller Barwert
	 * @throws Exception 
	 */
	public TPresentValue(int round, int presentValue) throws Exception {
		
		if (checkPresentValueValid(presentValue) == false){
			//Motivaions check failed
			throw new Exception("Present value invalid");
		}
		if (checkRoundValid(round)==false){
			//Runden check failed
			throw new Exception("Round invalid");
		}
		this.round = round;
		this.presentValue = presentValue;
		
	}
	public int getPresentValue(){
		return this.presentValue;
	}
	public int getRound(){
		return this.round;		
	}
	
	/**
	 * Pr�ft die Runde, ob sie positiv ist. 
	 * @param round gibt die Runde an
	 * @return true, falls die Runde plausibel, also gr��er als 0 ist.
	 */
	private boolean checkRoundValid(int round){
		if (round < 1){
			return false;
		}
		return true;
	}
	
	/**
	 * pr�ft ob die Motivation zwischen 0 und 1000 liegt
	 * (ehemals 0,0 und 999,9)
	 * @param motivation gibt die tempor�re Motivation an
	 * @return true, falls die Motivation valide
	 */
	private boolean checkPresentValueValid(int motivation){
		if(motivation > 0 && motivation < 1000){
			return true;
		}
		return false;
	}
}