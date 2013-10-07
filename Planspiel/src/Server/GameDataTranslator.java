package Server;

import java.util.ArrayList;

import Message.GameDataMessageFromClient;
import Message.GameDataMessageFromClient.DistributionFromClient.OfferFromClient;
import Message.GameDataMessageFromClient.HumanResourcesFromClient.BenefitBookingFromClient;
import Message.GameDataMessageFromClient.ProductionFromClient.ProductionOrderFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient.AcceptedSupplierOfferFromClient;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Message.GameDataMessageToClient;

public class GameDataTranslator {

	private static GameDataTranslator gameDataTranslator = null;

	private GameDataTranslator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton Kostruktor
	 * 
	 * @return Singletonobjekt
	 */
	public static GameDataTranslator getGameDataTranslator() {
		if (gameDataTranslator == null) {
			gameDataTranslator = new GameDataTranslator();
		}
		return gameDataTranslator;
	}

	public void convertGameDataMessage2Objects(
			ArrayList<GameDataMessageFromClient> gameDataMessages)
			throws Exception {

		for (GameDataMessageFromClient gameDataMessage : gameDataMessages) {
			Company company = findCompanyOfPlayer(gameDataMessage
					.getPlayerName());
			handlePurchaseRequests(gameDataMessage.purchase.requests, company);
			handleAcceptedSupplierOffers(
					gameDataMessage.purchase.acceptedSupplierOffers, company);
			handleProductionOrders(gameDataMessage.production.orders, company);
			handleDistributionOffers(gameDataMessage.distribution.offers,
					company);
			//handleBenefitBooking(gameDataMessage.humanResources.benefits,
			//		company);
			// In Creator verschieben. Weil Runde spaeter
			//handleMachineryLevel(gameDataMessage.increaseMachineLevel, company);
			handleWage(gameDataMessage.wage, company);
			handleMarketResearch(gameDataMessage.buyMarketResearch, company);

		}

	}

	private void handleMarketResearch(boolean buyMarketResearch, Company company) {
		if (buyMarketResearch) {
			// company.getMarketResearch()
		}

	}
/**
 * Hier wird der neue Lohn für nächste Runde gesetzt. 
 * @param wage
 * @param company
 * @throws Exception
 */
	private void handleWage(int wage, Company company) throws Exception {
		HumanResources hr = company.getHumanResources();
		hr.setWagePerRound(new TWage(wage, GameEngine.getGameEngine()
				.getRound()+1));

	}

	private void handleMachineryLevel(boolean increaseMachineLevel,
			Company company) {
		if (increaseMachineLevel) {
			company.getProduction().increaseMachineryLevel();
		}

	}

	private void handleBenefitBooking(ArrayList<BenefitBookingFromClient> benefits,
			Company company) throws Exception {
		HumanResources hr = company.getHumanResources();

		for (BenefitBookingFromClient benefit : benefits) {
			hr.bookBenefit(benefit.name, benefit.duration);
		}
	}

	private void handleDistributionOffers(ArrayList<OfferFromClient> offers,
			Company company) {
		Distribution distribution = company.getDistribution();
		for (OfferFromClient offer : offers) {
			distribution.createOffer(offer.quality, offer.quantityToSell,
					offer.price);
		}
	}

	/**
	 * Sucht die passenden Resources im Lager und erstellt mit ihnen die
	 * ProductionOrders
	 * 
	 * @param orders
	 *            vom Client angelegte Orders
	 * @param company
	 */
	private void handleProductionOrders(ArrayList<ProductionOrderFromClient> orders,
			Company company) {
		Storage storage = company.getStorage();
		for (ProductionOrderFromClient prodOrder : orders) {
			Resource wafer = null;
			Resource panelCase = null;
			for (Resource resource : storage.getAllResources()) {
				if (resource.getQuality() == prodOrder.qualityCase
						&& resource.getName().equals("Gehäuse")) {
					panelCase = resource;
				}
				if (resource.getQuality() == prodOrder.qualityWafer
						&& resource.getName().equals("Wafer")) {
					wafer = resource;
				}
				break;
			}// for
			company.getProduction().createProductionOrder(wafer, panelCase,
					prodOrder.quantity);
		}// for
	}

	/**
	 * nimmt die SupplierOffers an. vergleicht dabei die vom Client
	 * uebermittelten SupplierOffers mit denen auf dem Server. Akkzeptiert die
	 * auf dem Server liegende.
	 * 
	 * @param acceptedSupplierOffers
	 * @param company
	 * @throws Exception
	 */
	private void handleAcceptedSupplierOffers(
			ArrayList<AcceptedSupplierOfferFromClient> acceptedSupplierOffers,
			Company company) throws Exception {
		// geht alle acceptedSupOf des Clients durch
		for (AcceptedSupplierOfferFromClient acceptedSupOf : acceptedSupplierOffers) {
			// Sucht alle aktuellen Requests auf dem Server
			for (Server.Request request : company.getPurchase()
					.getListOfLatestRequest()) {
				// Sucht zum jeweiligen Request die 3 SupplierOffers auf dem
				// Server
				for (SupplierOffer supOf : request.getSupplierOffers()) {
					// sucht den Offer der client- und serverseitig
					// übereinstimmt
					if (supOf.equals(acceptedSupOf)) {
						// akzeptiert den serverseitigen
						company.getPurchase().acceptSupplierOffer(supOf,
								acceptedSupOf.quantity);
						break;
					}
				}
			}
		}

	}

	/**
	 * Liefert zu einem Spieler die Company-Referenz zurueck.
	 * 
	 * @param playerName
	 * @return Company
	 */
	private Company findCompanyOfPlayer(String playerName) {

		for (Player player : Server.Connection.Server.getServer()
				.getPlayerList()) {
			if (player.getName().equals(playerName)) {
				return player.getMyCompany();
			}
		}
		throw new IllegalArgumentException("Der Playername ist invalid!");

	}

	private void handlePurchaseRequests(ArrayList<RequestFromClient> requests,
			Company company) throws Exception {
		for (RequestFromClient request : requests) {
			company.getPurchase().createRequest(
					new Resource(request.quality, request.name, 0));
		}

	}

	public void createGameDataMessagesAndSend2Clients() {
		for (Player player : Server.Connection.Server.getServer()
				.getPlayerList()) {
			GameDataMessageToClient message= createGameDataMessageToClient(player);			
			player.getMyCompany().getBankAccount().getBankBalance();
			player.getServerConnection().writeMessage(message);
		}
	}

	private GameDataMessageToClient createGameDataMessageToClient(Player player) {
		String playerName = player.getName();
		
	
		Purchase purchase = new Purchase(requests);
		GameDataMessageToClient message = new GameDataMessageToClient(playerName, purchase, production, distribution, increaseMachineLevel, humanResources, wage, buyMarketResearch, cash, maxCredit);
		
	}

}
