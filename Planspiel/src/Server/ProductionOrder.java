/**
 * 
 */
package Server;

/**
 * Klasse für den Produktionsauftrag
 * 
 * @author Lars
 * 
 */
public class ProductionOrder {
	// Anzahl der insgesamt erzeugten Produktionen
	private static int counter = 1;
	// ID des auftrags (Spieler unabhängige ID!)
	private int id;
	// Speichert die verwendeten Ressourcen
	private Resource wafer;
	private Resource cases;
	
	//Gibt die Gewichtung als ganzzahlige Prozentzahl an
	private int impactWafer = 80;
	private int impactCases = 20;
	
	//Parameter für die Anzahl der Wafer pro Panel:
	private int waferPerPanel = 54 ; //72 max, 36 min ~54 durchschnitt
	
	//maximaler Qualitätszuwachs durch FE+Motivation+Land:
	private int maxAddition = 20;
	// Speichert hinterher das erzugte Panel:
	private FinishedGood panel;
	// Herzustellende Menge laut Auftrag
	private int quantityToProduce;
	// tatsächlich hergestellte Menge:
	private int quantityProduced;

	/**
	 * Erzeugt neue Production order
	 * 
	 * @param wafer
	 *            Resource für den Wafer
	 * @param cases
	 *            Resource für das case
	 * @param quantity
	 */
	public ProductionOrder(Resource wafer, Resource cases, int quantity) {
		this.id = counter;
		// TODO: sicherheitsabfragen
		this.wafer = wafer;
		this.cases = cases;
		this.quantityToProduce = quantity;
		counter++;

	}

	/**
	 * abgeleitetes attribut
	 * 
	 * @return int Ausschuss
	 */
	public int getWaste() {
		return quantityToProduce - quantityProduced;

	}

	/**
	 * Gibt die Produzierte Menge an (die wird in produce gesetzt)
	 * 
	 * @return int produzierte Menge
	 */
	public int getProduced() {
		return quantityProduced;
	}

	/**
	 * Gibt die in auftragegebene Menge an
	 * 
	 * @return int Anzahl aus dem Konstruktor
	 */
	public int getRequested() {
		return quantityToProduce;

	}
	/**
	 * Returns the global ID
	 * @return
	 */
	public int getID(){
		return this.id;
	}

	/**
	 * Wird jede Runde aufgerufen und fertigt den Produktionsauftrag
	 * 
	 * @param m
	 *            Maschine, auf der produziert wird
	 * @param Zuschlag
	 * 			  Der Zuschlagssatz aus Motivation, Land und Forschung
	 */
	public void produce(Machinery m,Storage s, int Zuschlag){
		//Prüfe ob bereits produziert wurde:
		if (panel != null){
			//panel bereits gesetzt, also exisitert das Produkt schon im Storage
			return;
		}
		//Es wird in doubles gerechnet:
		double additionalFactor = Zuschlag/100;
		//durchschnittsqualität der Produkte mit Gewichtung:
		double midQuality = (wafer.getQuality()*impactWafer+cases.getQuality()*impactCases) / 100 ;
		//neue Qualität (nicht mehr als double)
		int newQuality = (int) (midQuality * additionalFactor) * 10;
		
		//Prüfe ob die neue Qualität durch den Zuschlag zu sehr verändert wurde
		newQuality = (newQuality-midQuality > maxAddition )? (int)(midQuality + maxAddition): newQuality;
		
		//Berechne herstellkosten:
		int costs = wafer.getCosts()*quantityToProduce * waferPerPanel + cases.getCosts()*quantityToProduce;
		//TODO: maschinenkosten einrechnen
		//Zieh die Storage elemente aus dem Storage ab:
		//hierbei wird quantityToProduce genutzt, da der ausschuss ja trotzdem hinterher im lager fehlt
		s.unstore(wafer, waferPerPanel*quantityToProduce);
		s.unstore(cases, quantityToProduce);
		//Alle daten sind jetzt aus dem Storage. neues Panel erzeugen
		panel= FinishedGood.create(newQuality, costs);
		//speicher das panel in storage:
		s.store(panel, quantityProduced);
			
		//Ausschuss abziehen
		quantityProduced = m.produce(quantityToProduce) ;
		
		
		
	}

}
