package Server;
import java.io.IOException;

/**
 * Ein Offer Objekt wird erstellt, wenn der Spieler Fertigprodukte verkaufen moechte.
 * Dabei muss er die Menge, den Preis und das zu verkaufende Produkt angeben.
 * Es wird dabei auch überprüft, ob das Produkt existiert bzw ob es in der gewuenschten
 * Menge verkauft werden kann.
 * @author Felix
 *
 */

public class Offer implements Comparable<Offer>{
	
	private int quantityToSell;
	private int priceToSell;
	private int quantitySold;
	private int round;
	private StorageElement storageElement;
	private Distribution distribution;
	
	/**
	 * Konstruktor fuer ein Offer Objekt
	 * @param quantityToSell
	 * @param priceToSell
	 * @param round
	 * @param quantitySold
	 * @param storageElement
	 * @throws IOException
	 */
	
	public Offer(int quantityToSell, int priceToSell,int round, int quantitySold, StorageElement storageElement, Distribution d) throws IOException{
		if(quantityToSell<=0 || priceToSell<0){
			throw new IOException("quantityToSell oder priceToSell ist negativ. Class Offer Method Constructor");
		}
		this.quantityToSell = quantityToSell; //muss vorher geprüft werden ob sie negativ ist?!
		this.priceToSell = priceToSell; // ebenso
		this.round = round;
		this.quantitySold = quantitySold;
		this.distribution = d;
		
		if(storageElement == null){
			throw new IOException("StorageElement is null (Class Offer, Method: Constructor)");
		}
		this.storageElement = storageElement; 
		
	}//constructor
	
	public int getPrice(){
		return priceToSell;
	}//getPrice
	
	public int getQuantityToSell(){
		return quantityToSell;
	}//getQuantityToSell
	
	public int getQuantitySold(){
		return quantitySold;
	}//getQuantitySold
	
	public int getRound(){
		return round;
	}
	
	public StorageElement getStorageElement(){
		return storageElement;
	}//getStorageElement
	
	public Distribution getDistribution(){
		return distribution;
	}//getDistribution
	
	public void setQuantitySold(int quantitySold){
		if(quantitySold<0||quantitySold>quantityToSell){
			throw new RuntimeException("The quantitySold which should be set is lower 0 or greater than quantityToSell(Class Offer Method setQuantityToSell)");
		}
		this.quantitySold = quantitySold;
	}//setQuantitySold
	
	public boolean increaseQuantitySold(int quantity) throws Exception{
		if(quantity<0){
			return false;
		}
		else{
			quantitySold = quantitySold + quantity;
			return true;
		}
	}//increaseQuantitySold
	
	@Override
	public int compareTo(Offer offer) {
		StorageElement storageElementThis = this.getStorageElement();
		Product productThis = storageElementThis.getProduct();
		int qualityThis = productThis.getQuality();
		int priceToSellThis = this.getPrice();
		
		StorageElement storageElement = offer.getStorageElement();
		Product product = storageElement.getProduct();
		int quality = product.getQuality();
		int priceToSell = offer.getPrice();
		
		int costPerformanceRatioThis = qualityThis / priceToSellThis;
		int costPerformanceRatio = quality / priceToSell;
		
		if(costPerformanceRatioThis > costPerformanceRatio){
			return 1;
		}else if(costPerformanceRatioThis == costPerformanceRatio){
			return 0;
		}else{
		//(costPerformanceRatioThis < costPerformanceRatio)
			return -1;
		}
		
	}//compareTo
	

}
