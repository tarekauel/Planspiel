package Client.UI;

/**
 * Created by:
 * User: Lars Trey
 * Date: 08.10.13
 * Time: 16:35
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import Client.UI.ClientGameUIModel.Offer;
import Client.UI.ClientGameUIModel.ProductionOrder;
import Client.UI.ClientGameUIModel.Request;
import Client.UI.ClientGameUIModel.StoragePosition;
import Client.UI.ClientGameUIModel.SupplierOffer;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Message.GameDataMessageToClient.StorageToClient.StorageElementToClient;


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
	@FXML private TitledPane newPurchaseRequestTitledPane;
	@FXML private Button newPurchaseRequestButton;
	@FXML private Button newPurchaseRequestSaveButton;
	@FXML private ChoiceBox<String> newPurchaseRequestArticleNameChoiceBox;
	@FXML private Slider newPurchaseRequestArticleQualitySlider;
	@FXML private TextField newPurchaseRequestArticleQualityTextField;
	@FXML private TableView<Request> purchaseRequestsTableView;
	@FXML private TableColumn<Request,String> purchaseRequestArticleTableColumn;
	@FXML private TableColumn<Request,Integer> purchaseRequestIdTableColumn;
	@FXML private TableColumn<Request,String> purchaseRequestQualityTableColumn;
	@FXML private TableColumn<Request,String> purchaseRequestStatusTableColumn;
	@FXML private TableView<SupplierOffer> purchaseOffersTableView;
	@FXML private TableColumn<SupplierOffer,Integer> purchaseOffersIdTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersArticleTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersQualityTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersQuantityTableColumn;
	@FXML private TableColumn<SupplierOffer,String> purchaseOffersPriceTableColumn;	
    //Production
	@FXML private TitledPane newProductionOrderTitledPane;
	@FXML private Button newProductionOrderButton;
	@FXML private Button newProductionOrderSaveButton;
	@FXML private ChoiceBox<StorageElementToClient> newProductionOrderWaferChoiceBox;
	@FXML private Slider newProductionOrderOutputQuantitySlider;
	@FXML private TextField newProductionOrderOutputQuantityTextField;
	@FXML private ChoiceBox<StorageElementToClient> newProductionOrderCaseChoiceBox;
	@FXML private TextField newProductionOrderWaferStorageQuantityTextField;
	@FXML private TextField newProductionOrderCaseStorageQuantityTextField;
	@FXML private TextField newProductionOrderCostsTextField;
	@FXML private TableView<ProductionOrder> productionOrdersTableView;
	@FXML private TableColumn<ProductionOrder,Integer> productionOrderIdTableColumn;
	@FXML private TableColumn<ProductionOrder,String> productionOrderQualityWaferTableColumn;
	@FXML private TableColumn<ProductionOrder,String> productionOrderQualityCaseTableColumn;
	@FXML private TableColumn<ProductionOrder,String> productionOrderTargetQuantityTableColumn;
	@FXML private TableColumn<ProductionOrder,String> productionOrderQualityPanelTableColumn;	
	@FXML private TableColumn<ProductionOrder,String> productionOrderActualQuantityTableColumn;	
	@FXML private TableColumn<ProductionOrder,String> productionOrderCostsPerUnitTableColumn;	
	@FXML private TextField machineryLevelTextField;
	@FXML private TextField machineryMaximumCapacityTextField;
	@FXML private ProgressBar machineryWorkloadProgressBar;
	@FXML private CheckBox machineryIncreaseLevelCheckBox;
    //Storage
	private ObservableList<StorageElementToClient> resourcesInStorage = FXCollections.observableArrayList();
	private ObservableList<StorageElementToClient> waferInStorage = FXCollections.observableArrayList();
	private ObservableList<StorageElementToClient> casesInStorage = FXCollections.observableArrayList();
	@FXML private TextField storageCostsWaferTextField;
	@FXML private TextField storageCostsCasesTextField;
	@FXML private TextField storageCostsPanelsTextField;
	@FXML private TableView storagePositionsTableView;
	@FXML private TableColumn<StoragePosition,Integer> storagePositionIdTableColumn;
	@FXML private TableColumn<StoragePosition,String> storagePositionRessourceTableColumn;
	@FXML private TableColumn<StoragePosition,String> storagePositionQualityTableColumn;
	@FXML private TableColumn<StoragePosition,String> storagePositionQuantityTableColumn;
	@FXML private TableColumn<StoragePosition,String> storagePositionCostsTableColumn;	
    //Sales
	@FXML private Button newSaleOfferButton;
	@FXML private Button newSaleOfferSaveButton;
	@FXML private ChoiceBox<StorageElementToClient> newSaleOfferArticleChoiceBox;
	@FXML private Slider newSaleOfferArticleQuantitySlider;
	@FXML private TextField newSaleOfferArticleQuantityTextField;
	@FXML private TextField newSaleOfferArticlePriceTextField;
	@FXML private TextField newSaleOfferCostsTextField;
	@FXML private TextField newSaleOfferDistributionCostsTextField;
	@FXML private TextField newSaleOfferMaximumProfitTextField;
	@FXML private TitledPane newSaleOfferTitledPane;
	@FXML private TableView salesTableView;
	@FXML private TableColumn<Offer,Integer> salesIdTableColumn;
	@FXML private TableColumn<Offer,String> salesProductTableColumn;
	@FXML private TableColumn<Offer,String> salesQualityTableColumn;
	@FXML private TableColumn<Offer,String> salesPriceTableColumn;
	@FXML private TableColumn<Offer,String> salesQuantityTableColumn;
	@FXML private TableColumn<Offer,String> salesSoldQuantityTableColumn;
	@FXML private TableColumn<Offer,String> salesProfitTableColumn;
	@FXML private TableColumn<Offer,String> salesCostsTableColumn;
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
	@FXML private BarChart<String, Long> reportingSalesBarChart;
	@FXML private LineChart reportingCompanyValueLineChart;
	
    /**
     * Hier werden alle Felder des UIs initialisiert, die initial beim Aufrufen des UIs gefuellt sein sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	initGeneral();
    	initPurchase();
    	initProduction();
    	
    	initSales();
    	initStorage();
//    	initHumanResources();
//    	initMarketing();
//    	initReporting();
    		
    	model.parseAnswerFromServer();
    	
    	buildSalesChart();
    	//END WORK
    	
    }

	

	private void initGeneral() {
		
    	this.model = new ClientGameUIModel();
    	processRoundProgressBar(model.getRound());
    	
    	/**
    	 * Elementübergreifende Einstellungen
    	 */
    	
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
    	newProductionOrderOutputQuantityTextField.textProperty().bindBidirectional(newProductionOrderOutputQuantitySlider.valueProperty(), stringConverterForSliders);
    	newSaleOfferArticleQuantityTextField.textProperty().bindBidirectional(newSaleOfferArticleQuantitySlider.valueProperty(), stringConverterForSliders);
    	
    	/**
    	 * ActionListener
    	 */
    	
    	endRoundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {           	
            	model.setRound(model.getRound()+1);    
            	//TEST
            	processRoundProgressBar(model.getRound());
            }
        }); 
		
	}

	private void initPurchase() {
		
		/**
    	 * purchaseRequestTable: CellFactory
    	 */
    	
    	purchaseRequestArticleTableColumn.setCellValueFactory(
    		new PropertyValueFactory<Request, String>("name")
    	);
    	
    	purchaseRequestIdTableColumn.setCellValueFactory(
    		new PropertyValueFactory<Request, Integer>("id")
    	);
    	
    	purchaseRequestStatusTableColumn.setCellValueFactory(
    		new PropertyValueFactory<Request, String>("status")
    	);
    	
    	purchaseRequestQualityTableColumn.setCellValueFactory(
    		new PropertyValueFactory<Request, String>("quality")
    	);
    
    	purchaseRequestsTableView.setItems(model.getPurchaseRequestTableData());
    	
    	/**
    	 * purchaseRequestTable: Misc
    	 */
    	
    	// Verhindert, dass man eine neue Spalte durch schieben hinzufügen kann
    	purchaseRequestsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);    	    	
    	
    	purchaseRequestIdTableColumn.setSortType(TableColumn.SortType.DESCENDING);
    	purchaseRequestsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);    	
    	purchaseRequestsTableView.getSelectionModel().selectedItemProperty().addListener(
    		new ChangeListener<Request>() {
    			public void changed(ObservableValue<? extends Request> observable, Request oldValue, Request newValue) {
    				model.getPurchaseOffersTableData().clear();
    				for( SupplierOffer o : newValue.getOffer() ) {
    					model.getPurchaseOffersTableData().add(o);
    					if( o.getRound() == model.getRound()-1 ) {
    						purchaseOffersTableView.setEditable(true);
    					   	purchaseOffersQuantityTableColumn.setEditable(true);
    				    	// TODO an dieser stelle sollte das Bearbeiten der Rechten Tabelle der Spalte Menge moeglich sein!
    					}
    				}
    			}
    		}
    	);
    	
    	/**
    	 * purchaseOffersTable: CellFactory
    	 */
    	    	
    	purchaseOffersArticleTableColumn.setCellValueFactory(
    		new PropertyValueFactory<SupplierOffer, String>("name")
    	);
    	purchaseOffersIdTableColumn.setCellValueFactory(
    		new PropertyValueFactory<SupplierOffer, Integer>("id")
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
    	
    	purchaseOffersPriceTableColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
    	purchaseOffersTableView.setItems(model.getPurchaseOffersTableData());    	
    	
    	/**
    	 * purchaseOffersTable: Misc
    	 */
    	
    	purchaseOffersTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
    	purchaseOffersTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	
    	/**
    	 * ActionListener
    	 */
    	
    	newPurchaseRequestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {                  	
            	//Felder resetten
            	newPurchaseRequestTitledPane.setDisable(false);
            	newPurchaseRequestArticleNameChoiceBox.getSelectionModel().clearSelection();
            	newPurchaseRequestArticleQualitySlider.adjustValue(1.0);
            }
        }); 
    	
    	newPurchaseRequestSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {             	
            	model.getPurchaseRequestTableData().add(
        			new Request(
    					newPurchaseRequestArticleNameChoiceBox.getValue().toString(),
    					newPurchaseRequestArticleQualityTextField.getText()
        			)
            	); 
    			model.addRequest(
					new RequestFromClient(
						newPurchaseRequestArticleNameChoiceBox.getValue().toString(),
						Integer.parseInt(newPurchaseRequestArticleQualityTextField.getText())
					)
    			);
    			
    			newPurchaseRequestArticleNameChoiceBox.getSelectionModel().clearSelection();
            	newPurchaseRequestArticleQualitySlider.adjustValue(1.0);
            	newPurchaseRequestTitledPane.setDisable(true);            	
            }
        });
		
	}
	
	private void getResourcesInStorage(){
		
		resourcesInStorage.clear();
		waferInStorage.clear();
		casesInStorage.clear();
		
		for (int i = 0; i < model.getIn().storage.storageElements.size(); i++) {
			StorageElementToClient tmp = model.getIn().storage.storageElements.get(i);
			
			if (tmp.type.equals("Wafer")) {
				waferInStorage.add(tmp);
			} else if (tmp.type.equals("Gehäuse")) {
				casesInStorage.add(tmp);
			} else {
				resourcesInStorage.add(tmp);
			}
			
		}
	}
	
	private void calcMaximumProduction(){
		
		int qWaferInStorage = Integer.parseInt(newProductionOrderWaferStorageQuantityTextField.getText());
		int qCasesInStorage = Integer.parseInt(newProductionOrderCaseStorageQuantityTextField.getText());
		int qCasesNeededforMaxPanels = qWaferInStorage/54;
		
		if(qCasesNeededforMaxPanels > qCasesInStorage){
			newProductionOrderOutputQuantitySlider.setMax(qCasesInStorage);
		} else {			
			newProductionOrderOutputQuantitySlider.setMax(qCasesNeededforMaxPanels);
		}
		
	}
	
	private void calcAndSetMachinery(){
		
		int maxCapacity = model.getIn().reporting.machinery.maxCapacity;
		int cumulativeWorkload = 0;
		
		for (ProductionOrder x : model.getProductionOrdersTableData()) {
			int targetQuantity = Integer.parseInt(x.getTargetQuantity());
			cumulativeWorkload += targetQuantity;
		}
		
		double workload = cumulativeWorkload/maxCapacity;
		
		machineryLevelTextField.setText(model.getIn().reporting.machinery.level+"");
		machineryMaximumCapacityTextField.setText(maxCapacity+"");		
		machineryWorkloadProgressBar.setProgress(workload);
		
	}
	
	private void initProduction() {
		
		/**
    	 * productionOrdersTable: CellFactory
    	 */
		
		productionOrderIdTableColumn.setCellValueFactory(
    		new PropertyValueFactory<ProductionOrder, Integer>("id")
    	);
    	
		productionOrderQualityWaferTableColumn.setCellValueFactory(
    		new PropertyValueFactory<ProductionOrder, String>("qualityWafer")
    	);
    	
		productionOrderQualityCaseTableColumn.setCellValueFactory(
    		new PropertyValueFactory<ProductionOrder, String>("qualityCase")
    	);
    	
		productionOrderTargetQuantityTableColumn.setCellValueFactory(
    		new PropertyValueFactory<ProductionOrder, String>("targetQuantity")
    	);
		
		productionOrderQualityPanelTableColumn.setCellValueFactory(
    		new PropertyValueFactory<ProductionOrder, String>("qualityPanel")
    	);
	
		productionOrderActualQuantityTableColumn.setCellValueFactory(
    		new PropertyValueFactory<ProductionOrder, String>("actualQuantity")
    	);
		
		productionOrderCostsPerUnitTableColumn.setCellValueFactory(
    		new PropertyValueFactory<ProductionOrder, String>("costsPerUnit")
    	);
    
		productionOrderCostsPerUnitTableColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		productionOrdersTableView.setItems(model.getProductionOrdersTableData());
		
		/**
    	 * Misc
    	 */
		
		calcAndSetMachinery();		

    	/**
    	 * ActionListener
    	 */
    	
    	newProductionOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {  
            	getResourcesInStorage();
            	newProductionOrderTitledPane.setDisable(false);            	
            	newProductionOrderWaferChoiceBox.getItems().setAll(waferInStorage);   
            	newProductionOrderCaseChoiceBox.getItems().setAll(casesInStorage);                	
            }
        }); 
    	
    	newProductionOrderSaveButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
            public void handle(ActionEvent actionEvent) {             	
    			model.getProductionOrdersTableData().add(
        			new ProductionOrder(
        				newProductionOrderWaferChoiceBox.getValue().quality+"", 
        				newProductionOrderCaseChoiceBox.getValue().quality+"", 
        				newProductionOrderOutputQuantityTextField.getText()
        			)
            	); 
    			
    			calcAndSetMachinery();
    			//newProductionOrderWaferChoiceBox.getSelectionModel().clearSelection();
            	//newProductionOrderCaseChoiceBox.getSelectionModel().clearSelection();
            	newProductionOrderWaferStorageQuantityTextField.clear();           	
            	newProductionOrderCaseStorageQuantityTextField.clear();
            	newProductionOrderCostsTextField.clear();
            	newProductionOrderOutputQuantitySlider.adjustValue(0.0);
            	newProductionOrderTitledPane.setDisable(true);     
            	
            }
        }); 
    	    	
    	newProductionOrderWaferChoiceBox.valueProperty().addListener(
    		new ChangeListener<StorageElementToClient>() {
    			public void changed(ObservableValue<? extends StorageElementToClient> observable, StorageElementToClient oldValue, StorageElementToClient newValue) {
    				newProductionOrderWaferStorageQuantityTextField.setText(newValue.quantity+""); 
    				
    				if(newProductionOrderCaseChoiceBox.getValue() != null){
    					calcMaximumProduction();    					
    				}
    				
    			}
    		}
	    );
		
		newProductionOrderCaseChoiceBox.valueProperty().addListener(
    		new ChangeListener<StorageElementToClient>() {
    			public void changed(ObservableValue<? extends StorageElementToClient> observable, StorageElementToClient oldValue, StorageElementToClient newValue) {
    				newProductionOrderCaseStorageQuantityTextField.setText(newValue.quantity+"");
    				
    				if(newProductionOrderWaferChoiceBox.getValue() != null){
    					calcMaximumProduction();    					
    				}
    					
    				
    			}
    		}
	    );
		
	}
	
	private void initStorage() {		
		
		/**
    	 * storagePositionsTable: CellFactory
    	 */
		
		storagePositionIdTableColumn.setCellValueFactory(
		    new PropertyValueFactory<StoragePosition, Integer>("id")
	    );
		    	
		storagePositionRessourceTableColumn.setCellValueFactory(
		   	new PropertyValueFactory<StoragePosition, String>("ressource")
		);
		    	
		storagePositionQualityTableColumn.setCellValueFactory(
	    	new PropertyValueFactory<StoragePosition, String>("quality")
	    );
		
		storagePositionQuantityTableColumn.setCellValueFactory(
		   	new PropertyValueFactory<StoragePosition, String>("quantity")
		);
		    	
		storagePositionCostsTableColumn.setCellValueFactory(
	    	new PropertyValueFactory<StoragePosition, String>("costs")
	    );
		
		storagePositionCostsTableColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		
		storagePositionsTableView.setItems(model.getStoragePositionsTableData()); 
		
		storageCostsWaferTextField.setText(model.getnFormatter().format(model.getIn().storage.storageCostsWafer / 100.0));
		storageCostsCasesTextField.setText(model.getnFormatter().format(model.getIn().storage.storageCostsCase / 100.0));
		storageCostsPanelsTextField.setText(model.getnFormatter().format(model.getIn().storage.storageCostsPanel / 100.0));
		
		/**
    	 * ActionListener
    	 */
		
	}
	
	private void initSales() {
		
		/**
    	 * salesTableView: CellFactory
    	 */

		salesIdTableColumn.setCellValueFactory(
			new PropertyValueFactory<Offer, Integer>("id")
		);
		salesProductTableColumn.setCellValueFactory(
			new PropertyValueFactory<Offer, String>("product")
		);
		salesQualityTableColumn.setCellValueFactory(
			new PropertyValueFactory<Offer, String>("quality")
		);
		salesPriceTableColumn.setCellValueFactory(
			new PropertyValueFactory<Offer, String>("price")
		);
		salesQuantityTableColumn.setCellValueFactory(
			new PropertyValueFactory<Offer, String>("quantity")
		);
		salesSoldQuantityTableColumn.setCellValueFactory(
			new PropertyValueFactory<Offer, String>("soldQuantity")
		);
		salesProfitTableColumn.setCellValueFactory(
		    new PropertyValueFactory<Offer, String>("profit")
		);
		salesCostsTableColumn.setCellValueFactory(
			new PropertyValueFactory<Offer, String>("costs")
	    );
		
		salesTableView.setItems(model.getOfferTableData());
		
		/**
    	 * ActionListener
    	 */
		
		newSaleOfferButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { 
            	getResourcesInStorage();
            	newSaleOfferArticleChoiceBox.getItems().setAll(resourcesInStorage);
            	newSaleOfferTitledPane.setDisable(false);
            }
        }); 
    	
    	newSaleOfferSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {    
            	
            	model.getOfferTableData().add(
        			new Offer(
        					newSaleOfferArticleChoiceBox.getValue()+"", newSaleOfferArticleQuantityTextField.getText(), newSaleOfferArticlePriceTextField.getText() 
        			)
                ); 
            	
            	
            	newSaleOfferTitledPane.setDisable(true);
            }
        });
		
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
    
    public void buildSalesChart() {
    	int x=0;
    	for( ArrayList<Data<String, Long>> seriesData : model.getSalesChartData()) {
    		x++;
    		XYChart.Series<String, Long> series = new XYChart.Series<String, Long>();
    		series.setName("Bla");
    		for(Data<String, Long> singleData : seriesData) {
    			series.getData().add(singleData);
    		}
    		ObservableList<Series<String, Long>> data = reportingSalesBarChart.getData();
    		data.add(series);
    		if( x== 4)
    		break;
    	}
    }

}