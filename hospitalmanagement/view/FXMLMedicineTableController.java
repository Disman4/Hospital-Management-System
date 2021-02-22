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
import java.time.LocalDate;
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
public class FXMLMedicineTableController implements Initializable {

    ObservableList<Medicine> list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Medicine> Tableview;

    @FXML
    private TableColumn<Medicine, String> idcol;

    @FXML
    private TableColumn<Medicine, String> namecol;

    @FXML
    private TableColumn<Medicine, String> groupcol;

    @FXML
    private TableColumn<Medicine, LocalDate> datecol;

    @FXML
    private TableColumn<Medicine, Integer> quantitycol;

    @FXML
    public void backbtn() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void deletebtn() {//delete medicine information
        Medicine selectedForDeletion = Tableview.getSelectionModel().getSelectedItem();
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
            Boolean result = DataBaseHandler.getInstance().deletemed(selectedForDeletion);
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
    public void EditMed(){//edit medcine informantion 
        Medicine selectedForEdit = Tableview.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("NO RECORD SELECTED");
            alert.showAndWait();
        }
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddMedicine.fxml"));
            Parent parent = loader.load();
            
            FXMLAddMedicineController control = (FXMLAddMedicineController) loader.getController();
            control.ShowDatafromForm(selectedForEdit);
                    
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("edit medicine");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialiseCol() {
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        groupcol.setCellValueFactory(new PropertyValueFactory<>("group"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("expirydate"));
        quantitycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadData() {//load data from data base into table

        String qu = "Select * FROM MEDICINE";
        ResultSet rs = handle.execQuery(qu);
        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("medname");
                String group = rs.getString("groupname");
                String expiry = rs.getString("expirydate");
                String quantity = rs.getString("quantity");

                list.add(new Medicine(id, name, group, expiry, quantity));

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMedicineTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tableview.setItems(list);
    }

    

    Medicine m;
    DataBaseHandler handle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handle = DataBaseHandler.getInstance();
        loadData();
        initialiseCol();
    }

}
