package Server;

/**
 * @author Lars
 * Abstrakte Klasse der Abteilung
 */
public abstract class Department {

	private int fixCosts;
	private String name;
	private Company company;

	/**
	 * 
	 * @param c Referenz auf das Unternehmen
	 * @param n Name der Abteilung
	 * @param f Cent Zahl der fixKosten
	 * @throws Exception
	 */
	public Department(Company c, String n, int f)throws Exception {
		if (checkName(n)!=true){
			throw new Exception("Ungï¿½ltiger Abteilungsname:"+n);
		}
		if (checkFixCosts(f)!=true){
			throw new Exception("Ungï¿½ltige Fixkosten:"+f);
		}
		if (c == null){
			throw new Exception("Null-Referenz Unternehmen");
		}
		this.fixCosts = f;
		this.company = c;
		this.name = n;

	}
	/**
	 *  Getter Methode
	 * @return Referenz auf das Unternehmen
	 */
	public Company getCompany(){
		return this.company;
	}
	/**
	 *  Getter Methode
	 * @return integer Zahl der Fix Koste (Cent Betrag)
	 */
	public int getFixCosts(){
		return this.fixCosts;
	}
	/**
	 *  Getter Methode
	 * @return String des Abteilungsnamen
	 */
	public String getName(){
		
		return this.name;
	}
	/**
	 * Gibt den Abteilungen die Möglichkeit Initialisierungen durchzuführen
	 * Wird zu Beginn der Runde über die Company gestartet
	 * @param round Wird mit gegeben, um gegebenfalls Dinge zu prüfen
	 */
	
	public void prepareForNewRound(int round){
		//Gibt allen leuten die Möglichkeit dinge zu initialiesieren
	}
	
	/**
	 * 
	 * @param n
	 *            Name der Abteilung
	 * @return true, falls Name in der Liste: "Einkauf", "Verkauf", "Lager",
	 *         "Resporting", "Forschung&Entwicklung", "Produktion", "Personal",
	 *         "Marktforschung"
	 */
	private boolean checkName(String n) {
		switch (n) {
			case "Einkauf":
			case "Verkauf":
			case "Lager":
			case "Resporting":
			case "Produktion":
			case "Personal":
			case "Marktforschung":

				return true;
			default:
				return false;
		}
	}
/**
 * 
 * @param f zu prï¿½fende fix Kosten
 * @return true, falls fix Kosten einen positvien Wert haben
 */
	private boolean checkFixCosts(int f){
		return (f>0);
	}
}
