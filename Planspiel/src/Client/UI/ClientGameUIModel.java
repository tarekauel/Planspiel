package Client.UI;

import java.text.NumberFormat;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import KIGegner.UITestKI;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Message.GameDataMessageToClient;
import Message.GameDataMessageToClient.DistributionToClient;
import Message.GameDataMessageToClient.DistributionToClient.OfferToClient;
import Message.GameDataMessageToClient.HumanResourcesToClient;
import Message.GameDataMessageToClient.HumanResourcesToClient.BenefitBookingToClient;
import Message.GameDataMessageToClient.HumanResourcesToClient.PossibleBenefit;
import Message.GameDataMessageToClient.ProductionToClient;
import Message.GameDataMessageToClient.ProductionToClient.ProductionOrderToClient;
import Message.GameDataMessageToClient.PurchaseToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient.SupplierOfferToClient;
import Message.GameDataMessageToClient.StorageToClient;
import Message.GameDataMessageToClient.StorageToClient.StorageElementToClient;

public class ClientGameUIModel {

	/**
	 * General
	 */

	private static GameDataMessageToClient in = UITestKI.data;

	private int round;
	private int maxRounds = 20;
	private final ObservableList<Request> purchaseRequestTableData = FXCollections
			.observableArrayList();
	private final ObservableList<SupplierOffer> purchaseOffersTableData = FXCollections
			.observableArrayList();
	private final ObservableList<ProductionOrder> productionOrdersTableData = FXCollections
			.observableArrayList();
	private final ObservableList<StoragePosition> storagePositionsTableData = FXCollections
			.observableArrayList();
	private final ObservableList<Offer> offerTableData = FXCollections
			.observableArrayList();
	private final ObservableList<BenefitBooking> benefitBookingTableData = FXCollections.observableArrayList();
	
	private final ObservableList<Benefit> benfitBoxData = FXCollections.observableArrayList();

	private ArrayList<RequestFromClient> requests = new ArrayList<RequestFromClient>();

	private static NumberFormat nFormatter = NumberFormat.getCurrencyInstance();

	public static GameDataMessageToClient getIn() {
		return in;
	}

	public static NumberFormat getnFormatter() {
		return nFormatter;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getMaxRounds() {
		return maxRounds;
	}

	public void setMaxRounds(int maxRounds) {
		this.maxRounds = maxRounds;
	}

	public ArrayList<RequestFromClient> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<RequestFromClient> requests) {
		this.requests = requests;
	}

	public ObservableList<Request> getPurchaseRequestTableData() {
		return purchaseRequestTableData;
	}

	public ObservableList<SupplierOffer> getPurchaseOffersTableData() {
		return purchaseOffersTableData;
	}

	public ObservableList<ProductionOrder> getProductionOrdersTableData() {
		return productionOrdersTableData;
	}

	public ObservableList<StoragePosition> getStoragePositionsTableData() {
		return storagePositionsTableData;
	}

	public ObservableList<Offer> getOfferTableData() {
		return offerTableData;
	}
	
	public ObservableList<BenefitBooking> getBenefitBookingTableData() {
		return benefitBookingTableData;
	}
	
	public ObservableList<Benefit> getBenefitBoxData() {
		return benfitBoxData;
	}

	/**
	 * Parsing der GameDataMessageToClient
	 * 
	 * @param in
	 *            Message, die geparsed werden soll
	 */

	public void parseAnswerFromServer() {

		this.setRound(in.round);
		parsePurchase(in.purchase);
		parseProduction(in.production);
		parseStorage(in.storage);
		parseDistribution(in.distribution);
		parseHumanResources(in.humanResources);

	}

	private void parsePurchase(PurchaseToClient in) {

		for (int i = in.requests.size() - 1; i >= 0; i--) {

			RequestToClient req = in.requests.get(i);
			Request request = new Request(req, i);
			purchaseRequestTableData.add(request);

		}

	}

	private void parseProduction(ProductionToClient in) {

		for (int i = in.orders.size() - 1; i >= 0; i--) {

			ProductionOrderToClient pOrder = in.orders.get(i);
			ProductionOrder prodOrder = new ProductionOrder(pOrder, i);
			productionOrdersTableData.add(prodOrder);

		}

	}

	private void parseStorage(StorageToClient in) {

		for (int i = in.storageElements.size() - 1; i >= 0; i--) {

			StorageElementToClient stoElement = in.storageElements.get(i);
			StoragePosition stoPos = new StoragePosition(stoElement, i);
			storagePositionsTableData.add(stoPos);

		}

	}

	private void parseDistribution(DistributionToClient in) {
		for (OfferToClient offer : in.offers) {
			offerTableData.add(new Offer(offer));
		}
	}
	
	private void parseHumanResources(HumanResourcesToClient in) {
		for( BenefitBookingToClient b : in.benefits) {
			benefitBookingTableData.add( new BenefitBooking(b));
		}
		for(PossibleBenefit b: in.possibleBenefits) {
			benfitBoxData.add( new Benefit(b));
		}
	}

	/**
	 * Purchase
	 */

	public static class Request {

		private final SimpleStringProperty name;
		private final SimpleStringProperty quality;
		private final SimpleStringProperty status;
		private final SimpleStringProperty id;
		private final ArrayList<SupplierOffer> supplierOfferList;
		private static int lastId = 0;

		public Request(RequestToClient req, int id) {
			// TODO: "undefined" ersetzen
			this(req.name, req.quality + "", "undefined", id,
					req.supplierOffers);
		}

		public Request(String name, String quality) {
			this(name, quality, "Neu", lastId + 1, null);
		}

		private Request(String name, String quality, String status, int id,
				ArrayList<SupplierOfferToClient> offers) {
			this.name = new SimpleStringProperty(name);
			this.quality = new SimpleStringProperty(quality);
			this.id = new SimpleStringProperty(id + "");
			this.status = new SimpleStringProperty(status);
			supplierOfferList = new ArrayList<SupplierOffer>();
			if (offers != null) {
				int i = 0;
				for (SupplierOfferToClient o : offers) {
					supplierOfferList.add(new SupplierOffer(o, ++i));
				}
			}
			lastId = id;
		}

		public String getName() {
			return name.get();
		}

		public String getQuality() {
			return quality.get();
		}

		public String getStatus() {
			return status.get();
		}

		public String getId() {
			return id.get();
		}

		public ArrayList<SupplierOffer> getOffer() {
			return supplierOfferList;
		}
	}

	public void addRequest(RequestFromClient r) {
		requests.add(r);
	}

	public static class SupplierOffer {

		private final SimpleStringProperty name;
		private final SimpleStringProperty quality;
		private final SimpleStringProperty quantity;
		private final SimpleStringProperty price;
		private final SimpleStringProperty id;
		private final int round;
		private static int lastId = 0;

		public SupplierOffer(SupplierOfferToClient offer, int id) {
			this(offer.name, offer.quality + "", offer.orderedQuantity + "",
					offer.price + "", id, offer.round);
		}

		private SupplierOffer(String name, String quality, String quantity,
				String price, int id, int round) {
			this.name = new SimpleStringProperty(name);
			this.quality = new SimpleStringProperty(quality);
			// Falls Quantity 0 ist soll nichts erscheinen!
			quantity = (quantity.equals("0")) ? "" : quantity;
			this.quantity = new SimpleStringProperty(quantity);
			this.id = new SimpleStringProperty(id + "");

			// Währungsformatierung
			long priceTmp = Long.parseLong(price);
			String priceFormatted = nFormatter.format(priceTmp / 100.0);
			this.price = new SimpleStringProperty(priceFormatted);

			this.round = round;
			lastId = id;
		}

		public String getName() {
			return name.get();
		}

		public String getQuality() {
			return quality.get();
		}

		public String getQuantity() {
			return quantity.get();
		}

		public String getPrice() {
			return price.get();
		}

		public String getId() {
			return id.get();
		}

		public int getRound() {
			return round;
		}
	}

	/**
	 * Production
	 */

	public static class ProductionOrder {

		private final SimpleStringProperty id;
		private final SimpleStringProperty qualityWafer;
		private final SimpleStringProperty qualityCase;
		private final SimpleStringProperty targetQuantity;
		private final SimpleStringProperty qualityPanel;
		private final SimpleStringProperty actualQuantity;
		private final SimpleStringProperty costsPerUnit;
		// private final StorageElementToClient waferRef;
		// private final StorageElementToClient caseRef;
		private static int lastId = 0;

		public ProductionOrder(ProductionOrderToClient prodOrder, int id) {
			this(id, prodOrder.qualityWafer + "", prodOrder.qualityCase + "",
					prodOrder.quantity + "", prodOrder.qualityPanel + "",
					prodOrder.producedQuantity + "", prodOrder.costs + "");
		}

		public ProductionOrder(String qualityWafer, String qualityCase,
				String targetQuantity) {
			this(lastId + 1, qualityWafer, qualityCase, targetQuantity, "", "",
					"");
		}

		private ProductionOrder(int id, String qualityWafer,
				String qualityCase, String targetQuantity, String qualityPanel,
				String actualQuantity, String costsPerUnit) {
			this.id = new SimpleStringProperty(id + "");
			this.qualityWafer = new SimpleStringProperty(qualityWafer);
			this.qualityCase = new SimpleStringProperty(qualityCase);
			this.targetQuantity = new SimpleStringProperty(targetQuantity);
			this.qualityPanel = new SimpleStringProperty(qualityPanel);
			this.actualQuantity = new SimpleStringProperty(actualQuantity);

			// Währungsformatierung
			long costsPerUnitTmp = Long.parseLong(costsPerUnit);
			String costsPerUnitFormatted = nFormatter
					.format(costsPerUnitTmp / 100.0);
			this.costsPerUnit = new SimpleStringProperty(costsPerUnitFormatted);

			lastId = id;
		}

		public String getId() {
			return id.get();
		}

		public String getQualityWafer() {
			return qualityWafer.get();
		}

		public String getQualityCase() {
			return qualityCase.get();
		}

		public String getTargetQuantity() {
			return targetQuantity.get();
		}

		public String getQualityPanel() {
			return qualityPanel.get();
		}

		public String getActualQuantity() {
			return actualQuantity.get();
		}

		public String getCostsPerUnit() {
			return costsPerUnit.get();
		}

	}

	public class StoragePosition {

		private final SimpleStringProperty id;
		private final SimpleStringProperty ressource;
		private final SimpleStringProperty quality;
		private final SimpleStringProperty quantity;
		private final SimpleStringProperty costs;

		public StoragePosition(int id, String ressource, String quality,
				String quantity, String costs) {
			this.id = new SimpleStringProperty(id + "");
			this.ressource = new SimpleStringProperty(ressource);
			this.quality = new SimpleStringProperty(quality);
			this.quantity = new SimpleStringProperty(quality);

			// Währungsformatierung
			long costsTmp = Long.parseLong(costs);
			String costsFormatted = nFormatter.format(costsTmp / 100.0);
			this.costs = new SimpleStringProperty(costsFormatted);

		}

		public StoragePosition(StorageElementToClient stoElement, int id) {

			this(id, stoElement.type, stoElement.quality + "",
					stoElement.quantity + "", stoElement.costs + "");

		}

		public String getId() {
			return id.get();
		}

		public String getRessource() {
			return ressource.get();
		}

		public String getQuality() {
			return quality.get();
		}

		public String getQuantity() {
			return quantity.get();
		}

		public String getCosts() {
			return costs.get();
		}

	}

	/**
	 * Sales
	 */

	public static class Offer {

		private final SimpleStringProperty id;
		private final SimpleStringProperty product;
		private final SimpleStringProperty quality;
		private final SimpleStringProperty price;
		private final SimpleStringProperty quantity;
		private final SimpleStringProperty soldQuantity;
		private final SimpleStringProperty profit;
		private final SimpleStringProperty costs;

		private static int nextId = 1;

		public Offer(String quality, String quantity, String soldQuantity,
				String costs, String price) {
			this.id = new SimpleStringProperty(nextId + "");
			nextId++;
			this.product = new SimpleStringProperty("Panel");
			this.price = new SimpleStringProperty(price);
			this.quality = new SimpleStringProperty(quality + "");
			this.quantity = new SimpleStringProperty(quantity + "");
			this.soldQuantity = new SimpleStringProperty(soldQuantity + "");

			// Währungsformatierung
			long costsTmp = Long.parseLong(costs);
			String costsFormatted = nFormatter.format(costsTmp / 100.0);
			this.costs = new SimpleStringProperty(costsFormatted);
			double profit = Integer.parseInt(soldQuantity)
					* Integer.parseInt(price) / 100.0 - costsTmp / 100.0
					* Integer.parseInt(quantity);
			this.profit = new SimpleStringProperty(nFormatter.format(profit));

		}

		public Offer(OfferToClient offer) {
			this(offer.quality + "", offer.quantityToSell + "",
					offer.quantitySold + "", offer.costs + "", offer.price + "");
		}

		public String getId() {
			return id.get();
		}

		public String getProduct() {
			return product.get();
		}

		public String getQuality() {
			return quality.get();
		}

		public String getQuantity() {
			return quantity.get();
		}

		public String getSoldQuantity() {
			return soldQuantity.get();
		}

		public String getCosts() {
			return costs.get();
		}

		public String getPrice() {
			return price.get();
		}

		public String getProfit() {
			return profit.get();
		}

	}

	/**
	 * Benefit
	 */

	public static class BenefitBooking {

		private final SimpleStringProperty id;
		private final SimpleStringProperty benefit;
		private final SimpleStringProperty costs;
		private final SimpleStringProperty duration;

		private static int nextId = 1;

		public BenefitBooking(String benefit, String costs, String duration) {
			this.id = new SimpleStringProperty(nextId + "");
			nextId++;
			this.benefit = new SimpleStringProperty(benefit);
			this.duration = new SimpleStringProperty(duration);

			// Währungsformatierung
			long costsTmp = Long.parseLong(costs);
			String costsFormatted = nFormatter.format(costsTmp / 100.0);
			this.costs = new SimpleStringProperty(costsFormatted);

		}

		public BenefitBooking(BenefitBookingToClient benefit) {
			this(benefit.name, benefit.remainingRounds + "", "");
		}

		public String getId() {
			return id.get();
		}

		public String getBenefit() {
			return benefit.get();
		}

		public String getCosts() {
			return costs.get();
		}

		public String getDuration() {
			return duration.get();
		}

	}
	
	public static class Benefit {
		
		private final SimpleStringProperty benefit;
		private final SimpleStringProperty costs;

		public Benefit(String benefit, String costs) {	
	
			this.benefit = new SimpleStringProperty(benefit);

			// Währungsformatierung
			long costsTmp = Long.parseLong(costs);
			String costsFormatted = nFormatter.format(costsTmp / 100.0);
			this.costs = new SimpleStringProperty(costsFormatted);

		}

		public Benefit(PossibleBenefit benefit) {
			this(benefit.name, benefit.costsPerRound+"");
		}

		public String getBenefit() {
			return benefit.get();
		}

		public String getCosts() {
			return costs.get();
		}
		
		public String toString() {
			return benefit.get();
		}
		
	}

}
