package Client.UI;

import java.util.ArrayList;

import aaaaa.GameTestConsole;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import KIGegner.KI;
import Message.GameDataMessageToClient;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Message.GameDataMessageToClient.ProductionToClient;
import Message.GameDataMessageToClient.PurchaseToClient;
import Message.GameDataMessageToClient.ProductionToClient.ProductionOrderToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient.SupplierOfferToClient;
import Message.GameDataMessageToClient.StorageToClient;
import Message.GameDataMessageToClient.StorageToClient.StorageElementToClient;

public class ClientGameUIModel {

	/**
	 * General
	 */
	
	private static GameDataMessageToClient in = KI.data;
	
	private int round;
	private int maxRounds = 20;
	private final ObservableList<Request> purchaseRequestTableData = FXCollections.observableArrayList();
	private final ObservableList<SupplierOffer> purchaseOffersTableData = FXCollections.observableArrayList();
	private final ObservableList<ProductionOrder> productionOrdersTableData = FXCollections.observableArrayList();
	private final ObservableList<StoragePosition> storagePositionsTableData = FXCollections.observableArrayList();
	
	private ArrayList<RequestFromClient> requests = new ArrayList<RequestFromClient>();
	
	public static GameDataMessageToClient getIn() {
		return in;
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

	/**
	 * Parsing der GameDataMessageToClient
	 * @param in Message, die geparsed werden soll
	 */
	
	public void parseAnswerFromServer(){
		
		this.setRound(in.round);
		parsePurchase(in.purchase);	
		parseProduction(in.production);
		parseStorage(in.storage);
		
	}	
	
	private void parsePurchase(PurchaseToClient in){	

		for(int i=in.requests.size()-1; i>=0; i--) {
			
			RequestToClient req = in.requests.get(i);			
			Request request = new Request(req, i);
			purchaseRequestTableData.add(request);
		
		}			
		
	}
	
	private void parseProduction(ProductionToClient in){	

		for(int i=in.orders.size()-1; i>=0; i--) {
			
			ProductionOrderToClient pOrder = in.orders.get(i);			
			ProductionOrder prodOrder = new ProductionOrder(pOrder, i);
			productionOrdersTableData.add(prodOrder);
		
		}			
		
	}
	
	private void parseStorage(StorageToClient in){	

		for(int i=in.storageElements.size()-1; i>=0; i--) {
			
			StorageElementToClient stoElement = in.storageElements.get(i);			
			StoragePosition stoPos = new StoragePosition(stoElement, i);
			storagePositionsTableData.add(stoPos);
		
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
			//TODO: "undefined" ersetzen
			this(req.name, req.quality+"", "undefined", id, req.supplierOffers);
		}
		
		public Request(String name, String quality) {
			this(name, quality, "Neu", lastId+1, null);
		}
		
		private Request(String name, String quality, String status, int id, ArrayList<SupplierOfferToClient> offers) {
			this.name = new SimpleStringProperty(name);
			this.quality = new SimpleStringProperty(quality);
			this.id = new SimpleStringProperty(id+"");
			this.status = new SimpleStringProperty(status);		
			supplierOfferList = new ArrayList<SupplierOffer>();
			if(offers != null) {
				int i=0;
				for( SupplierOfferToClient o:offers) {
					supplierOfferList.add( new SupplierOffer( o, ++i ));
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
		
		public SupplierOffer( SupplierOfferToClient offer, int id) {
			this(offer.name, offer.quality+"", offer.orderedQuantity+"", offer.price+"", id, offer.round);
		}
		
		private SupplierOffer(String name, String quality, String quantity, String price, int id, int round) {
			this.name = new SimpleStringProperty(name);
			this.quality = new SimpleStringProperty(quality);
			// Falls Quantity 0 ist soll nichts erscheinen!
			quantity = (quantity.equals("0")) ? "" : quantity;
			this.quantity = new SimpleStringProperty(quantity);
			this.id = new SimpleStringProperty(id+"");
			this.price = new SimpleStringProperty(price);	
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
//		private final StorageElementToClient waferRef;
//		private final StorageElementToClient caseRef;
		private static int lastId = 0;		
		
		public ProductionOrder(ProductionOrderToClient prodOrder, int id) {
			this(id, prodOrder.qualityWafer+"", prodOrder.qualityCase+"", prodOrder.quantity+"", prodOrder.qualityPanel+"", prodOrder.producedQuantity+"", prodOrder.costs+"");
		}
		
		public ProductionOrder(String qualityWafer, String qualityCase, String targetQuantity) {
			this(lastId+1, qualityWafer, qualityCase, targetQuantity, "", "", "");
		}
		
		private ProductionOrder(int id, String qualityWafer,
				String qualityCase,
				String targetQuantity,
				String qualityPanel,
				String actualQuantity,
				String costsPerUnit) {
			this.id = new SimpleStringProperty(id+"");
			this.qualityWafer = new SimpleStringProperty(qualityWafer);
			this.qualityCase = new SimpleStringProperty(qualityCase);
			this.targetQuantity = new SimpleStringProperty(targetQuantity);
			this.qualityPanel = new SimpleStringProperty(qualityPanel);
			this.actualQuantity = new SimpleStringProperty(actualQuantity);
			this.costsPerUnit = new SimpleStringProperty(costsPerUnit);
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
		
		public StoragePosition(int id,
				String ressource, String quality,
				String quantity, String costs) {
			this.id = new SimpleStringProperty(id+"");
			this.ressource = new SimpleStringProperty(ressource);
			this.quality = new SimpleStringProperty(quality);
			this.quantity= new SimpleStringProperty(quality);
			this.costs = new SimpleStringProperty(costs);
		}
		
		public StoragePosition(StorageElementToClient stoElement, int id){
			
			this(id, stoElement.type, stoElement.quality+"", stoElement.quantity+"", stoElement.costs+"");
			
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

}
