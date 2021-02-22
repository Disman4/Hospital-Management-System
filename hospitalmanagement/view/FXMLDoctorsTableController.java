





























/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

import hospitalmanagement.MainMenu;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Tanaka
 */
public class FXMLDoctorsTableController implements Initializable {
    
    ObservableList<Doctor>list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private TableView<Doctor> TableView;
    
    @FXML
    private TableColumn<Doctor, String> idcol;
    
    @FXML
    private TableColumn<Doctor, String> namecol;
    
    @FXML
    private TableColumn<Doctor, String> dobcol;
    
    @FXML
    private TableColumn<Doctor, String> contactcol;
    
    @FXML
    private TableColumn<Doctor, String> emailcol;
    
    @FXML
    private TableColumn<Doctor, String> addresscol;
    
    @FXML
    public  void deletebtn(){
        Doctor selectedForDeletion = TableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("NO RECORD SELECTED");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("delete");
        alert.setContentText("ARE YOU SURE YOU WANT TO DELETE THIS RECORD");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {//it will delete what i have selected
            Boolean result = DataBaseHandler.getInstance().deleteDoc(selectedForDeletion);
            if (result) {
                list.remove(selectedForDeletion);
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("delete");
                alert.setContentText("RECORD WAS NOT DELETED!!");
                alert.showAndWait();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("delete");
            alert.setContentText("YOU CANCELED!!");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    public void editdata(){
        Doctor selectedForEdit = TableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("NO RECORD SELECTED");
            alert.showAndWait();
        }
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddDoctors.fxml"));
            Parent parent = loader.load();
            
            FXMLAddDoctorsController control = (FXMLAddDoctorsController)loader.getController();
            control.ShowDatafromForm(selectedForEdit);
                    
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("edit doctor");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initialiseCol() {
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        contactcol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("homeaddress"));
    }
    
    private void loadData() {//load data from data base into table

        String qu = "SELECT * FROM DOCTORS";
        ResultSet rs = handle.execQuery(qu);
        try {
            while (rs.next()) {
                String docid = rs.getString("docid");
                String name = rs.getString("docname");
                String dob = rs.getString("dateofbirth");
                String contact = rs.getString("phone_number");
                String email = rs.getString("email");
                String homeaddress = rs.getString("home_address");

                list.add(new Doctor(docid, name, dob, contact, email, homeaddress));

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDoctorsTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableView.setItems(list);
    }
    
    @FXML
    public void backbtn(){
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    Doctor d;
    DataBaseHandler handle;
    /**
     * InitialiSes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handle = DataBaseHandler.getInstance();
        loadData();
        initialiseCol();
    }    
    
}
