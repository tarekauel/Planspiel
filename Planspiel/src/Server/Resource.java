package Server;

/**
 * 
 * @author D059270 Resorce erbt von Prodct und kann nur ein Wafer oder ein
 *         Geh�use sein.
 */
public class Resource extends Product {

	/**
	 * Fachlicher Konstruktor: Liefert eine neue Resorce zur�ck, falls die
	 * Eingaben valide waren. Sonst null.
	 * 
	 * @param quality
	 * @param costs
	 * @param name
	 * @return
	 */
	public static Resource create(int quality, int costs, String name) {
		if (!checkNameIsValid(name)) {
			return null; // Kein valider Name
		}
		try {
			Resource resource = new Resource(quality, name, costs);
			return resource;
		} catch (Exception e) {
			return null; // Kosten oder Qualit�t ist nicht valide.
		}

	}

	/**
	 * privater Konstruktor, der den Oberklassenteilkonstruktor von Product
	 * aufruft.
	 * 
	 * @param quality
	 * @param name
	 * @param costs
	 * @throws Exception
	 */
	private Resource(int quality, String name, int costs) throws Exception {
		super(quality, name, costs);

	}

	/**
	 * Pr�ft ob der Name den validen Produktnamen (Wafer oder Geh�se)
	 * entspricht.
	 * 
	 * @param name
	 * @return
	 */
	private static Boolean checkNameIsValid(String name) {
		if (name.equals("Wafer") || name.equals("Geh�use")) {
			return true;
		}
		return false;
	}

}
