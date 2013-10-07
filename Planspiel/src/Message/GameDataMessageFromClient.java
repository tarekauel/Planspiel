package Message;

import java.util.ArrayList;

public class GameDataMessageFromClient extends GameDataMessage {

	public GameDataMessageFromClient(String playerName, Purchase purchase,
			Production production, Distribution distribution,
			boolean increaseMachineLevel, HumanResources humanResources,
			int wage, boolean buyMarketResearch) {
		super(playerName);
		this.purchase = purchase;
		this.production = production;
		this.distribution = distribution;
		this.increaseMachineLevel = increaseMachineLevel;
		this.humanResources = humanResources;
		this.wage = wage;
		this.buyMarketResearch = buyMarketResearch;
	}

	public final Purchase purchase;
	public final Production production;
	public final Distribution distribution;
	public final boolean increaseMachineLevel;
	public final HumanResources humanResources;
	public final int wage;
	public final boolean buyMarketResearch;

	public class Purchase {
		public Purchase(ArrayList<Request> requests,
				ArrayList<AcceptedSupplierOffer> acceptedSupplierOffers) {
			this.requests = requests;
			this.acceptedSupplierOffers = acceptedSupplierOffers;
		}

		public final ArrayList<Request> requests; // Artikel

		// und
		public class Request {
			public Request(String name, int quality) {
				this.name = name;
				this.quality = quality;

			}

			public final String name;
			public final int quality;
		}

		// Qualitaet
		public class AcceptedSupplierOffer {
			public AcceptedSupplierOffer(String name, int quality, int quantity) {
				this.name = name;
				this.quality = quality;
				this.quantity = quantity;
			}

			public final String name;
			public final int quality;
			public final int quantity;
		}

		public final ArrayList<AcceptedSupplierOffer> acceptedSupplierOffers;

	}

	public class Production {

		public Production(ArrayList<ProductionOrder> orders) {
			this.orders = orders;
		}

		public class ProductionOrder {
			public ProductionOrder(int qualityWafer, int qualityCase,
					int quantity) {

				this.qualityWafer = qualityWafer;
				this.qualityCase = qualityCase;
				this.quantity = quantity;
			}

			public final int qualityWafer;
			public final int qualityCase;
			public final int quantity;
		}

		public final ArrayList<ProductionOrder> orders;

	}

	public class Distribution {

		public Distribution(ArrayList<Offer> offers) {
			this.offers = offers;
		}

		public class Offer {

			public Offer(int quality, int quantityToSell, int price) {
				this.quality = quality;
				this.quantityToSell = quantityToSell;
				this.price = price;
			}

			public final int quality;
			public final int quantityToSell;
			public final int price;
		}

		public final ArrayList<Offer> offers;
	}

	public class HumanResources {

		public HumanResources(ArrayList<BenefitBooking> benefits) {

			this.benefits = benefits;
		}

		public class BenefitBooking {

			public BenefitBooking(String name, int duration) {
				this.name = name;
				this.duration = duration;
			}

			public final String name;
			public final int duration;
		}

		public final ArrayList<BenefitBooking> benefits;
	}

}
