package Message;

import java.util.ArrayList;

public class GameDataMessageFromClient extends GameDataMessage {

	public GameDataMessageFromClient(String playerName, PurchaseFromClient purchase,
			ProductionFromClient production, DistributionFromClient distribution,
			boolean increaseMachineLevel, HumanResourcesFromClient humanResources,
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

	public final PurchaseFromClient purchase;
	public final ProductionFromClient production;
	public final DistributionFromClient distribution;
	public final boolean increaseMachineLevel;
	public final HumanResourcesFromClient humanResources;
	public final int wage;
	public final boolean buyMarketResearch;

	public class PurchaseFromClient {
		public PurchaseFromClient(ArrayList<RequestFromClient> requests,
				ArrayList<AcceptedSupplierOfferFromClient> acceptedSupplierOffers) {
			this.requests = requests;
			this.acceptedSupplierOffers = acceptedSupplierOffers;
		}

		public final ArrayList<RequestFromClient> requests; // Artikel

		// und
		public class RequestFromClient {
			public RequestFromClient(String name, int quality) {
				this.name = name;
				this.quality = quality;

			}

			public final String name;
			public final int quality;
		}

		// Qualitaet
		public class AcceptedSupplierOfferFromClient {
			public AcceptedSupplierOfferFromClient(String name, int quality, int quantity) {
				this.name = name;
				this.quality = quality;
				this.quantity = quantity;
			}

			public final String name;
			public final int quality;
			public final int quantity;
		}

		public final ArrayList<AcceptedSupplierOfferFromClient> acceptedSupplierOffers;

	}

	public class ProductionFromClient {

		public ProductionFromClient(ArrayList<ProductionOrderFromClient> orders) {
			this.orders = orders;
		}

		public class ProductionOrderFromClient {
			public ProductionOrderFromClient(int qualityWafer, int qualityCase,
					int quantity) {

				this.qualityWafer = qualityWafer;
				this.qualityCase = qualityCase;
				this.quantity = quantity;
			}

			public final int qualityWafer;
			public final int qualityCase;
			public final int quantity;
		}

		public final ArrayList<ProductionOrderFromClient> orders;

	}

	public class DistributionFromClient {

		public DistributionFromClient(ArrayList<OfferFromClient> offers) {
			this.offers = offers;
		}

		public class OfferFromClient {

			public OfferFromClient(int quality, int quantityToSell, int price) {
				this.quality = quality;
				this.quantityToSell = quantityToSell;
				this.price = price;
			}

			public final int quality;
			public final int quantityToSell;
			public final int price;
		}

		public final ArrayList<OfferFromClient> offers;
	}

	public class HumanResourcesFromClient {

		public HumanResourcesFromClient(ArrayList<BenefitBookingFromClient> benefits) {

			this.benefits = benefits;
		}

		public class BenefitBookingFromClient {

			public BenefitBookingFromClient(String name, int duration) {
				this.name = name;
				this.duration = duration;
			}

			public final String name;
			public final int duration;
		}

		public final ArrayList<BenefitBookingFromClient> benefits;
	}

}
