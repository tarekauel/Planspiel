package Client.UI;

/**
 * Created by:
 * User: Lars Trey
 * Date: 08.10.13
 * Time: 16:35
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import Client.UI.ClientGameUIModel.BenefitBooking;
import Client.UI.ClientGameUIModel.Offer;
import Client.UI.ClientGameUIModel.ProductionOrder;
import Client.UI.ClientGameUIModel.Request;
import Client.UI.ClientGameUIModel.StoragePosition;
import Client.UI.ClientGameUIModel.SupplierOffer;
import Message.GameDataMessageFromClient.PurchaseFromClient.RequestFromClient;
import Message.GameDataMessageToClient.HumanResourcesToClient.BenefitBookingToClient;
import Message.GameDataMessageToClient.StorageToClient.StorageElementToClient;


/**
 * Dies ist die Controller-Klasse der Game-Stage. Hier wird alles implementiert, was das Game-UI manipulieren soll.
 * @author Lars Trey
 */
public class ClientGameUIController implements Initializable{
	
	private ClientGameUIModel model;

	/**
	 * Hier werden alle Felder des UIs mit Variablen verkn�pft.
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
	@FXML private TextField machineryPlannedCapacityTextField;
	@FXML private ProgressBar machineryWorkloadProgressBar;
	@FXML private CheckBox machineryIncreaseLevelCheckBox;	
    //Storage
	private ObservableList<StorageElementToClient> resourcesInStorage = FXCollections.observableArrayList();
	private ObservableList<StorageElementToClient> waferInStorage = FXCollections.observableArrayList();
	private ObservableList<StorageElementToClient> casesInStorage = FXCollections.observableArrayList();
	@FXML private TextField storageCostsWaferTextField;
	@FXML private TextField storageCostsCasesTextField;
	@FXML private TextField storageCostsPanelsTextField;
	@FXML private TableView<StoragePosition> storagePositionsTableView;
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
	@FXML private TableView<Offer> salesTableView;
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
	@FXML private ChoiceBox<BenefitBookingToClient> benefitsChoiceBox;
	private ObservableList<BenefitBookingToClient> bookableBenefits = FXCollections.observableArrayList();
	@FXML private Button bookBenefitButton;
	@FXML private TextField bookBenefitDurationTextField;
	@FXML private TextField bookBenefitCostsPerRoundTextField;
	@FXML private TextField benefitsTotalCostsTextField;
	@FXML private TableView<BenefitBooking> bookedBeneftisTableView;
	@FXML private LineChart<String, Double> hrMotivationLineChart;
	@FXML private CategoryAxis hrMotivationLineChartXAxis;
	@FXML private NumberAxis hrMotivationLineChartYAxis; 
    //Marketing
	@FXML private TextField marketResearchAverageWagesLastRoundTextField;
	@FXML private TextField marketResearchPeakAMarketTextField;
	@FXML private TextField marketResearchPeakCMarketTextField;
	@FXML private LineChart<String, Double> marketingWaferPriceChart;
	@FXML private CategoryAxis marketingWaferPriceChartXAxis;
	@FXML private NumberAxis marketingWaferPriceChartYAxis; 
	@FXML private LineChart<String, Double> marketingCasePriceChart;
	@FXML private CategoryAxis marketingCasePriceChartXAxis;
	@FXML private NumberAxis marketingCasePriceChartYAxis;
	@FXML private PieChart marketingMarketSharePieChart;	
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
	@FXML private StackedBarChart<String, Double> reportingSalesBarChart;
	@FXML private CategoryAxis reportingSalesBarChatXAxis;
	@FXML private NumberAxis reportingSalesBarChatYAxis; 
	@FXML private LineChart reportingCompanyValueLineChart;
	
    /**
     * Hier werden alle Felder des UIs initialisiert, die initial beim Aufrufen des UIs gefuellt sein sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	initGeneral();
    	initStorage();
    	initPurchase();
    	initProduction();    	
    	initSales();    	
    	initHumanResources();
    	initMarketing();
    	initReporting();
    		
    	model.parseAnswerFromServer();
    	
    	
    	//  START BUILDING SALES CHART
    	String[] cat = new String[5];
    	int index=0;
    	for(int i=this.model.getRound()-5; i < this.model.getRound(); i++) {
    		cat[index++] = "Runde " + i;
    	}
    	
    	buildXYChart(this.model.getSalesChartData(), cat, reportingSalesBarChatXAxis, reportingSalesBarChatYAxis, reportingSalesBarChart);
    	
    	// START BULDING MOTIVATION CHART
    	
    	String[] catMot = new String[this.model.getRound()];
    	for( int i=0; i<catMot.length; i++) {
    		catMot[i] = (i+1)+"";
    	}
    	
    	buildXYChart(this.model.getMotivationChartData(), catMot, hrMotivationLineChartXAxis, hrMotivationLineChartYAxis, hrMotivationLineChart);
    	
    	// START BUILDING WAFER PRICE CHART
    	String[] catWafer = new String[100];
    	for( int i=0; i<catWafer.length; i++) {
    		catWafer[i] = (i+1) + "";
    	}
    	
    	buildXYChart(this.model.getWaferPriceListChartData(), catWafer, marketingWaferPriceChartXAxis, marketingWaferPriceChartYAxis,marketingWaferPriceChart );
    	marketingWaferPriceChart.setCreateSymbols(false);
    	
    	// START BUILDING Case PRICE CHART
    	String[] catCase = new String[100];
    	for( int i=0; i<catCase.length; i++) {
    		catCase[i] = (i+1) + "";
    	}
    	
    	buildXYChart(this.model.getCasePriceListChartData(), catCase, marketingCasePriceChartXAxis, marketingCasePriceChartYAxis,marketingCasePriceChart );
    	marketingCasePriceChart.setCreateSymbols(false);
    	
    	// START BUILDING Market Shares
    	
    	buildPieChart(this.model.getMarketShareChartData(), marketingMarketSharePieChart);
    	
    	//END WORK
    	
    }

	private void initGeneral() {
		
    	this.model = new ClientGameUIModel();
    	processRoundProgressBar(model.getIn().round);
    	
    	/**
    	 * Element�bergreifende Einstellungen
    	 */
    	
    	/**
    	 * Hier werden alle Slider bidirektional mit der dazugeh�rigen Textbox verkn�pft. 
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
	
	class EditingCell extends TableCell<Request, String> {
		
		  private TextField textField;
	     
	      public EditingCell() {}
	     
	      @Override
	      public void startEdit() {
	    	  
	    	  if(purchaseRequestsTableView.getSelectionModel().getSelectedItem().getStatus().equals("Offen")){
	    		  super.startEdit();
	 	         
		          if (textField == null) {
		              createTextField();
		          }
		         
		          setGraphic(textField);
		          setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		          textField.selectAll();
	    	  } else {
	    		  cancelEdit();
	    	  }    	  
	          
	      }
	     
	      @Override
	      public void cancelEdit() {
	          super.cancelEdit();
	         
	          setText(String.valueOf(getItem()));
	          setContentDisplay(ContentDisplay.TEXT_ONLY);
	      }
	 
	      @Override
	      public void updateItem(String item, boolean empty) {
	          super.updateItem(item, empty);
	         
	          if (empty) {
	              setText(null);
	              setGraphic(null);
	          } else {
	              if (isEditing()) {
	                  if (textField != null) {
	                      textField.setText(getString());
	                  }
	                  setGraphic(textField);
	                  setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	              } else {
	                  setText(getString());
	                  setContentDisplay(ContentDisplay.TEXT_ONLY);
	              }
	          }
	      }
	 
	      private void createTextField() {
	          textField = new TextField(getString());
	          textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
	          textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	             
	              @Override
	              public void handle(KeyEvent t) {
	                  if (t.getCode() == KeyCode.ENTER) {
	                      commitEdit(textField.getText());
	                  } else if (t.getCode() == KeyCode.ESCAPE) {
	                      cancelEdit();
	                  }
	              }
	          });
	      }
	     
	      private String getString() {
	          return getItem() == null ? "" : getItem().toString();
	  }
		
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
    	
    	// Verhindert, dass man eine neue Spalte durch schieben hinzuf�gen kann
    	purchaseRequestsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);   	
    	//purchaseRequestIdTableColumn.setSortType(TableColumn.SortType.DESCENDING);
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
    				    }
    				}
    			}
    		}
    	);
    	
    	/**
    	 * purchaseOffersTable: CellFactory
    	 */
    	
    	Callback<TableColumn<SupplierOffer, String>, TableCell<SupplierOffer, String>> cellFactory =
	        new Callback<TableColumn<SupplierOffer, String>, TableCell<SupplierOffer, String>>() {
	            public TableCell call(TableColumn p) {
	                return new EditingCell();
	            }
	        };

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
    	purchaseOffersQuantityTableColumn.setCellFactory(cellFactory);
        purchaseOffersQuantityTableColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<SupplierOffer, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<SupplierOffer, String> t) {
                    ((SupplierOffer)t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setQuantity(t.getNewValue());
                }
            }
        );
        purchaseOffersPriceTableColumn.setCellValueFactory(
    		new PropertyValueFactory<SupplierOffer, String>("price")
    	);
    	
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
            	newPurchaseRequestSaveButton.setDisable(false);
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
            	newPurchaseRequestSaveButton.setDisable(true);
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
			} else if (tmp.type.equals("Geh�use")) {
				casesInStorage.add(tmp);
			} else {
				resourcesInStorage.add(tmp);
			}
			
		}
	}
	
	private boolean calcMaximumProduction(){
		
		int qWaferInStorage = Integer.parseInt(newProductionOrderWaferStorageQuantityTextField.getText());
		int qCasesInStorage = Integer.parseInt(newProductionOrderCaseStorageQuantityTextField.getText());
		int qCasesNeededforMaxPanels = qWaferInStorage/54;
		
		//System.out.println(qCasesNeededforMaxPanels);
		
		if(qCasesNeededforMaxPanels > qCasesInStorage && qCasesInStorage <= model.getIn().reporting.machinery.maxCapacity){
			newProductionOrderOutputQuantitySlider.setMax(qCasesInStorage);
			newProductionOrderOutputQuantitySlider.setValue(qCasesInStorage);			
		} else if(qCasesNeededforMaxPanels <= qCasesInStorage && qCasesNeededforMaxPanels <= model.getIn().reporting.machinery.maxCapacity) {			
			newProductionOrderOutputQuantitySlider.setMax(qCasesNeededforMaxPanels);
			newProductionOrderOutputQuantitySlider.setValue(qCasesNeededforMaxPanels);
		} else if(qCasesInStorage > model.getIn().reporting.machinery.maxCapacity || qCasesNeededforMaxPanels > model.getIn().reporting.machinery.maxCapacity){
			return false;
		}
		
		return true;
		
	}
	
	private boolean calcAndSetMachinery(int productionOrder){
		
		int maxCapacity = model.getIn().reporting.machinery.maxCapacity;
		int cumulativeWorkloadBefore = 0;
		int cumulativeWorkloadAfter = 0;
		double percentWorkloadAfter;
		
		final Tooltip tooltip = new Tooltip();
		
		for (ProductionOrder x : model.getProductionOrdersTableData()) {
			int targetQuantity = Integer.parseInt(x.getTargetQuantity());
			
			if(x.getQualityPanel().equals("")){
				cumulativeWorkloadBefore += targetQuantity;
			} 			
		}
		
		cumulativeWorkloadAfter = cumulativeWorkloadBefore + productionOrder;
		percentWorkloadAfter = (double)cumulativeWorkloadAfter / (double)maxCapacity;
		
		tooltip.setText((percentWorkloadAfter*100)+" %"+" - "+cumulativeWorkloadAfter+" Panels");		
			
		if(cumulativeWorkloadAfter > maxCapacity){
			//machineryWorkloadProgressBar.setProgress(percentWorkloadAfter);
			machineryWorkloadProgressBar.getStyleClass().add("red-bar");
			machineryWorkloadProgressBar.setTooltip(tooltip);
			return false;
		} else if (cumulativeWorkloadAfter <= maxCapacity && percentWorkloadAfter >= 70.0){
			machineryWorkloadProgressBar.setProgress(percentWorkloadAfter);
			machineryWorkloadProgressBar.getStyleClass().add("yellow-bar");
			machineryWorkloadProgressBar.setTooltip(tooltip);
			machineryPlannedCapacityTextField.setText(cumulativeWorkloadAfter+"");
		} else if (cumulativeWorkloadAfter <= maxCapacity && percentWorkloadAfter < 70.0){
			machineryWorkloadProgressBar.setProgress(percentWorkloadAfter);
			machineryWorkloadProgressBar.getStyleClass().add("yellow-bar");		
			machineryWorkloadProgressBar.setTooltip(tooltip);
			machineryPlannedCapacityTextField.setText(cumulativeWorkloadAfter+"");
		}
		
		return true;
		
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
    
		//productionOrderCostsPerUnitTableColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		productionOrdersTableView.setItems(model.getProductionOrdersTableData());
		
		productionOrdersTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);   	
		//productionOrderIdTableColumn.setSortType(TableColumn.SortType.DESCENDING);
    	productionOrdersTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); 
		
		/**
    	 * Misc
    	 */
		
		
		machineryLevelTextField.setText(model.getIn().reporting.machinery.level+"");
		machineryMaximumCapacityTextField.setText(model.getIn().reporting.machinery.maxCapacity+"");
		machineryPlannedCapacityTextField.setText("0");
		//boolean isPossibleMachinery;
		//boolean isPossibleProduction;
		

    	/**
    	 * ActionListener
    	 */
		
		final ChangeListener<StorageElementToClient> newProductionOrderWaferChoiceBoxListener = new ChangeListener<StorageElementToClient>() {
			public void changed(ObservableValue<? extends StorageElementToClient> observable, StorageElementToClient oldValue, StorageElementToClient newValue) {
				newProductionOrderWaferStorageQuantityTextField.setText(newValue.quantity+""); 
				
				if(newProductionOrderCaseChoiceBox.getValue() != null){
					calcMaximumProduction();    					
				}
				
			}
		};  
		
		final ChangeListener<StorageElementToClient> newProductionOrderCaseChoiceBoxListener = new ChangeListener<StorageElementToClient>() {
			public void changed(ObservableValue<? extends StorageElementToClient> observable, StorageElementToClient oldValue, StorageElementToClient newValue) {
				
				newProductionOrderCaseStorageQuantityTextField.setText(newValue.quantity+"");
				
				if(newProductionOrderWaferChoiceBox.getValue() != null){
					calcMaximumProduction();    					
				}				
				
			}
		};    	
    	
    	newProductionOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {  
            	getResourcesInStorage();
            	newProductionOrderTitledPane.setDisable(false);  
            	//newProductionOrderSaveButton.setDisable(false);
            	newProductionOrderWaferChoiceBox.valueProperty().removeListener(newProductionOrderWaferChoiceBoxListener);		
        		newProductionOrderCaseChoiceBox.valueProperty().removeListener(newProductionOrderCaseChoiceBoxListener);    			
    			newProductionOrderWaferChoiceBox.getSelectionModel().clearSelection();
    			newProductionOrderWaferChoiceBox.getItems().clear();
            	newProductionOrderCaseChoiceBox.getSelectionModel().clearSelection();
            	newProductionOrderCaseChoiceBox.getItems().clear();
            	newProductionOrderWaferStorageQuantityTextField.clear();           	
            	newProductionOrderCaseStorageQuantityTextField.clear();
            	newProductionOrderCostsTextField.clear();
            	newProductionOrderOutputQuantitySlider.adjustValue(0.0);
            	newProductionOrderWaferChoiceBox.getItems().setAll(waferInStorage);   
            	newProductionOrderCaseChoiceBox.getItems().setAll(casesInStorage);             	
            	newProductionOrderWaferChoiceBox.valueProperty().addListener(newProductionOrderWaferChoiceBoxListener);		
        		newProductionOrderCaseChoiceBox.valueProperty().addListener(newProductionOrderCaseChoiceBoxListener);
            }
        }); 
    	
    	newProductionOrderSaveButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
            public void handle(ActionEvent actionEvent) {             	

    			if(newProductionOrderCaseChoiceBox.getValue() != null && newProductionOrderWaferChoiceBox.getValue() != null){
					boolean isPossibleMachinery = calcAndSetMachinery(Integer.parseInt(newProductionOrderOutputQuantityTextField.getText()));
					
					if(isPossibleMachinery == true){
						model.getProductionOrdersTableData().add(
		        			new ProductionOrder(
		        				newProductionOrderWaferChoiceBox.getValue().quality+"", 
		        				newProductionOrderCaseChoiceBox.getValue().quality+"", 
		        				newProductionOrderOutputQuantityTextField.getText()
		        			)
			            ); 						
					} else {
						
					}
					
				}
    			
    			newProductionOrderWaferChoiceBox.valueProperty().removeListener(newProductionOrderWaferChoiceBoxListener);		
        		newProductionOrderCaseChoiceBox.valueProperty().removeListener(newProductionOrderCaseChoiceBoxListener);    			
    			newProductionOrderWaferChoiceBox.getSelectionModel().clearSelection();
    			newProductionOrderWaferChoiceBox.getItems().clear();
            	newProductionOrderCaseChoiceBox.getSelectionModel().clearSelection();
            	newProductionOrderCaseChoiceBox.getItems().clear();
            	newProductionOrderWaferStorageQuantityTextField.clear();           	
            	newProductionOrderCaseStorageQuantityTextField.clear();
            	newProductionOrderCostsTextField.clear();
            	newProductionOrderOutputQuantitySlider.adjustValue(0.0);
            	newProductionOrderTitledPane.setDisable(true);     
            	
            }
        }); 
    	
    	
		
		newProductionOrderOutputQuantitySlider.valueProperty().addListener(
			new ChangeListener<Number>() {					
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {   				
    				
    					
    				
				}
			}				
		);
		
	}
	
	private void calcStorageCosts(){
		
		int costsWafer = 0;
		int costsCases = 0;
		int costsPanels = 0;
		
		int storageCostsWafer = model.getIn().storage.storageCostsWafer;
		int storageCostsCases = model.getIn().storage.storageCostsCase;
		int storageCostsPanels = model.getIn().storage.storageCostsPanel;
		
		for (StorageElementToClient elem : model.getIn().storage.storageElements) {

			if(elem.type.equals("Wafer")){				
				costsWafer += elem.quantity * storageCostsWafer;
			} else if(elem.type.equals("Geh�use")){				
				costsCases += elem.quantity * storageCostsCases;
			} else if(elem.type.equals("Panel")){				
				costsPanels += elem.quantity * storageCostsPanels;
			}
			
		}
		
		storageCostsWaferTextField.setText(model.getnFormatterCurrency().format(costsWafer / 100.0));
		storageCostsCasesTextField.setText(model.getnFormatterCurrency().format(costsCases / 100.0));
		storageCostsPanelsTextField.setText(model.getnFormatterCurrency().format(costsPanels / 100.0));
		
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
		
		//storagePositionCostsTableColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		storagePositionsTableView.setItems(model.getStoragePositionsTableData()); 
		
		storagePositionsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);   	
		//storagePositionIdTableColumn.setSortType(TableColumn.SortType.DESCENDING);
		storagePositionsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); 

		/**
    	 * Misc
    	 */
		
		calcStorageCosts();
		
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
		
		salesTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);   	
		//salesIdTableColumn.setSortType(TableColumn.SortType.DESCENDING);
		salesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); 
		
		/**
    	 * ActionListener
    	 */
		
		final ChangeListener<StorageElementToClient> newSaleOfferArticleChoiceBoxListener = new ChangeListener<StorageElementToClient>() {
			public void changed(ObservableValue<? extends StorageElementToClient> observable, StorageElementToClient oldValue, StorageElementToClient newValue) {
				newSaleOfferArticleQuantitySlider.setMax(newSaleOfferArticleChoiceBox.getValue().quantity);
				newSaleOfferArticleQuantitySlider.setValue(newSaleOfferArticleChoiceBox.getValue().quantity);     				
			}
		};
		
		final ChangeListener<String> newSaleOfferArticlePriceTextFieldListener = new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				newSaleOfferDistributionCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().distribution.costsPerOffer/100));
				newSaleOfferCostsTextField.setText(model.getnFormatterCurrency().format(newSaleOfferArticleChoiceBox.getValue().costs/100));
				int cumulCosts = (newSaleOfferArticleChoiceBox.getValue().costs/100 * Integer.parseInt(newSaleOfferArticleQuantityTextField.getText())) + (model.getIn().distribution.costsPerOffer/100);
				int maxProfit = (Integer.parseInt(newSaleOfferArticlePriceTextField.getText()) * Integer.parseInt(newSaleOfferArticleQuantityTextField.getText())) - cumulCosts;
				newSaleOfferMaximumProfitTextField.setText(model.getnFormatterCurrency().format(maxProfit));   			
			}			
		};

		newSaleOfferButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { 
            	getResourcesInStorage();
            	newSaleOfferArticlePriceTextField.textProperty().removeListener(newSaleOfferArticlePriceTextFieldListener);
            	newSaleOfferArticleChoiceBox.valueProperty().removeListener(newSaleOfferArticleChoiceBoxListener);	
            	newSaleOfferArticleChoiceBox.getSelectionModel().clearSelection();
            	newSaleOfferArticlePriceTextField.clear();   
            	newSaleOfferArticleQuantitySlider.adjustValue(0.0);
            	newSaleOfferArticleQuantityTextField.clear();
            	newSaleOfferCostsTextField.clear();
            	newSaleOfferDistributionCostsTextField.clear();
            	newSaleOfferCostsTextField.clear();
            	newSaleOfferMaximumProfitTextField.clear(); 
            	newSaleOfferArticleChoiceBox.getItems().setAll(resourcesInStorage);
            	newSaleOfferArticleChoiceBox.valueProperty().addListener(newSaleOfferArticleChoiceBoxListener);	
            	newSaleOfferArticlePriceTextField.textProperty().addListener(newSaleOfferArticlePriceTextFieldListener);
            	newSaleOfferTitledPane.setDisable(false);
            	newSaleOfferSaveButton.setDisable(false);
            }
        }); 
    	
    	newSaleOfferSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {    
            	
            	model.getOfferTableData().add(
        			new Offer(
        				newSaleOfferArticleChoiceBox.getValue().quality+"", newSaleOfferArticleQuantityTextField.getText(), Integer.parseInt(newSaleOfferArticlePriceTextField.getText())*100+"" 
        			)
                );        	
            	
            	newSaleOfferArticlePriceTextField.textProperty().removeListener(newSaleOfferArticlePriceTextFieldListener);
            	newSaleOfferArticleChoiceBox.valueProperty().removeListener(newSaleOfferArticleChoiceBoxListener);	
            	newSaleOfferArticleChoiceBox.getSelectionModel().clearSelection();
            	newSaleOfferArticlePriceTextField.clear();   
            	newSaleOfferArticleQuantitySlider.adjustValue(0.0);
            	newSaleOfferArticleQuantityTextField.clear();
            	newSaleOfferCostsTextField.clear();
            	newSaleOfferDistributionCostsTextField.clear();
            	newSaleOfferCostsTextField.clear();
            	newSaleOfferMaximumProfitTextField.clear();            	
            	newProductionOrderTitledPane.setDisable(true); 
            	
            }
        });
    	
	}

	private void initReporting() {
		
		reportingSalesFixCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().reporting.fixCosts.get(0).costs/100));
		reportingHRFixCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().reporting.fixCosts.get(1).costs/100));
		reportingMarketingFixCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().reporting.fixCosts.get(2).costs/100));
		reportingProductionFixCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().reporting.fixCosts.get(3).costs/100));
		reportingPurchaseFixCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().reporting.fixCosts.get(4).costs/100));
		reportingStorageFixCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().reporting.fixCosts.get(5).costs/100));	
		//reportingReportingFixCostsTextField.setText(model.getIn().reporting.fixCosts.get(6)+""); //TODO: Gibt es nicht im MessageObjekt
		
		reportingMachineryLevelTextField.setText(model.getIn().reporting.machinery.level+"");
		reportingMachineryMaxCapacityTextField.setText(model.getIn().reporting.machinery.maxCapacity+"");
		reportingMachineryAvgWorkloadProgressBar.setProgress(model.getIn().reporting.machinery.averageUsage);
		reportingMachineryLastRoundWorkloadProgressBar.setProgress(model.getIn().reporting.machinery.usageLastRound);
		
	}
	
	private void getBookableBenefits(){
		
		bookableBenefits.clear();
		
		for (BenefitBookingToClient benefit : model.getIn().humanResources.benefits) {
			
			System.out.println(benefit.name);
			bookableBenefits.add(benefit);
			
		}
		
	}
	
	private void initMarketing() {
		
		/**
		 * Misc
		 */
		
		//System.out.println(model.getIn().humanResources.averageWage);
		
		if (model.getIn().marketing.isBooked == true) {
			marketResearchAverageWagesLastRoundTextField.setText(model.getIn().humanResources.averageWage+""); //TODO: Ist 0 bei Test
			marketResearchPeakAMarketTextField.setText(model.getIn().marketing.peakAMarket+"");
			marketResearchPeakCMarketTextField.setText(model.getIn().marketing.peakCMarket+"");
		} else {
			marketResearchAverageWagesLastRoundTextField.setText("n.A.");
			marketResearchPeakAMarketTextField.setText("n.A.");
			marketResearchPeakCMarketTextField.setText("n.A.");
		}

	}
	
	private void initHumanResources() {
		
		getBookableBenefits();
		
		/**
		 * Misc
		 */
		
//		System.out.println(model.getIn().humanResources.myWage);
//		System.out.println(model.getIn().humanResources.averageWage);
//		System.out.println(model.getIn().humanResources.wageCosts);
		
		hrWagesPerHourTextField.setText(model.getnFormatterCurrency().format(model.getIn().humanResources.myWage));
		hrAverageWagesTextField.setText(model.getnFormatterCurrency().format(model.getIn().humanResources.averageWage));
		hrCountEmployeesTextField.setText(model.getnFormatter().format(model.getIn().humanResources.countEmployees));
		hrWageCostsTextField.setText(model.getnFormatterCurrency().format(model.getIn().humanResources.wageCosts));
		
		benefitsChoiceBox.setItems(bookableBenefits);
		
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
    
    /**
	 * Diese Methode befuellt Kategorie-Orientierte Daten in ein BarChart
	 * @param data Eine Liste der Daten, die zu jeder Kategorie gehoeren.  
	 * @param categories Bezeichner der Kategorie (gleich lang wie data!)
	 * @param xAxisBar Die XAchse des Diagramms
	 * @param yAxisBar Die YAchse des Diagramms
	 * @param barChart Das Diagramm in dem die Daten erscheinen sollen
	 */
	public static <T> void buildXYChart(ArrayList<HashMap<String, T>> data, String[] categories, CategoryAxis xAxisBar, NumberAxis yAxisBar, XYChart<String, T> barChart) {			
		
		HashMap<String, XYChart.Series<String, T>> seriesMaps = new HashMap<String, XYChart.Series<String, T>>();
		
		for( int i=0; i<data.size(); i++) {
			HashMap<String, T> roundMap = data.get(i);
			for(Map.Entry<String, T> entry : roundMap.entrySet()) {
				XYChart.Series<String, T> roundSeries = seriesMaps.get(entry.getKey());
				if( roundSeries == null) {
					XYChart.Series<String, T> newSerie = new XYChart.Series<>();
					newSerie.setName(entry.getKey());
					newSerie.getData().add(new XYChart.Data<String, T>(categories[i], entry.getValue()));
					seriesMaps.put(entry.getKey(), newSerie);
					barChart.getData().add(newSerie);
				} else {
					roundSeries.getData().add( new XYChart.Data<String, T>(categories[i], entry.getValue()));
				}
			}
		}
		
		xAxisBar.setCategories(FXCollections.observableArrayList(categories));
	}
	
	public static void buildPieChart(HashMap<String, Double> data, PieChart chart) {
		for(Map.Entry<String, Double> entry : data.entrySet()) {
			chart.getData().add( new PieChart.Data(entry.getKey(), entry.getValue()));
		}
	}

}