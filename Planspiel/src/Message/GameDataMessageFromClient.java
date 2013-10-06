package Message;


import java.util.ArrayList;


public class GameDataMessageFromClient extends GameDataMessage {

	public Purchase purchase;
	public Production production;
	public Distribution distribution;
	public boolean increaseMachineLevel;
	public HumanResources humanResources;
	public int wage;
	public boolean buyMarketResearch;

	public GameDataMessageFromClient(String playerName) {
		super(playerName);
		// TODO Auto-generated constructor stub
	}

	public class Purchase {
		
		public class Request {
			public String name;
			public int quality;
		}

		public ArrayList<Request> requests = new ArrayList<Request>(); // Artikel und
																// Qualitaet
		public class AcceptedSupplierOffer {
			public String name;
			public int quality;
			public int quantity;
		}

		public ArrayList<AcceptedSupplierOffer> acceptedSupplierOffers = new ArrayList<AcceptedSupplierOffer>(); 	
		
	}
	
	public class Production{
		public class ProductionOrder{
			public int qualityWafer;
			public int qualityCase;
			public int quantity;
		}
		
		public ArrayList<ProductionOrder> orders = new ArrayList<ProductionOrder>(); 
		
	}
	
	public class Distribution{
		public class Offer{
			public int quality;
			public int quantityToSell;
			public int price;
		}
		public ArrayList<Offer> offers = new ArrayList<Offer>(); 
	}
	
	public class HumanResources{
		public class BenefitBooking{
			public String name;
			public int duration;
		}
		public ArrayList<BenefitBooking> benefits = new ArrayList<BenefitBooking>();
	}


}
