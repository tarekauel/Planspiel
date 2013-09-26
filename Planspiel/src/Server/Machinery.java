/**
 * 
 */
package Server;

import java.util.Random;

/**
 * @author Lars
 * 
 *         Klasse f�r die Maschine mit Attributen f�r das level. Hiervon
 *         abgeleitet bietet die Klasse an, wieviel eine Maschine produzieren
 *         kann und wie gro� die Auslastung ist
 */
public class Machinery {
	private int level; //Maschinen ausbaustufe 
	private Production production; // H�lt die Produktions abteilung
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
	 *            enth�lt Referenz auf die Produktionsabteilung wird ben�tigt um
	 *            die Belastung zu berechnen
	 */
	public Machinery(Production p) {

		level = 1;
		this.production = p;

	}
/**
 * 
 * @param quantity zu produzierende Menge
 * @return tats�chlich produzierte Menge
 */
	public int produce(int quantity) {
		// diese variable wird nachher returned
		int produce = 0;
		//Berechne den Ausschuss(hole den prozentualen Anteil an M�ll)
		
		produce = (int)(quantity * (percentJunk()/100));
				
		return produce;
	}
/**
 * Gibt einen Prozentsatz an Ausschuss an
 * 
 * @return Zahl zwischen 0 und 12
 */
	private int percentJunk() {
		// Zufallsgenerator holen
		Random r = new Random();
		// zufallszahl:
		int zz;
		//maximaler prozentualer Ausschuss
		int max;
		//Maximaler Ausschuss in % sind zwischen 2 und 12 
		max = (12-level);
		//Zufallszahl zwischen 0 und max:
		zz = r.nextInt(max);
		//Gib die Zahl zur�ck
		return zz;
	}

	/**
	 * Gibt die Maximal produzierbare Menge an
	 * 
	 * @return Maschinenkapazit�t als Integer
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
	 * Erh�ht die Ausbaustufe der Maschine um 1
	 * 
	 * @return true, falls erfolgreiche Erh�hung false, falls maschinenlvl
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
	 * Berechnet aus der produzierten St�ckzahl die Auslastung
	 * 
	 * @return gibt die Auslastung in Prozent an, als integer
	 */
	public int percentageOfUsage() {
		// Hole die zu produzierenden Mengen aus den Produktionsauftr�gen
		int produced = production.getProducedQuantity();
		// Hole aus dem internen Bereich die Kapazit�t der Maschine
		int maxProduce = capacityArray[level];

		double result = produced / maxProduce;

		result = result * 100;

		return (int) result;
	}
	
	/**
	 *  Berechnet sich aus Ausbaustufe zum Quadrat mal 150�
	 * 
	 * @return gibt die Fixkosten an
	 */
	public int getCosts(){
		
		
		return (level * level) * 15000;
				
	}

}
