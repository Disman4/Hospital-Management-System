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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanaka
 */
public class FXMLAddPatientsController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void SaveRecord() {
        String patientid = patientidtextfield.getText();
        String name = nametextfield.getText();
        String dob = datetextfield.getText();
        String gender = gendertextfield.getText();
        String bloodgroup = bloodgrouptextfield.getText();
        String phonenumber = phonenumbertextfield.getText();
        String docid = assigneddoctoridtextfield.getText();
        String medid = medicineidtextfield.getText();

        if (patientid.isEmpty() || name.isEmpty() || dob.isEmpty() || gender.isEmpty() || bloodgroup.isEmpty() || phonenumber.isEmpty() || docid.isEmpty() || medid.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("FILL IN ALL FIELDS");
            alert.showAndWait();
            return;
        }
        if (isinedit) {
            handleEditOperation();
            return;
        }

        String qw = "INSERT INTO PATIENTS VALUES("
                + "'" + patientid + "',"
                + "'" + name + "',"
                + "'" + dob + "',"
                + "'" + gender + "',"
                + "'" + bloodgroup + "',"
                + "'" + phonenumber + "',"
                + "'" + docid + "',"
                + "'" + medid + "'"
                + ")";
        System.out.println(qw);
        if (databasehandle.execAction(qw)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Patient Has been Added!");
            alert.showAndWait();
        } else//Error
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed to add Patient!");
            alert.showAndWait();
        }

    }

    @FXML
    public TextField patientidtextfield;

    @FXML
    public TextField datetextfield;

    @FXML
    public TextField nametextfield;

    @FXML
    public TextField gendertextfield;

    @FXML
    public TextField bloodgrouptextfield;

    @FXML
    public TextField phonenumbertextfield;

    @FXML
    public TextField assigneddoctoridtextfield;

    @FXML
    public TextField medicineidtextfield;  
    
    public void backbtn(){
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    DataBaseHandler databasehandle;

    private Boolean isinedit = Boolean.FALSE;

    public void ShowDatafromForm(Patient pat) {//fills the text fields with information from the database
        patientidtextfield.setText(pat.getId());
        nametextfield.setText(pat.getName());
        datetextfield.setText(pat.getDob());
        gendertextfield.setText(pat.getGender());
        bloodgrouptextfield.setText(pat.getBloodgroup());
        phonenumbertextfield.setText(pat.getPhonenumber());
        assigneddoctoridtextfield.setText(pat.getDocid());
        medicineidtextfield.setText(pat.getMedid());
        medicineidtextfield.setEditable(false);//we shouldn't be able to edit this field
        isinedit = Boolean.TRUE;
    }
    
 
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databasehandle = DataBaseHandler.getInstance();
    }

    private void handleEditOperation() {
        Patient pat = new Patient(patientidtextfield.getText(), nametextfield.getText(), datetextfield.getText(), gendertextfield.getText(), bloodgrouptextfield.getText(), phonenumbertextfield.getText(), assigneddoctoridtextfield.getText(), medicineidtextfield.getText());
        if (databasehandle.updatePat(pat)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Patient Updated!");
            alert.showAndWait();
        }
    }

}
