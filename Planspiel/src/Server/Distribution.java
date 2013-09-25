package Server;
import java.io.IOException;
import java.util.ArrayList;


public class Distribution extends Department{
	
	private ArrayList<Offer> listOfOffers = new ArrayList<Offer>();
	private ArrayList<Offer> sortedListOfLatestOffers = new ArrayList<Offer>();
	
	public void createOffer(int quality, int quantityToSell, int price ){
		
		Storage storage = company.getStorage();
		StorageElement storageElement = storage.getFinishedGoodByQuality(quality); 
		
		if(storageElement==null){
			throw new NullPointerException("StorageElement could not found! Class Distribution Method createOffer");
		}
		
		int round = gameEngine.getRound();		 //Rundenzahl muss von GameEngine gegeben werden.
		int sold = 0; 							 // die verkaufte Menge ist beim erstellen eines Offers immer 0.
		try{
			Offer offer = new Offer(quantityToSell,price,round,sold,storageElement);
			listOfOffers.add(offer);
		}catch(IOException e){
			e.getMessage();					     //korrekt??			
		}
		
		
		
		
	}//createOffer
	
	public ArrayList<Offer> getListOfOffers(){
		return listOfOffers;
	}//getListOfOffers
	
	public ArrayList<Offer> getSortedListOfLatestOffers(){
		return sortedListOfLatestOffers;
	}
}
