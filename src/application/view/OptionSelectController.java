package application.view;


import application.User;
import application.database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

import java.util.Optional;

public class OptionSelectController extends AbstractController {

    @FXML private Text managerOrCustomer;
    @FXML private Text uniqueIdentifier;

    @FXML
    private void handleLogout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging Out...");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            DatabaseManager.closeConnection();
            _mainApp.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void handleEditCustDetails(){
        _mainApp.initEditCustomerDetails();
    }

    @FXML
    private void handleReserveTicket(){
        _mainApp.initReserveTickets();
    }

    @FXML
    private void handleSearch(){
        _mainApp.initSearch();
    }

//    @FXML
//    private void handleAdvancedSearch(){
//        _mainApp.initAdvancedSearch();
//    }

    @FXML
    private void handleAddData() {
        if (User.getInstance().getIsManager()) {
            _mainApp.initAddData();
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Access Denied");
            alert.setContentText("Customers cannot add data!");
            alert.showAndWait();
        }
    }
    public void setUniqueIdentifier(String uniqueID){
        uniqueIdentifier.setText(uniqueID);
    }

}
