package Client.UI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Diese Klasse erzeugt das Login-UI aus der geladenen FXML-Datei und gibt ab an den vernküpften Controller.
 * @author Lars Trey
 *
 */
public class ClientLoginUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	
    	Parent root = FXMLLoader.load(getClass().getResource("ClientLoginUI.fxml"));
		
		Scene scene = new Scene(root);

		stage.setTitle("Planspiel - Client - Login");
		stage.setScene(scene);		
		stage.show();
		
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
