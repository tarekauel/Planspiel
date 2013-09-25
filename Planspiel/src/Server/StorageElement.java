
public class StorageElement {
	
	private int quantity;
	private Product product;
	
	private StorageElement(int quantity, Product product){
		//prüfungen??
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
		//pruefe ob quantity positiv?! wann wird false geliefert?
		this.quantity = this.quantity + quantity;
		return true;
	}
	
	public Product getProduct(){
		return product;
	}

	
}
