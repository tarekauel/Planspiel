package Server;
import java.io.IOException;


public class Offer implements Comparable<Offer>{
	
	private int quantityToSell;
	private int priceToSell;
	private int quantitySold;
	private int round;
	private StorageElement storageElement;
	private Distribution distribution;
	
	public Offer(int quantityToSell, int priceToSell,int round, int quantitySold, StorageElement storageElement) throws IOException{
		if(quantityToSell<=0 || priceToSell<0){
			throw new IOException("quantityToSell oder priceToSell ist negativ. Class Offer Method Constructor");
		}
		this.quantityToSell = quantityToSell; //muss vorher geprüft werden ob sie negativ ist?!
		this.priceToSell = priceToSell; // ebenso
		this.round = round;
		this.quantitySold = quantitySold;
		
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
		int qualityThis = product.getQuality();
		int priceToSellThis = this.getPrice();
		
		StorageElement storageElement = offer.getStorageElement();
		Product product = storageElement.getProduct();
		int quality = product.getQuality();
		int priceToSell = offer.getPrice();
		
		int costPerformanceRatioThis = qualityThis / priceToSellThis;
		int costPerformanceRatio = quality / priceToSell;
		
		if(costPerformanceRatioThis > costPerformanceRatio){
			return 1;
		}
		if(costPerformanceRatioThis == costPerformanceRatio){
			return 0;
		}
		if(costPerformanceRatioThis < costPerformanceRatio){
			return -1;
		}
		
	}//compareTo
	

}
