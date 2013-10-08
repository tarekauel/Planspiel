package Client.UI;

/**
 * Created by:
 * User: Lars Trey
 * Date: 08.10.13
 * Time: 16:38
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


/**
 * Dies ist die Controller-Klasse der Login-Stage. Hier wird alles implementiert, was das Login-UI manipulieren soll.
 * @author Lars Trey
 */
public class ClientUILoginController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleLoginSuccessful(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    /**
     * Hier werden alle Felder des UIs initialisiert, die initial beim Aufrufen des UIs gefüllt sein sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}