package Server;
import java.util.ArrayList;

import Logger.Log;


public class Storage extends Department {

	private ArrayList<StorageElement> listOfStorageElements = new ArrayList<StorageElement>();
	
	public Storage(Company c, int fix) throws Exception {
        super(c,"Lager",fix);
        
    }
	
	
	
	public void store(Product product, int quantity)throws Exception{
		Log.method(new Object[]{product,quantity});
		int size = listOfStorageElements.size();
		StorageElement storageElement = null;
		boolean found = false; 
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			if(storageElement.getProduct() == product){
				storageElement.increaseQuantity(quantity); //falls Element gefunden wird Anzahl erhï¿½ht
				// TODO Kosten müssen neu berechnet werden
				found = true;
				break;
			}
		}
		
		if(!found){
			//Exceptions lassen Programm stoppen
			storageElement = new StorageElement(quantity,product);
			
			listOfStorageElements.add(storageElement);
			
		}
		Log.methodExit();
	}//store
	
	public void updateStorageElements(){
		
	}// TODO updateStorageElements was macht diese Funktion?! die kosten updaten?!?!?!?!
	
	public int getStorageCostsSum(){
		
		StorageElement storageElement = null;
		Product product = null;
		int sum = 0;
		int size = listOfStorageElements.size();
		for(int i=0; i<size; i++){
			storageElement = listOfStorageElements.get(i);
			product = storageElement.getProduct();
			sum = sum + product.getStorageCostsPerRound();
		}
		Log.get(sum);
		return sum;
	}//getStorageCostsSum
	
	public boolean unstore(Product product, int quantity){
		Log.method(new Object[]{product,quantity});
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
				//falls das storageelement jetzt leer ist, lösche dir Referenz
				if (storageElement.getQuantity()==0){
					listOfStorageElements.remove(storageElement);
					
				}
				//beenden der schleife
				break;
			}
		}//for sucht passendes StrEl anhand von Prod aendert dann die Anzahl
		Log.get(success);
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
				Log.get(storageElement);
				return storageElement;
			}//if
		}//for
		Log.get(null);
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
		Log.get(finishedGoods);
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
		Log.get(resources);
		return resources;
		
	}//getAllResources
	
	/**
	 * Liefert eine Liste aller Storage-Elemente zurück
	 * @return Liste aller Storage Elemente
	 */
	public ArrayList<StorageElement> getAllStorageElements() {
		Log.get(listOfStorageElements);
		return listOfStorageElements;
	}
}
