package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Distribution extends Department {
	
	/**
	 * Regulärer Konstruktor der Distribution, 
	 * 
	 * @param c
	 *            Referenz des Unternehmens
	 * @param fix
	 *            Fixkosten
	 * @throws Exception
	 *             falls Abteilung nicht erstellt werden konnte
	 */
	public Distribution(Company c, int fix)throws Exception{
		super(c,"Verkauf",fix);
	}
	
	/**
	 * privater Konstruktor damit niemand eine Falsche Abteilung erzeugen kann
	 * (name falsch)
	 * 
	 * @param c
	 *            Referenz des Unternehmens
	 * @param n
	 *            Name der Abteilung
	 * @param f
	 *            Fixkosten der Abteilung
	 * @throws Exception
	 */
	private Distribution(Company c, String n, int f) throws Exception {
		super(c, n, f);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Offer> listOfOffers = new ArrayList<Offer>();

	public void createOffer(int quality, int quantityToSell, int price) {

		Storage storage = this.getCompany().getStorage();
		StorageElement storageElement = storage
				.getFinishedGoodByQuality(quality);

		if (storageElement == null) {
			throw new NullPointerException(
					"StorageElement could not found! Class Distribution Method createOffer");
		}

		int round = GameEngine.getGameEngine().getRound(); // Rundenzahl muss
															// von GameEngine
															// gegeben werden.
		int sold = 0; // die verkaufte Menge ist beim erstellen eines Offers
						// immer 0.
		try {
			Offer offer = new Offer(quantityToSell, price, round, sold,
					storageElement);
			listOfOffers.add(offer);
		} catch (IOException e) {
			e.getMessage(); // korrekt??
		}

	}// createOffer

	public ArrayList<Offer> getListOfOffers() {
		return listOfOffers;
	}// getListOfOffers

	public ArrayList<Offer> getSortedListOfLatestOffers() {
		ArrayList<Offer> listOfLatestOffers = new ArrayList<Offer>();
		for (Offer elem : listOfOffers) {
			if (elem.getRound() == GameEngine.getGameEngine().getRound()) {
				listOfLatestOffers.add(elem);
			}
		}
		Collections.sort(listOfLatestOffers);
		return listOfLatestOffers;
	}
}
