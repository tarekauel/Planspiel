package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PopController {

	@FXML
	private StackedBarChart<String, Double>	popupChart;
	@FXML
	private CategoryAxis					xAxisPopup;
	@FXML
	private NumberAxis						yAxisPopup;

	private Stage							dialogStage;

	@FXML
	private void initialize() {
		ArrayList<HashMap<String, Double>> data = new ArrayList<HashMap<String, Double>>();

		for (int i = 1; i < 6; i++) {
			// Jede HashMap enhaelt die Daten einer Runde
			HashMap<String, Double> series = new HashMap<String, Double>();
			series.put("Q1", i * 10.0);
			series.put("Q2", i * 10.0);
			series.put("Q3", i * 10.0);
			series.put("Q4", i * 10.0);
			series.put("Q5", i * 10.0);
			data.add(series);
		}

		buildXYChart(data, new String[] { "Runde 1", "Runde 2", "Runde 3", "Runde 4", "Runde 5" }, xAxisPopup,
				yAxisPopup, popupChart);
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void close() {
		this.dialogStage.close();
	}

	public static <T> void buildXYChart(ArrayList<HashMap<String, T>> data, String[] categories, CategoryAxis xAxisBar,
			NumberAxis yAxisBar, XYChart<String, T> barChart) {

		HashMap<String, XYChart.Series<String, T>> seriesMaps = new HashMap<String, XYChart.Series<String, T>>();

		for (int i = 0; i < data.size(); i++) {
			HashMap<String, T> roundMap = data.get(i);
			for (Map.Entry<String, T> entry : roundMap.entrySet()) {
				XYChart.Series<String, T> roundSeries = seriesMaps.get(entry.getKey());
				if (roundSeries == null) {
					XYChart.Series<String, T> newSerie = new XYChart.Series<>();
					newSerie.setName(entry.getKey());
					newSerie.getData().add(new XYChart.Data<String, T>(categories[i], entry.getValue()));
					seriesMaps.put(entry.getKey(), newSerie);
					barChart.getData().add(newSerie);
				} else {
					roundSeries.getData().add(new XYChart.Data<String, T>(categories[i], entry.getValue()));
				}
			}
		}

		xAxisBar.setCategories(FXCollections.observableArrayList(categories));
	}

}
