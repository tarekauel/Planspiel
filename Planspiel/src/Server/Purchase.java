package Server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Die Purchase-Klasse stellt die Einkaufsabteilung des Unternehmens dar. Sie
 * kann an den beschaffungsmarkt neue Anfragen stellen und die Antworten
 * daraufhin mit der Angabe der gewuenschten Menge annehmen.
 * 
 * @author Felix
 * 
 */

public class Purchase extends Department {
/**
 * Name wird vom Konstruktor gesetzt.
 * @param c Company, Unternehmensreferenz
 * @param fix FixKosten
 * @throws Exception falls keine Korrekte erstellung
 */
	public Purchase(Company c, int fix) throws Exception {
		super(c, "Einkauf", fix);
	}

	/**
	 * privater Konstruktor, dass niemand einen falschen Abteilungsnamen eingibt
	 * 
	 * @param c Company = Unternehmen
	 * @param n Name = Abteilungsname
	 * @param f fixCosts = Fix Kosten der Abteilung
	 * @throws Exception falls die Abteilung nicht korrekt erstellt werden konnte
	 */
	private Purchase(Company c, String n, int f) throws Exception {
		super(c, n, f);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Request> listOfRequests = new ArrayList<Request>();

	/**
	 * erstellt eine neue Anfrage an den Beschaffungsmarkt
	 * 
	 * @param resource
	 *            : Für eine spezielle resource(Wafer oder Case inklusive
	 *            Qualität)
	 * @throws Exception
	 *             : falls die Resource null ist
	 */

	public void createRequest(Resource resource) throws Exception {

		if (resource == null) {
			throw new NullPointerException(
					"Resource is null! Class Purchase Method createRequest");
		}

		Request request = Request.create(resource);
		// Request request = Request.create(resource);
		listOfRequests.add(request);

	}

	/**
	 * Die SupplierOffer wird unter Angabe eine gewuenschten Menge angenommen
	 * 
	 * @param supplierOffer
	 *            : Offer auf die sich bezogen wird
	 * @param quantity
	 *            : Menge, die gewuenscht ist
	 * @throws Exception
	 *             : falls SupplierOffer null ist oder die Anzahl kleiner null.s
	 */

	public void acceptSupplierOffer(SupplierOffer supplierOffer, int quantity)
			throws Exception {
		if (supplierOffer == null) {
			throw new NullPointerException(
					"supplierOffer is null! Class Purchase Method acceptSupplierOffer");
		}

		if (quantity <= 0) {
			throw new IOException(
					"Quantity is 0 or lower! Class Purchase Method acceptSupplierOffer");
		}

		Resource resource = supplierOffer.getResource();
		if (resource == null) {
			throw new NullPointerException(
					"resource of supplierOffer is null! Class Purchase Method acceptSupplierOffer");
		}

		Storage storage = this.getCompany().getStorage();
		storage.store(resource, quantity);
	}

	public ArrayList<Request> getListOfRequest() {
		return listOfRequests;
	}// getListOfRequest

}
