package Server;

import tarekMuell.Company;

/**
 * Dieser Typ gibt den Marktanteil einer Firma wieder
 * 
 * @author Tarek
 * 
 */
// TODO: ist jetz ein Typ :D
public class TMarketShare {

	private Company	c;
	private int		marketShare;

	/**
	 * Konstruktor für Marketshare
	 * 
	 * @param c
	 *            Die Firma für den dieser Marktanteil gilt
	 * @param marketShare
	 *            der Marktanteil in ganze Prozent
	 */
	public TMarketShare(Company c, int marketShare) {
		if (!checkMarketShare(marketShare)) {
			throw new IllegalArgumentException("Marktanteil ist ungültig: '" + marketShare + "'");
		}
		if (c == null) {
			throw new IllegalArgumentException("Company-Referenz darf nicht auf NULL zeigen!");
		}
		this.c = c;
		this.marketShare = marketShare;
	}

	/**
	 * 
	 * @return liefert die Firma des Marktanteils zurück
	 */
	public Company getCompany() {
		return c;
	}

	/**
	 * 
	 * @return liefert den Marktanteil der Firma in ganzen Prozent zurück
	 */
	public int getMarketShare() {
		return marketShare;
	}

	/**
	 * Überprüft einen Marktanteil auf gültig
	 * 
	 * @param marketShare
	 *            Marktanteil
	 * @return true: gültig false: ungültig
	 */
	private boolean checkMarketShare(int marketShare) {
		// Marktanteil mit dem Interval [0;100]
		return (marketShare >= 0 && marketShare <= 100);
	}
}
