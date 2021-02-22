/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Tanaka
 */
public class FXMLBottonDController implements Initializable {

    @FXML
    private Label btnLabel;

    @FXML
    public void btnpushed() {
        // btnLabel.setText("buttton pushed");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("button");
        alert.setContentText("Button pushed!");
        alert.showAndWait();
    }

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
