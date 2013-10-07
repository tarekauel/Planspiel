package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by: User: Lars Trey Date: 28.09.13 Time: 19:07
 */

public class Benefit {

	// Bezeichnung
	private final String name; 
	
	// Kosten pro Runde
	private final int costsPerRound; 

	// Alle buchbaren Benefits
	private static ArrayList<Benefit> bookableBenefits = new ArrayList<Benefit>(); 

	/**
	 * Erstellt ein neues Benefit
	 * 
	 * @param name
	 *            Name des zu erstellenden Benefits
	 * @param costsPerRound
	 *            Kosten pro Runde des Benefits
	 */
	private Benefit(String name, int costsPerRound) {
		if (!checkName(name))
			throw new IllegalArgumentException(
					"Name darf nicht null sein und nicht leer");
		this.name = name;
		if (!checkCostsPerRound(costsPerRound))
			throw new IllegalArgumentException("Kosten muesen >0 sein");
		this.costsPerRound = costsPerRound;
		
		if( getBenefitByName( name ) == null)	{	
			bookableBenefits.add(this);
		} else {
			throw new IllegalArgumentException("Benefit existiert bereits: " + name );
		}
	}

	public static Benefit getBenefitByName(String name) {
		for (Benefit benefit : bookableBenefits) {
			if (benefit.name.equals(name)) {
				return benefit;
			}
		}
		return null;
	}

	public static void createBenefit(String name, int costsPerRound)
			throws Exception {

		boolean benefitExisting = false;

		for (Benefit b : bookableBenefits) {

			if (b.getName().equals(name)) {
				benefitExisting = true;
			}

		}

		if (benefitExisting == false) {

			Benefit benefit = new Benefit(name, costsPerRound);
			bookableBenefits.add(benefit);

		} else if (benefitExisting == true) {

			throw new Exception("Benefit existiert bereits.");

		}

	}

	public static ArrayList<Benefit> getBookableBenefits() {

		return bookableBenefits;

	}

	public static void initBenefits() throws Exception {
		BufferedReader r = new BufferedReader( new FileReader("benefit.dat"));
		String line = null;
		while ((line = r.readLine()) != null) {
			// Zeile Teile trennen
			String[] lineParts = line.split(";", 2);
			
			// Nur wenn zwei Teile gefunden wurden, ist der Eintrag gueltig
			if(lineParts.length != 2)
				continue;
			
			new Benefit(lineParts[0], Integer.parseInt(lineParts[1]));			
		}

	}

	public String getName() {

		return name;
	}

	public int getCostsPerRound() {

		return costsPerRound;
	}

	@Override
	public String toString() {
		return name + " " + costsPerRound;
	}

	/**
	 * Ueberprueft ob der Name nicht leer und nicht null ist
	 * 
	 * @param name
	 *            zu pruefender Name
	 * @return Ergebnis der Pruefung
	 */
	private boolean checkName(String name) {
		if (name == null || name.equals(""))
			return false;
		return true;
	}

	/**
	 * Ueberprueft ob die Kosten groesser als 0 sind
	 * 
	 * @param c
	 *            Kosten, die geprueft werden sollen
	 * @return Ergebnis der Pruefung
	 */
	private boolean checkCostsPerRound(int c) {
		return (c > 0);
	}
	
	@Override
	public boolean equals(Object o) {
		Benefit b = (Benefit) o;
		// Prueft nur auf Namensgleichheit
		if( b.getName().equals(getName()))				
			return true;
		return false;
	}

}
