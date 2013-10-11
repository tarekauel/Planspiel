package Message;

import java.io.Serializable;
import java.util.ArrayList;

import Server.GameEngine;


public class GameDataMessageToClient extends GameDataMessage implements Serializable
	{

	public GameDataMessageToClient(String playerName, PurchaseToClient purchase,
			ProductionToClient production,StorageToClient storage, DistributionToClient distribution,
			HumanResourcesToClient humanResources,
			MarketingToClient marketing, ReportingToClient reporting,
			 long cash, long maxCredit) {
		super(playerName);
		this.purchase = purchase;
		this.production = production;
		this.distribution = distribution;
		this.marketing=marketing;
		this.humanResources = humanResources;
		this.reporting = reporting;
		this.cash = cash;
		this.maxCredit = maxCredit;
		this.storage= storage;
		this.round = GameEngine.getGameEngine().getRound();
	}

	public final PurchaseToClient purchase;
	public final ProductionToClient production;
	public final StorageToClient storage;
	public final DistributionToClient distribution;
	public final MarketingToClient marketing;
	public final ReportingToClient reporting;	
	public final HumanResourcesToClient humanResources;	
	public final long cash;
	public final long maxCredit;
	public final int round;

	public static class PurchaseToClient implements Serializable {
		public PurchaseToClient(ArrayList<RequestToClient> requests) {
			this.requests = requests;
		}

		public final ArrayList<RequestToClient> requests; // ewige Liste

		public static class RequestToClient implements Serializable {
			public RequestToClient(String name, int quality,
					ArrayList<SupplierOfferToClient> supplierOffers) {
				this.name = name;
				this.quality = quality;
				this.supplierOffers = supplierOffers;

			}

			public final ArrayList<SupplierOfferToClient> supplierOffers;
			public final String name;
			public final int quality;
			

			public static class SupplierOfferToClient implements Serializable {
				public SupplierOfferToClient(String name, int quality, int orderedQuantity,int price, int round) {
					this.name = name;
					this.quality = quality;
					this.price = price;
					this.orderedQuantity=orderedQuantity;
					this.round = round;
				}

				public final String name;
				public final int quality;
				public final int orderedQuantity;
				public final int price;
				public final int round;
			}
		}

	}

	public static class ProductionToClient implements Serializable {

		public ProductionToClient(ArrayList<ProductionOrderToClient> orders) {
			this.orders = orders;
		}

		public static class ProductionOrderToClient implements Serializable {
			public ProductionOrderToClient(int qualityWafer, int qualityCase,
					int quantity) {

				this.qualityWafer = qualityWafer;
				this.qualityCase = qualityCase;
				this.quantity = quantity;
			
			}

			public final int qualityWafer;
			public final int qualityCase;
			public final int quantity;
			
		}

		public final ArrayList<ProductionOrderToClient> orders; // Ewige Liste

	}
	
	public static class StorageToClient implements Serializable {
		

		
		public StorageToClient(int storageCostsWafer, int storageCostsCase,
				int storageCostsPanel,
				ArrayList<StorageElenmentToClient> storageElements) {
			
			this.storageCostsWafer = storageCostsWafer;
			this.storageCostsCase = storageCostsCase;
			this.storageCostsPanel = storageCostsPanel;
			this.storageElements = storageElements;
		}

		public static class StorageElenmentToClient implements Serializable {
			
			public StorageElenmentToClient(String type, int quality,
					int quantity) {
				this.type = type;
				this.quality = quality;
				this.quantity = quantity;
			}
			public final String type;
			public final int quality;
			public final int quantity;
			
		}
		public final int storageCostsWafer;
		public final int storageCostsCase;
		public final int storageCostsPanel;

		public final ArrayList<StorageElenmentToClient> storageElements; // Liste StoageElements

	}

	public static  class DistributionToClient implements Serializable {

		public DistributionToClient(ArrayList<OfferToClient> offers) {
			this.offers = offers;
		}

		public static class OfferToClient {

			public OfferToClient(int quality, int quantityToSell, int quantitySold, int price) {
				this.quality = quality;
				this.quantityToSell = quantityToSell;
				this.quantitySold = quantitySold;
				this.price = price;
			}

			public final int quality;
			public final int quantityToSell;
			public final int quantitySold;
			public final int price;
		}

		public final ArrayList<OfferToClient> offers;
	}

	public static class HumanResourcesToClient implements Serializable {

		public final ArrayList<BenefitBookingToClient> benefits;

		public HumanResourcesToClient(ArrayList<BenefitBookingToClient> benefits,
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

		public static class BenefitBookingToClient implements Serializable {

			public BenefitBookingToClient(String name, int remainingRounds) {
				this.name = name;
				this.remainingRounds = remainingRounds;
			}

			public final String name;
			public final int remainingRounds;
		}

	}

	public static class MarketingToClient implements Serializable {

		public MarketingToClient(int peakAMarket, int peakCMarket,
				ArrayList<MarketShareToClient> marketShares,
				ArrayList<RessourcePriceToClient> waferPrice,
				ArrayList<RessourcePriceToClient> casePrice,
				ArrayList<MotivationRoundToClient> motivationRounds) {

			this.peakAMarket = peakAMarket;
			this.peakCMarket = peakCMarket;
			this.marketShares = marketShares;
			this.waferPrice = waferPrice;
			this.casePrice = casePrice;
			this.motivationRounds = motivationRounds;
		}

		public final int peakAMarket;
		public final int peakCMarket;
		public final ArrayList<MarketShareToClient> marketShares;
		public final ArrayList<RessourcePriceToClient> waferPrice;
		public final ArrayList<RessourcePriceToClient> casePrice;
		// For HR
		public final ArrayList<MotivationRoundToClient> motivationRounds;

		public static class MotivationRoundToClient implements Serializable {
			int round;
			int motivation;
		}

		public static class RessourcePriceToClient implements Serializable{

			public RessourcePriceToClient(int quality, int price) {

				this.quality = quality;
				this.price = price;
			}

			public final int quality;
			public final int price;

		}

		public static class MarketShareToClient implements Serializable {

			public MarketShareToClient(int share, String name) {
				super();
				this.share = share;
				this.name = name;
			}

			public final int share;
			public final String name;

		}

	}

	public static class ReportingToClient implements Serializable {

		public ReportingToClient(ArrayList<FixCostToClient> fixCosts, MachineryToClient machinery,
				ArrayList<SellsToClient> sellsOfRounds,
				ArrayList<CashValueOfRoundToClient> cashValues) {

			this.fixCosts = fixCosts;
			this.machinery = machinery;
			this.sellsOfRounds = sellsOfRounds;
			this.cashValues = cashValues;
		}

		public final ArrayList<FixCostToClient> fixCosts;
		public final MachineryToClient machinery;
		public final ArrayList<SellsToClient> sellsOfRounds;
		public final ArrayList<CashValueOfRoundToClient> cashValues;

		public static class SellsToClient  implements Serializable{

			public SellsToClient(int round, ArrayList<Integer> qualities) {

				this.round = round;
				this.qualities = qualities;
			}

			public final int round;
			public final ArrayList<Integer> qualities;

		}

		public static class FixCostToClient implements Serializable {

			public FixCostToClient(String nameOfDepartment, int costs) {

				this.nameOfDepartment = nameOfDepartment;
				this.costs = costs;
			}

			public final String nameOfDepartment;
			public final int costs;
		}

		public static class MachineryToClient implements Serializable{

			public MachineryToClient(int level, int maxCapacity, int averageUsage,
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

		public static class CashValueOfRoundToClient implements Serializable {

			public CashValueOfRoundToClient(int round, int costs) {

				this.round = round;
				this.costs = costs;
			}

			public final int round;
			public final int costs;
		}

	}
}
