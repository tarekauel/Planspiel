package Client.UI;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient.SupplierOfferToClient;

public class ClientGameUIModel {
	
	private int round;
	private int maxRounds = 20;
	
	private ArrayList<RequestFromClient> requests = new ArrayList<RequestFromClient>();
	
	public int getRound() {
		return round;
	}
	public int getMaxRounds() {
		return maxRounds;
	}
	public void setRound(int round) {
		this.round = round;
	}

	public static class Request {		
		
		private final SimpleStringProperty name;
		private final SimpleStringProperty quality;
		private final SimpleStringProperty status;
		private final SimpleStringProperty id;
		private final ArrayList<SupplierOffer> supplierOfferList;
		private static int lastId = 0;
			
		Request( RequestToClient req, int id) {
			this(req.name, req.quality+"", "undefined", id, req.supplierOffers);
		}
		
		Request( String name, String quality) {
				this(name, quality, "Neu", lastId+1, null);
		}
		
		private Request( String name, String quality, String status, int id, ArrayList<SupplierOfferToClient> offers) {
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

	public void addRequest( RequestFromClient r) {
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
			this( offer.name, offer.quality+"", offer.orderedQuantity+"", offer.price+"", id, offer.round);
		}
		
		private SupplierOffer( String name, String quality, String quantity,  String price, int id, int round) {
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

}
