package application;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class Controller {

	@FXML
	private PieChart					pieChart;

	@FXML
	private LineChart<String, Number>	lineChart;

	@FXML
	private CategoryAxis				xAxisLine;

	@FXML
	private NumberAxis					yAxisLine;

	@FXML
	private StackedBarChart				barChart;

	@FXML
	private CategoryAxis				xAxisBar;
	@FXML
	private NumberAxis					yAxisBar;

	public void initSBC() {
		final String r1 = "Runde 1";
		final String r2 = "Runde 2";
		final String r3 = "Runde 3";
		final String r4 = "Runde 4";
		final String r5 = "Runde 5";
		final XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		final XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
		final XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
		barChart.setTitle("Gewinn nach Qualität");
		xAxisBar.setLabel("");
		xAxisBar.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(r1, r2, r3, r4,
				r5)));
		yAxisBar.setLabel("Value");
		series1.setName("Q1");
		series1.getData().add(new XYChart.Data<String, Number>(r1, 25601.34));
		series1.getData().add(new XYChart.Data<String, Number>(r2, 20148.82));
		series1.getData().add(new XYChart.Data<String, Number>(r3, 10000));
		series1.getData().add(new XYChart.Data<String, Number>(r4, 35407.15));
		series1.getData().add(new XYChart.Data<String, Number>(r5, 12000));
		series2.setName("Q4");
		series2.getData().add(new XYChart.Data<String, Number>(r1, 57401.85));
		series2.getData().add(new XYChart.Data<String, Number>(r2, 41941.19));
		series2.getData().add(new XYChart.Data<String, Number>(r3, 45263.37));
		series2.getData().add(new XYChart.Data<String, Number>(r4, 117320.16));
		series2.getData().add(new XYChart.Data<String, Number>(r5, 14845.27));
		series3.setName("Q8");
		series3.getData().add(new XYChart.Data<String, Number>(r1, 45000.65));
		series3.getData().add(new XYChart.Data<String, Number>(r2, 44835.76));
		series3.getData().add(new XYChart.Data<String, Number>(r3, 18722.18));
		series3.getData().add(new XYChart.Data<String, Number>(r4, 17557.31));
		series3.getData().add(new XYChart.Data<String, Number>(r5, 92633.68));
		barChart.getData().addAll(series1, series2, series3);
	}

	public void initPie() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Grapefruit",
				13), new PieChart.Data("Oranges", 25), new PieChart.Data("Plums", 10), new PieChart.Data("Pears", 22),
				new PieChart.Data("Apples", 30));

		pieChart.setData(pieChartData);
	}

	public void initLine() {
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		series.setName("Barwert");
		xAxisLine.setLabel("Monate");
		yAxisLine.setLabel("Y-Achse");

		series.getData().add(new XYChart.Data<String, Number>("Jan", 2300000));
		series.getData().add(new XYChart.Data<String, Number>("Feb", 1400000));
		series.getData().add(new XYChart.Data<String, Number>("Mar", 1500000));
		series.getData().add(new XYChart.Data<String, Number>("Apr", 2400000));
		series.getData().add(new XYChart.Data<String, Number>("May", 3400000));
		series.getData().add(new XYChart.Data<String, Number>("Jun", 3600000));
		series.getData().add(new XYChart.Data<String, Number>("Jul", 2200000));
		series.getData().add(new XYChart.Data<String, Number>("Aug", 4500000));
		series.getData().add(new XYChart.Data<String, Number>("Sep", 4300000));
		series.getData().add(new XYChart.Data<String, Number>("Oct", 1700000));
		series.getData().add(new XYChart.Data<String, Number>("Nov", 2900000));
		series.getData().add(new XYChart.Data<String, Number>("Dec", 2500000));

		lineChart.getData().add(series);
	}

	public void initialize() {
		initPie();
		initLine();
		initSBC();
	}
}
