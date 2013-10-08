package Client.UI;

/**
 * Created by:
 * User: Lars Trey
 * Date: 08.10.13
 * Time: 16:38
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 *
 * @author Lars Trey
 */
public class ClientUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new ClientUI();
    }

    public ClientUI(){

       try {
           final Parent root = FXMLLoader.load(Executor.class.getResource("Login.fxml"));
           final Stage stage = new Stage(){{
               setScene(new Scene(root, 300, 250));
               setTitle("Planspiel - Client - Login");
               setResizable(false);
               show();
           }};
       } catch (IOException e) {
           e.printStackTrace();
       }

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
