package Server;
import java.io.IOException;
import java.util.ArrayList;


public class Purchase {

	private ArrayList<Request> listOfRequests = new ArrayList<Request>();
	
	public void createRequest(Resource resource) throws Exception{
		
		if(resource==null){
			throw new NullPointerException("Resource is null! Class Purchase Method createRequest");
		}
		
		Request request = new Request(resource);
		//Request request = Request.create(resource);
		listOfRequests.add(request);
		
	}
	
	public void acceptSupplierOffer(SupplierOffer supplierOffer, int quantity) throws Exception{
		if(supplierOffer == null){
			throw new NullPointerException("supplierOffer is null! Class Purchase Method acceptSupplierOffer");
		}
		
		if(quantity<=0){
			throw new IOException("Quantity is 0 or lower! Class Purchase Method acceptSupplierOffer");
		}
		
		Resource resource = supplierOffer.getResource();
		if(resource == null){
			throw new NullPointerException("resource of supplierOffer is null! Class Purchase Method acceptSupplierOffer");
		}
		
		Storage storage = company.getStorage();
		storage.store(resource, quantity);
	}
	
	public ArrayList<Request> getListOfRequest(){
		return listOfRequests;
	}//getListOfRequest
	
}
