package Server;
/**
 * Typ für PresentValue
 * Speichert ein Key-Value Pair von PresentValue und Runde
 * @author Lars
 *
 */

public class TPresentValue {
	final int round;
	final int presentValue;
	/**
	 * Konstruktor speichert bereits alle Daten.
	 * Ein nachträgliches setzen ist nicht möglich
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
	 * Prüft die Runde, ob sie positiv ist. 
	 * @param round gibt die Runde an
	 * @return true, falls die Runde plausibel, also größer als 0 ist.
	 */
	private boolean checkRoundValid(int round){
		if (round < 1){
			return false;
		}
		return true;
	}
	
	/**
	 * prüft ob die Motivation zwischen 0 und 1000 liegt
	 * (ehemals 0,0 und 999,9)
	 * @param motivation gibt die temporäre Motivation an
	 * @return true, falls die Motivation valide
	 */
	private boolean checkPresentValueValid(int motivation){
		if(motivation > 0 && motivation < 1000){
			return true;
		}
		return false;
	}
}