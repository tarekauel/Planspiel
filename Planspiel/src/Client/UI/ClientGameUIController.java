package Client.UI;

/**
 * Created by:
 * User: Lars Trey
 * Date: 08.10.13
 * Time: 16:35
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import Client.UI.ClientGameUIModel.Request;
import Client.UI.ClientGameUIModel.SupplierOffer;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Message.GameDataMessageToClient;
import Message.GameDataMessageToClient.PurchaseToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient;
import aaaaa.GameTestConsole;


/**
 * Dies ist die Controller-Klasse der Game-Stage. Hier wird alles implementiert, was das Game-UI manipulieren soll.
 * @author Lars Trey
 */
public class ClientGameUIController implements Initializable{
	
	private ClientGameUIModel model;

	/**
	 * Hier werden alle Felder des UIs mit Variablen verknüpft.
	 */
	@FXML private ListView<String> eventListView;
	@FXML private Button endRoundButton;
	@FXML private Label roundLabel;
	@FXML private ProgressBar roundProgressBar;
    //Purchase
	@FXML private Button newPurchaseRequestButton;
	@FXML private Button newPurchaseRequestSaveButton;
	@FXML private ChoiceBox newPurchaseRequestArticleNameChoiceBox;
	@FXML private Slider newPurchaseRequestArticleQualitySlider;
	@FXML private TextField newPurchaseRequestArticleQualityTextField;
	@FXML private TableView<Request> purchaseRequestsTableView;
	@FXML private TableColumn<Request,String> purchaseRequestArtikelTableColumn;
	@FXML private TableColumn<Request,String> purchaseRequestIdTableColumn;
	@FXML private TableColumn<Request,String> purchaseRequestQualityTableColumn;
	@FXML private TableColumn<Request,String> purchaseRequestStatusTableColumn;
	private final ObservableList<Request> purchaseRequestTableData = FXCollections.observableArrayList();
	@FXML private TableView<SupplierOffer> purchaseOffersTableView;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersIdTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersArtikelTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersQualityTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersQuantityTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersPriceTableColumn;	
	private final ObservableList<SupplierOffer> purchaseOffersTableData = FXCollections.observableArrayList();
    //Production
	@FXML private Button newProductionOrderButton;
	@FXML private Button newProductionOrderSaveButton;
	@FXML private ChoiceBox newProductionOrderWaferChoiceBox;
	@FXML private Slider newProductionOrderOutputAmountSlider;
	@FXML private TextField newProductionOrderOutputAmountTextField;
	@FXML private ChoiceBox newProductionOrderCaseChoiceBox;
	@FXML private TextField newProductionOrderWaferStorageAmountTextField;
	@FXML private TextField newProductionOrderCaseStorageAmountTextField;
	@FXML private TextField newProductionOrderCostsTextField;
	@FXML private TableView productionOrdersTableView;
	@FXML private TextField machineryLevelTextField;
	@FXML private TextField machineryMaximumCapacityTextField;
	@FXML private ProgressBar machineryWorkloadProgressBar;
	@FXML private CheckBox machineryIncreaseLevelCheckBox;
    //Storage
	@FXML private TextField storageCostsWaferTextField;
	@FXML private TextField storageCostsCasesTextField;
	@FXML private TextField storageCostsPanelsTextField;
	@FXML private TableView storagePositionsTableView;
    //Sales
	@FXML private Button newSaleOfferButton;
	@FXML private Button newSaleOfferSaveButton;
	@FXML private ChoiceBox newSaleOfferArticleChoiceBox;
	@FXML private Slider newSaleOfferArticleAmountSlider;
	@FXML private TextField newSaleOfferArticleAmountTextField;
	@FXML private TextField newSaleOfferArticlePriceTextField;
	@FXML private TextField newSaleOfferCostsTextField;
	@FXML private TextField newSaleOfferDistributionCostsTextField;
	@FXML private TextField newSaleOfferMaximumProfitTextField;
	@FXML private TableView salesTableView;
    //HumanResources
	@FXML private TextField hrWagesPerHourTextField;
	@FXML private TextField hrAverageWagesTextField;
	@FXML private TextField hrCountEmployeesTextField;
	@FXML private TextField hrWageCostsTextField;
    // -- Benefits ausgelassen; TODO: Benefits umbauen und anpassen
	@FXML private LineChart hrMotivationLineChart;
    //Marketing
    // -- ausgelassen; TODO: MarketingUI besprechen, umbauen und anpassen
    //Reporting
	@FXML private TextField reportingSalesFixCostsTextField;
	@FXML private TextField reportingProductionFixCostsTextField;
	@FXML private TextField reportingStorageFixCostsTextField;
	@FXML private TextField reportingPurchaseFixCostsTextField;
	@FXML private TextField reportingHRFixCostsTextField;
	@FXML private TextField reportingMarketingFixCostsTextField;
	@FXML private TextField reportingReportingFixCostsTextField;
	@FXML private TextField reportingMachineryLevelTextField;
	@FXML private TextField reportingMachineryMaxCapacityTextField;
	@FXML private ProgressBar reportingMachineryAvgWorkloadProgressBar;
	@FXML private ProgressBar reportingMachineryLastRoundWorkloadProgressBar;
	@FXML private BarChart reportingSalesBarChart;
	@FXML private LineChart reportingCompanyValueLineChart;

/**
 * parsed alle GameDatas aus einer Message
 * @param in Message, die geparsed werden soll
 */
	public void parseAnswerFromServer(GameDataMessageToClient in){
		parsePurchase(in.purchase);			
	}
	
	
	
	/**
	 * hier werden die Daten für den Verkauf geparsed
	 * @param in das Klassen objekt für den Verkauf
	 */
	private void parsePurchase(PurchaseToClient in){
		
		
			for(int i=in.requests.size()-1; i>=0; i--) {
				RequestToClient req = in.requests.get(i);
			
			Request request = new Request(req, i);
			purchaseRequestTableData.add(request);
		}			
		
	}
	
	
	
    /**
     * Hier werden alle Felder des UIs initialisiert, die initial beim Aufrufen des UIs gefuellt sein sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
    	
    	this.model = new ClientGameUIModel();
    	processRoundProgressBar(model.getRound());
    	
    	purchaseRequestArtikelTableColumn.setCellValueFactory(
    			new PropertyValueFactory<Request, String>("name")
    			);
    	
    	purchaseRequestIdTableColumn.setCellValueFactory(
    			new PropertyValueFactory<Request, String>("id")
    			);
    	
    	purchaseRequestStatusTableColumn.setCellValueFactory(
    			new PropertyValueFactory<Request, String>("status")
    			);
    	
    	purchaseRequestQualityTableColumn.setCellValueFactory(
    			new PropertyValueFactory<Request, String>("quality")
    			);
    	
    	purchaseRequestsTableView.setItems(
				 purchaseRequestTableData
				);
    	
    	// Verhindert, dass man eine neue Spalte durch schieben hinzufügen kann
    	purchaseRequestsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);    	    	
    	
    	purchaseRequestIdTableColumn.setSortType(TableColumn.SortType.DESCENDING);
    	purchaseRequestsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);    	
    	purchaseRequestsTableView.getSelectionModel().selectedItemProperty().addListener(
    			new ChangeListener<Request>() {
    				public void changed(ObservableValue<? extends Request> observable, Request oldValue, Request newValue) {
    					purchaseOffersTableData.clear();
    					for( SupplierOffer o:newValue.getOffer()) {
    						purchaseOffersTableData.add(o);
    					}
    				}
    			}
    			);
    	    	
    	purchaseOffersArtikelTableColumn.setCellValueFactory(
    			new PropertyValueFactory<SupplierOffer, String>("name")
    			);
    	purchaseOffersIdTableColumn.setCellValueFactory(
    			new PropertyValueFactory<SupplierOffer, String>("id")
    			);
    	purchaseOffersQualityTableColumn.setCellValueFactory(
    			new PropertyValueFactory<SupplierOffer, String>("quality")
    			);
    	purchaseOffersQuantityTableColumn.setCellValueFactory(
    			new PropertyValueFactory<SupplierOffer, String>("quantity")
    			);
    	purchaseOffersPriceTableColumn.setCellValueFactory(
    			new PropertyValueFactory<SupplierOffer, String>("price")
    			);
    	purchaseOffersTableView.setItems(
    			purchaseOffersTableData
    			);
    	
    	purchaseOffersTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
    	purchaseOffersTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  
    	
    	
    	/**
    	 * Hier werden alle Buttons mit ActionListenern versehen.
    	 */
    	
    	endRoundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {           	
            	model.setRound(model.getRound()+1);    
            	//TEST
            	processRoundProgressBar(model.getRound());
            }
        }); 
    	
    	newPurchaseRequestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {                  	
            	//Felder resetten
            	newPurchaseRequestArticleNameChoiceBox.getSelectionModel().clearSelection();
            	newPurchaseRequestArticleQualitySlider.adjustValue(1.0);
            	newPurchaseRequestArticleQualityTextField.setText("1.0");
            }
        }); 
    	
    	newPurchaseRequestSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {  
            	purchaseRequestTableData.add(
            			new Request(
            					newPurchaseRequestArticleNameChoiceBox.getValue().toString(),
            					String.valueOf(((int) newPurchaseRequestArticleQualitySlider.getValue())))); 
            			model.addRequest(new RequestFromClient(newPurchaseRequestArticleNameChoiceBox.getValue().toString(),
            					((int) newPurchaseRequestArticleQualitySlider.getValue())));
            			
            			newPurchaseRequestArticleNameChoiceBox.getSelectionModel().clearSelection();
                    	newPurchaseRequestArticleQualitySlider.adjustValue(1.0);
                    	newPurchaseRequestArticleQualityTextField.setText("1.0");
            }
        }); 
    	
    	newProductionOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {           	
            	//Felder resetten  	
            }
        }); 
    	
    	newProductionOrderSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {           	
            	       	
            }
        }); 
    	
    	newSaleOfferButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {           	
            	//Felder resetten    	
            }
        }); 
    	
    	newSaleOfferSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {           	
            	          	
            }
        }); 
    	
    	/**
    	 * Hier werden alle Slider bidirektional mit der dazugehörigen Textbox verknüpft. 
    	 * Der Wert wird dabei durch den StringConverter auf einen Integer ohne NKST gesetzt.    	
    	 */
    	
    	StringConverter<Number> stringConverterForSliders = new StringConverter<Number>(){

    		@Override
    		public String toString(Number t){
    			Integer number = t.intValue();
    			return number.toString();
    		}

    		@Override
    		public Number fromString(String string){
    			return Double.parseDouble(string);
    		}

    	};
    	
    	newPurchaseRequestArticleQualityTextField.textProperty().bindBidirectional(newPurchaseRequestArticleQualitySlider.valueProperty(), stringConverterForSliders);
    	newProductionOrderOutputAmountTextField.textProperty().bindBidirectional(newProductionOrderOutputAmountSlider.valueProperty(), stringConverterForSliders);
    	newSaleOfferArticleAmountTextField.textProperty().bindBidirectional(newSaleOfferArticleAmountSlider.valueProperty(), stringConverterForSliders);
    	
    	//TODO:
    	parseAnswerFromServer(GameTestConsole.data);
    	//END WORK
    }
    
    /**
     * Berechnet den Runden-Fortschrittsbalken neu und setzt den neuen Wert. Ausserdem wird das Runden-Label aktualisiert.
     * @param round
     */
    public void processRoundProgressBar(int round){
    	
    	Integer roundInt = new Integer(round);
    	Integer maxRoundsInt = new Integer(model.getMaxRounds());
    	double newProgress = (roundInt.doubleValue() / maxRoundsInt.doubleValue());
    	roundProgressBar.setProgress(newProgress);
    	roundLabel.setText("Runde: "+roundInt.toString());
        
    }

}