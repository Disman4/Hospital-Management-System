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
public class FXMLPatientsTableController implements Initializable {

    ObservableList<Patient> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Patient> TableView;

    @FXML
    private TableColumn<Doctor, String> idcol;

    @FXML
    private TableColumn<Patient, String> namecol;

    @FXML
    private TableColumn<Patient, String> dobcol;

    @FXML
    private TableColumn<Patient, String> gendercol;

    @FXML
    private TableColumn<Patient, String> bloodgroupcol;

    @FXML
    private TableColumn<Patient, String> contactcol;

    @FXML
    private TableColumn<Patient, String> docidcol;

    @FXML
    private TableColumn<Patient, String> medidcol;

    @FXML
    public void deletebtn() {
        Patient selectedForDeletion = TableView.getSelectionModel().getSelectedItem();
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
            Boolean result = DataBaseHandler.getInstance().deletePat(selectedForDeletion);
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
    public void editbtn() {
        Patient selectedForEdit = TableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("NO RECORD SELECTED");
            alert.showAndWait();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddPatients.fxml"));
            Parent parent = loader.load();

            FXMLAddPatientsController control = (FXMLAddPatientsController) loader.getController();
            control.ShowDatafromForm(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("edit Patient");
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
        gendercol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        bloodgroupcol.setCellValueFactory(new PropertyValueFactory<>("bloodgroup"));
        contactcol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        docidcol.setCellValueFactory(new PropertyValueFactory<>("docid"));
        medidcol.setCellValueFactory(new PropertyValueFactory<>("medid"));
    }

    private void loadData() {//load data from data base into table

        String qu = "SELECT * FROM PATIENTS";
        ResultSet rs = handle.execQuery(qu);
        try {
            while (rs.next()) {
                String patid = rs.getString("id");
                String name = rs.getString("name");
                String dob = rs.getString("dateofbirth");
                String gender = rs.getString("gender");
                String bloodgroup = rs.getString("blood_group");
                String phonenumber = rs.getString("phone_number");
                String doctid = rs.getString("docname");
                String mediid = rs.getString("medname");

                list.add(new Patient(patid, name, dob, gender, bloodgroup, phonenumber, doctid, mediid));

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDoctorsTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableView.setItems(list);
    }

    @FXML
    public void backbtn() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    Patient p;
    DataBaseHandler handle;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handle = DataBaseHandler.getInstance();
        loadData();
        initialiseCol();
    }

}
