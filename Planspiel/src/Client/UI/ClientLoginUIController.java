package Client.UI;

/**
 * Created by:
 * User: Lars Trey
 * Date: 08.10.13
 * Time: 16:38
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Dies ist die Controller-Klasse der Login-Stage. Hier wird alles implementiert, was das Login-UI manipulieren soll.
 * @author Lars Trey
 */
public class ClientLoginUIController implements Initializable {

	@FXML private TextField loginNameField;
	@FXML private Button loginButton;
	@FXML private PasswordField passwordField;

    /**
     * Hier werden alle Felder des UIs initialisiert, die initial beim Aufrufen des UIs gefüllt sein sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	
            	changeScene(actionEvent);       	
            	
            }
        });

    }
    
    private void changeScene(ActionEvent actionEvent){
    	
    	Node source = (Node) actionEvent.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("ClientGameUI.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    	
    }

}