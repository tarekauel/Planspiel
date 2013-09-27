package Server;
import java.util.ArrayList;


public class Storage {
	
	private ArrayList<StorageElement> listOfStorageElements = new ArrayList<StorageElement>();
	
	public void store(Product product, int quantity){
		int size = listOfStorageElements.size();
		StorageElement storageElement = null;
		boolean found = false; 
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			if(storageElement.getProduct() == product){
				storageElement.increaseQuantity(quantity); //falls Element gefunden wird Anzahl erhöht
				found = true;
			}
		}//for
		if(found==false){
			try{
			storageElement = new StorageElement(quantity,product);
			
			listOfStorageElements.add(storageElement);
			}catch(Exception e){
				//Problem beim erstellen, keine Behandlung
				//TODO: Was passiert hier?!
			}
		}
	}//store
	
	public void updateStorageElements(){
		
	}//updateStorageElements was macht diese Funktion?!
	
	public int getStorageCostsSum(){
		StorageElement storageElement = null;
		Product product = null;
		int sum = 0;
		int size = listOfStorageElements.size();
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			product = storageElement.getProduct();
			sum = sum + product.getStorageCostsPerRond();
		}
		return sum;
	}//getStorageCostsSum
	
	public boolean unstore(Product product, int quantity){
		//muss hier die angegebene Quantity//Product wieder geprueft werden??
		StorageElement storageElement = null;
		Product productTmp = null;
		int size = listOfStorageElements.size();
		boolean success = false;
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			productTmp = storageElement.getProduct();
			if(productTmp == product){
				success = storageElement.reduceQuantity(quantity); 
			}
		}//for sucht passendes StrEl anhand von Prod aendert dann die Anzahl
		return success; //success macht keine angabe ob reduceQuantity()fehlschlug oder
						//kein StorageElement/product in der ArrayList gefunden wurde
	}//unstore
	
	public StorageElement getFinishedGoodByQuality(int quality){
		StorageElement storageElement = null;
		Product product = null;
		int size = listOfStorageElements.size();
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			product = storageElement.getProduct();
			if(product.getQuality() == quality){
				return storageElement;
			}//if
		}//for
		return null;
	}//getFinishedGoodByQuality
	
	public ArrayList<FinishedGood> getAllFinishedGoods(){
		ArrayList<FinishedGood> finishedGoods = new ArrayList<FinishedGood>(); 
		StorageElement storageElement = null;
		Product product = null;
		FinishedGood finishedGood = null;
		int size = listOfStorageElements.size();
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			product = storageElement.getProduct();
			if(product instanceof FinishedGood){
				finishedGood = (FinishedGood) product;
				finishedGoods.add(finishedGood);
			}
		}//for
		return finishedGoods;
	}//getAllFinishedGoods
	
	public ArrayList<Resource> getAllResources(){
		ArrayList<Resource> resources = new ArrayList<Resource>(); 
		StorageElement storageElement = null;
		Product product = null;
		Resource resource = null;
		int size = listOfStorageElements.size();
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			product = storageElement.getProduct();
			if(product instanceof Resource){
				resource = (Resource) product;
				resources.add(resource);
			}
		}//for
		return resources;
		
	}//getAllResources
}
