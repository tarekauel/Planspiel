package Client.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Diese Klasse erzeugt das Game-UI aus der geladenen FXML-Datei und gibt ab an den vernküpften Controller.
 * @author Lars Trey
 *
 */
public class ClientGameUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
    	Parent root = FXMLLoader.load(getClass().getResource("ClientGameUI.fxml"));
		
		Scene scene = new Scene(root);

		stage.setTitle("Planspiel - Client - Game");
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
