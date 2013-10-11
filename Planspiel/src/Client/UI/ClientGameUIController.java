package Client.UI;

/**
 * Created by:
 * User: Lars Trey
 * Date: 08.10.13
 * Time: 16:35
 */

import java.net.URL;
import java.util.ResourceBundle;

import aaaaa.GameTestConsole;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import Message.GameDataMessageToClient;
import Message.GameDataMessageToClient.PurchaseToClient.RequestToClient;


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
	@FXML private TableView purchaseRequestsTableView;
	@FXML private TableView purchaseOffersTableView;
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


	public void parseAnswerFromServer(GameDataMessageToClient in){
		
		for (RequestToClient r:in.purchase.requests){
			machineryLevelTextField.setText(r.name);
		}
		
		
		
	}
	
    /**
     * Hier werden alle Felder des UIs initialisiert, die initial beim Aufrufen des UIs gefuellt sein sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
    	
    	this.model = new ClientGameUIModel();
    	
    	processRoundProgressBar(model.getRound());
    	
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
            	newPurchaseRequestArticleQualitySlider.adjustValue(0.0);
            	newPurchaseRequestArticleQualityTextField.setText("0.0");
            }
        }); 
    	
    	newPurchaseRequestSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {           	

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