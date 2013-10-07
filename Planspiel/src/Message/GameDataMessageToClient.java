package Message;

import java.util.ArrayList;

import Message.GameDataMessageFromClient.Purchase.AcceptedSupplierOffer;

public class GameDataMessageToClient extends GameDataMessage {

	public GameDataMessageToClient(String playerName, Purchase purchase,
			Production production, Distribution distribution,
			boolean increaseMachineLevel, HumanResources humanResources,
			int wage, boolean buyMarketResearch, long cash, long maxCredit) {
		super(playerName);
		this.purchase = purchase;
		this.production = production;
		this.distribution = distribution;
		this.increaseMachineLevel = increaseMachineLevel;
		this.humanResources = humanResources;
		this.wage = wage;
		this.buyMarketResearch = buyMarketResearch;
		this.cash = cash;
		this.maxCredit = maxCredit;
	}

	public final Purchase purchase;
	public final Production production;
	public final Distribution distribution;
	public final boolean increaseMachineLevel;
	public final HumanResources humanResources;
	public final int wage;
	public final boolean buyMarketResearch;
	public final long cash;
	public final long maxCredit;

	public class Purchase {
		public Purchase(ArrayList<Request> requests,
				ArrayList<AcceptedSupplierOffer> allSupplierOffers) {
			this.requests = requests;
		}

		public final ArrayList<Request> requests; // ewige Liste

		public class Request {
			public Request(String name, int quality,
					ArrayList<SupplierOffer> supplierOffers) {
				this.name = name;
				this.quality = quality;
				this.supplierOffers = supplierOffers;

			}

			public final ArrayList<SupplierOffer> supplierOffers;
			public final String name;
			public final int quality;

			public class SupplierOffer {
				public SupplierOffer(String name, int quality, int quantity) {
					this.name = name;
					this.quality = quality;
					this.quantity = quantity;
				}

				public final String name;
				public final int quality;
				public final int quantity;
			}
		}

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

		public final ArrayList<ProductionOrder> orders; // Ewige Liste

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

		public final ArrayList<BenefitBooking> benefits;

		public HumanResources(ArrayList<BenefitBooking> benefits,
				int averageWage, int myWage, int countEmployees, int wageCosts) {

			this.benefits = benefits;
			this.averageWage = averageWage;
			this.myWage = myWage;
			this.countEmployees = countEmployees;
			this.wageCosts = wageCosts;
		}

		public final int averageWage;
		public final int myWage;
		public final int countEmployees;
		public final int wageCosts;

		public class BenefitBooking {

			public BenefitBooking(String name, int duration) {
				this.name = name;
				this.duration = duration;
			}

			public final String name;
			public final int duration;
		}

	}

	public class Marketing {

		public Marketing(int peakAMarket, int peakCMarket,
				ArrayList<MarketShare> marketShares,
				ArrayList<RessourcePrice> waferPrice,
				ArrayList<RessourcePrice> casePrice,
				ArrayList<MotivationRound> motivationRounds) {

			this.peakAMarket = peakAMarket;
			this.peakCMarket = peakCMarket;
			this.marketShares = marketShares;
			this.waferPrice = waferPrice;
			this.casePrice = casePrice;
			this.motivationRounds = motivationRounds;
		}

		public final int peakAMarket;
		public final int peakCMarket;
		public final ArrayList<MarketShare> marketShares;
		public final ArrayList<RessourcePrice> waferPrice;
		public final ArrayList<RessourcePrice> casePrice;
		// For HR
		public final ArrayList<MotivationRound> motivationRounds;

		public class MotivationRound {
			int round;
			int motivation;
		}

		public class RessourcePrice {

			public RessourcePrice(int quality, int price) {

				this.quality = quality;
				this.price = price;
			}

			public final int quality;
			public final int price;

		}

		public class MarketShare {

			public MarketShare(int share, String name) {
				super();
				this.share = share;
				this.name = name;
			}

			public final int share;
			public final String name;

		}

	}

	public class Reporting {

		public Reporting(ArrayList<FixCost> fixCosts, Machinery machinery,
				ArrayList<Sells> sellsOfRounds,
				ArrayList<CashValueOfRound> cashValues) {

			this.fixCosts = fixCosts;
			this.machinery = machinery;
			this.sellsOfRounds = sellsOfRounds;
			this.cashValues = cashValues;
		}

		public final ArrayList<FixCost> fixCosts;
		public final Machinery machinery;
		public final ArrayList<Sells> sellsOfRounds;
		public final ArrayList<CashValueOfRound> cashValues;

		public class Sells {

			public Sells(int round, ArrayList<Integer> qualities) {

				this.round = round;
				this.qualities = qualities;
			}

			public final int round;
			public final ArrayList<Integer> qualities;

		}

		public class FixCost {

			public FixCost(String nameOfDepartment, int costs) {

				this.nameOfDepartment = nameOfDepartment;
				this.costs = costs;
			}

			public final String nameOfDepartment;
			public final int costs;
		}

		public class Machinery {

			public Machinery(int level, int maxCapacity, int averageUsage,
					int usageLastRound) {

				this.level = level;
				this.maxCapacity = maxCapacity;
				this.averageUsage = averageUsage;
				this.usageLastRound = usageLastRound;
			}

			public final int level;
			public final int maxCapacity;
			public final int averageUsage;
			public final int usageLastRound;
		}

		public class CashValueOfRound {

			public CashValueOfRound(int round, int costs) {

				this.round = round;
				this.costs = costs;
			}

			public final int round;
			public final int costs;
		}

	}
}
