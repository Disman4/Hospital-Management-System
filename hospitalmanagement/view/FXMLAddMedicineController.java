/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

import hospitalmanagement.MedicineTable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.security.x509.CertificateSubjectName;

/**
 * FXML Controller class
 *
 * @author Tanaka
 */
public class FXMLAddMedicineController implements Initializable {

    @FXML
    public void SaveMedicine(ActionEvent event) {
        String medicineid = medicineidtextfield.getText();
        String medname = medicinenametextfield.getText();
        String group = medicinegrouptextfield.getText();
        String expirydate = medicineexpirydatetextfield.getText();
        String quantity = medicinequantitytextfield.getText();

        if (medicineid.isEmpty() || medname.isEmpty() || group.isEmpty() || expirydate.isEmpty()|| quantity.isEmpty()) {
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

        String qw = "INSERT INTO MEDICINE VALUES ("
                + "'" + medicineid + "',"
                + "'" + medname + "',"
                + "'" + group + "',"
                + "'" + expirydate + "',"
                + "'" + quantity + "'"
                + ")";
        System.out.println(qw);
        if (databasehandle.execAction(qw)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Medicine Has been Added!");
            alert.showAndWait();
        } else//Error
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed to add Medication!");
            alert.showAndWait();
        }

    }

    @FXML
    public void btncancel() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public TextField medicineidtextfield;

    @FXML
    public TextField medicinenametextfield;

    @FXML
    public TextField medicinegrouptextfield;

    @FXML
    public TextField medicineexpirydatetextfield;

    @FXML
    public TextField medicinequantitytextfield;

    @FXML
    public AnchorPane rootPane;

    DataBaseHandler databasehandle;
    
    private Boolean isinedit = Boolean.FALSE;
    
    public void ShowDatafromForm(Medicine medicine){//fills the text fields with information from the database
        medicineidtextfield.setText(medicine.getId());
        medicinenametextfield.setText(medicine.getName());
        medicinegrouptextfield.setText(medicine.getGroup());
        medicineexpirydatetextfield.setText(medicine.getExpirydate());
        medicinequantitytextfield.setText(medicine.getQuantity());
        medicineidtextfield.setEditable(false);//we shouldn't be able to edit this field
        isinedit = Boolean.TRUE;
    }

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        databasehandle = DataBaseHandler.getInstance();
        // checkdata();
    }

    private void handleEditOperation() {
       Medicine medicine = new Medicine(medicineidtextfield.getText(), medicinenametextfield.getText(), medicinegrouptextfield.getText(), medicineexpirydatetextfield.getText(), medicinequantitytextfield.getText());
       if(databasehandle.updateMed(medicine)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Medicine Updated!");
            alert.showAndWait();
    }
    }
 
}
