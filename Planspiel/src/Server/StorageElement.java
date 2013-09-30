package Server;

import Logger.Log;

public class StorageElement {
	
	private int quantity;
	private Product product;
	/**
	 * Erzeugt ein neues StorageElement
	 * @param quantity Anzahl der produkte die das Element enthält
	 * @param product Zu verstauendes
	 * @throws IllegalArgumentException
	 */
	public StorageElement(int quantity, Product product) throws Exception{
		Log.newObj(new Object[]{quantity,product});
		if(product == null || !(checkQuantityHigherZero(quantity))){
			throw new IllegalArgumentException("product is null or quantity is lower zero. Class StorageElement Method Constructor");
		}
		this.product = product;
		this.quantity = quantity;
		Log.methodExit();
	}
	
	
	private boolean checkQuantityHigherZero(int quantity){
		boolean result = (quantity>0)? true:false;
		return result;
	} 
	
	private boolean checkEnoughInStorage(int quantity){
		if(this.quantity >= quantity) return true;
		else return false;
		
	}
	
	public int getQuantity(){
		Log.get(quantity);
		return quantity;
	}
	
	public boolean reduceQuantity(int quantity){
		Log.method(quantity);
		boolean enoughInStore = checkEnoughInStorage(quantity);
		if(enoughInStore&&quantity>0){
			this.quantity = this.quantity - quantity;
			Log.get(true);
			return true;
		}
		Log.get(false);
		return false;
	}
	
	public boolean increaseQuantity(int quantity){
		Log.method(quantity);
		if(quantity>0){
			this.quantity = this.quantity + quantity;
			Log.get(true);
			return true;
		}
		Log.get(false);
		return false;
	}
	
	public Product getProduct(){
		Log.get(product);
		return product;
	}

	
}
