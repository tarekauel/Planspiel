/**
 * 
 */
package Server;

import java.util.Random;

import Constant.Constant;
import Logger.Log;

/**
 * @author Lars
 * 
 *         Klasse für die Maschine mit Attributen für das level. Hiervon
 *         abgeleitet bietet die Klasse an, wieviel eine Maschine produzieren
 *         kann und wie groß die Auslastung ist
 */
public class Machinery {
	private int level; //Maschinen ausbaustufe 
	

	/**
	 * Erzeugt eine neue Maschine mit Ausbaustufe 1
	 * 
	 * @param p
	 *            enthält Referenz auf die Produktionsabteilung wird benötigt um
	 *            die Belastung zu berechnen
	 */
	public Machinery() {

		level = 1;
		
	}

/**
 * Gibt an, ob eine Produktion erfolgreich war oder nicht.
 * Regelt den Ausschuss
 * 
 * @return boolean, true, falls produziert wird, false, falls nicht
 */
	public boolean isJunk() {
		Log.get("Ausschuss");
		//Zufallszahlgenerator initialisieren
		Random r = new Random();
		//Chance auf Produktion: 84% + level.. also mindestens 85%
		
		return (r.nextInt(100)<(Constant.MACHINERY_JUNK_INIT+level))?false: true;
	
	
	}

	/**
	 * Gibt die Maximal produzierbare Menge an
	 * 
	 * @return Maschinenkapazität als Integer
	 */
	public int getMaxCapacity() {
		Log.get(Constant.MACHINERY_CAPACITY[level]);
		return Constant.MACHINERY_CAPACITY[level];
	}

	/**
	 * Gibt die Ausbaustufe der Maschine an
	 * 
	 * @return Maschinenlevel als Integer
	 */
	public int getLevel() {
		Log.get(level);
		return this.level;
	}

	/**
	 * Erhöht die Ausbaustufe der Maschine um 1
	 * 
	 * @return true, falls erfolgreiche Erhöhung false, falls maschinenlvl
	 *         bereits 10
	 */
	public boolean increaseLevel() {
		Log.method();
		if (level >= 10) {
			Log.methodExit();
			return false;
		}
		level++;
		Log.methodExit();
		return true;
	}

	/**
	 * Vermindert die Ausbaustufe der Maschine um 1
	 * 
	 * @return true, falls erfolgreiche Minderung false, falls Maschinenlvl 1
	 */
	public boolean decreaseLevel() {
		Log.method();
		if (level == 1) {
			Log.methodExit();
			return false;
		}
		level--;
		Log.methodExit();
		return true;
	}

	/**
	 *  Berechnet sich aus Ausbaustufe zum Quadrat und einem Faktor
	 * 
	 * @return gibt die Fixkosten an
	 */
	public int getCosts(){
		Log.get((level * level) * Constant.MACHINERY_FIX_COST);
		
		return (level * level) * Constant.MACHINERY_FIX_COST;	
	}
	/**
	 * Gibt Kosten für Hilfsstoffe an. je höher das Maschinen level, je niedriger die Kosten.
	 * @return Stückkosten auf der Maschine
	 */
	public int getPieceCosts(){
		Log.get(Constant.MACHINERY_PIECE_COST_BASIC * (11 - level));
		return Constant.MACHINERY_PIECE_COST_BASIC * (11 - level);
	}

}
