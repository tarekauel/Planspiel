package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Logger.Log;

public class Distribution extends DepartmentRoundSensitive {
	
	// Liste aller jemals erstellten Offers
	private ArrayList<Offer> listOfOffers = new ArrayList<Offer>();
	
	// Liste aller Offers dieser Runde
	private ArrayList<Offer> listOfLatesOffers = new ArrayList<Offer>();
	
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
		Log.newObj(new Object[] {c,fix});
		CustomerMarket.getMarket().addDistribution(this);
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
		Log.newObj(new Object[]{c,n,f});
	}

	public void createOffer(int quality, int quantityToSell, int price) {
		Log.method(new Object[]{quality,quantityToSell,price});
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
					storageElement, this);
			listOfOffers.add(offer);
			listOfLatesOffers.add(offer);
		} catch (IOException e) {
			e.getMessage(); // korrekt?? 
		}
		Log.methodExit();
	}// createOffer

	public ArrayList<Offer> getListOfOffers() {
		Log.get(listOfOffers);
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
		
		Log.get(listOfLatestOffers);
		
		return listOfLatestOffers;
	}
	
	/**
	 * Liefert eine Liste der aktuellen Offer zurück
	 * @return Liste der aktuellen Offer
	 */
	public ArrayList<Offer> getListOfLatestOffers() {
		Log.get(listOfLatesOffers);
		return listOfLatesOffers;
	}
	
	@Override
	public void prepareForNewRound( int round ) {
		Log.method(round);
		listOfLatesOffers = new ArrayList<Offer>();
	}
}
