/**
 * 
 */
package Server;

import java.util.Random;

/**
 * @author Lars
 * 
 *         Klasse für die Maschine mit Attributen für das level. Hiervon
 *         abgeleitet bietet die Klasse an, wieviel eine Maschine produzieren
 *         kann und wie groß die Auslastung ist
 */
public class Machinery {
	private int level; //Maschinen ausbaustufe 
	private int[] capacityArray = { 500, // lvl 1
			1000, // lvl 2
			2000, // lvl 3
			4000, // lvl 4
			7000, // lvl 5
			10000, // lvl 6
			14000, // lvl 7
			19000, // lvl 8
			25000, // lvl 9
			35000 // lvl 10
	};

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
		//Zufallszahlgenerator initialisieren
		Random r = new Random();
		//Chance auf Produktion: 84% + level.. also mindestens 85%
		return (r.nextInt(100)<(84+level))?false: true;
		
	
	}

	/**
	 * Gibt die Maximal produzierbare Menge an
	 * 
	 * @return Maschinenkapazität als Integer
	 */
	public int getMaxCapacity() {
		return capacityArray[level];
	}

	/**
	 * Gibt die Ausbaustufe der Maschine an
	 * 
	 * @return Maschinenlevel als Integer
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Erhöht die Ausbaustufe der Maschine um 1
	 * 
	 * @return true, falls erfolgreiche Erhöhung false, falls maschinenlvl
	 *         bereits 10
	 */
	public boolean increaseLevel() {
		if (level >= 10) {
			return false;
		}
		level++;
		return true;
	}

	/**
	 * Vermindert die Ausbaustufe der Maschine um 1
	 * 
	 * @return true, falls erfolgreiche Minderung false, falls Maschinenlvl 1
	 */
	public boolean decreaseLevel() {
		if (level == 1) {
			return false;
		}
		level--;
		return true;
	}

	/**
	 *  Berechnet sich aus Ausbaustufe zum Quadrat mal 150€
	 * 
	 * @return gibt die Fixkosten an
	 */
	public int getCosts(){
		return (level * level) * 250000;	
	}
	/**
	 * Gibt Kosten für Hilfsstoffe an. je höher das Maschinen level, je niedriger die Kosten.
	 * @return Stückkosten auf der Maschine
	 */
	public int getPieceCosts(){
		return 1500 * (11 - level);
	}

}
