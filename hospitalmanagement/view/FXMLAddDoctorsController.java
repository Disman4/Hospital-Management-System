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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanaka
 */
public class FXMLAddDoctorsController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void AddDoctors() {
        String doctorid = doctorsidtextfield.getText();
        String docname = doctorsnametextfield.getText();
        String dob = doctorsdobtextfield.getText();
        String phonenumeber = doctorstelephonenumbertextfield.getText();
        String email = doctorsemailaddresstextfield.getText();
        String address = doctorsaddresstextfield.getText();

        if (doctorid.isEmpty() || docname.isEmpty() || dob.isEmpty() || phonenumeber.isEmpty() || email.isEmpty() || address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("FILL IN ALL FIELDS");
            alert.showAndWait();
            return;
        }
        
        if(isinedit){
            handleEditOperation();
            return;
        }

        String qw = "INSERT INTO DOCTORS VALUES ("
                + "'" + doctorid + "',"
                + "'" + docname + "',"
                + "'" + dob + "',"
                + "'" + phonenumeber + "',"
                + "'" + email + "',"
                + "'" + address + "'"
                + ")";
        System.out.println(qw);
        if (databasehandle.execAction(qw)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Doctor Has been Added!");
            alert.showAndWait();
        } else//Error
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed to add Doctor!");
            alert.showAndWait();
        }
    }

    @FXML
    public TextField doctorsidtextfield;

    @FXML
    public TextField doctorsnametextfield;

    @FXML
    public TextField doctorsdobtextfield;

    @FXML
    public TextField doctorstelephonenumbertextfield;

    @FXML
    public TextField doctorsemailaddresstextfield;

    @FXML
    public TextField doctorsaddresstextfield;

    DataBaseHandler databasehandle;
    private Boolean isinedit = Boolean.FALSE;

    public void ShowDatafromForm(Doctor doc) {//fills the text fields with information from the database
        doctorsidtextfield.setText(doc.getId());
        doctorsnametextfield.setText(doc.getName());
        doctorsdobtextfield.setText(doc.getDob());
        doctorstelephonenumbertextfield.setText(doc.getContact());
        doctorsemailaddresstextfield.setText(doc.getEmail());
        doctorsaddresstextfield.setText(doc.getHomeaddress());
        doctorsidtextfield.setEditable(false);//we shouldn't be able to edit this field
        isinedit = Boolean.TRUE;
    }

    @FXML
    public void backbtn(){
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databasehandle = DataBaseHandler.getInstance();
    }
    
    private void handleEditOperation() {
       Doctor doc = new Doctor(doctorsidtextfield.getText(), doctorsnametextfield.getText(), doctorsdobtextfield.getText(), doctorstelephonenumbertextfield.getText(), doctorsemailaddresstextfield.getText(), doctorsaddresstextfield.getText());
       if(databasehandle.updateDoc(doc)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Doctor Updated!");
            alert.showAndWait();
    }
    }

}
