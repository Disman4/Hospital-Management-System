/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

import hospitalmanagement.MainMenu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Tanaka
 */
public class FXMLMainMenuController implements Initializable {
    
                    
    public void AddPatient(){
        loadwindow("FXMLAddPatients.fxml", "Add Patient");
    }
    
    @FXML
    public void AddDoctor(){
        loadwindow("FXMLAddDoctors.fxml", "Add Doctor");
    }
    
    @FXML
    public void AddMedicine(){
        loadwindow("FXMLAddMedicine.fxml", "Add Medicine");
    }
    
    @FXML
    public  void MedicineRecords(){
        loadwindow("FXMLMedicineTable.fxml", "Medicine Table");
    }
    
    @FXML
    public void DoctorRecords(){
         loadwindow("FXMLDoctorsTable.fxml", "Doctors Table");
    }
    
    @FXML
    public void PatientsRecords(){
        loadwindow("FXMLPatientsTable.fxml", "Patients Table");
    }
    
    @FXML
    public void buttond(){
        loadwindow("FXMLBottonD.fxml", "Patients Table");
    }
    
    
    
    
    
    @FXML
    public void ShutDown(){
        System.exit(0);
    }
    
    void loadwindow(String location, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
