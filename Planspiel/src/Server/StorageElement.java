package Server;

import java.io.IOException;

public class StorageElement {
	
	private int quantity;
	private Product product;
	
	public StorageElement(int quantity, Product product) throws Exception{
		if(product == null || checkQuantityHigherZero(quantity)){
			throw new IOException("product is null or quantity is lower zero. Class StorageElement Method Constructor");
		}
		this.product = product;
		this.quantity = quantity;
	}
	
	
	private boolean checkQuantityHigherZero(int quantity){
		boolean result = (quantity>=0)? true:false;
		return result;
	} 
	
	private boolean checkEnoughInStorage(int quantity){
		if(this.quantity >= quantity) return true;
		else return false;
		
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public boolean reduceQuantity(int quantity){
		boolean enoughInStore = checkEnoughInStorage(quantity);
		if(enoughInStore){
			this.quantity = this.quantity - quantity;
			return true;
		}
		else return false;
	}
	
	public boolean increaseQuantity(int quantity){
		if(quantity>0){
			this.quantity = this.quantity + quantity;
			return true;
		}
		return false;
	}
	
	public Product getProduct(){
		return product;
	}

	
}
