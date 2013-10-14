package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) throws IOException {	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
		Parent root = (Parent) loader.load();
		Controller controller = (Controller)loader.getController();
		controller.setStage(primaryStage);
		Scene scene = new Scene(root);

		primaryStage.setTitle("Chart-Demo");

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();		

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
