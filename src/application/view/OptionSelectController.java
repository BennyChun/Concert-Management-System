package application.view;


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
            _mainApp.initStartMenu();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    public void setUniqueIdentifier(String uniqueID){
        uniqueIdentifier.setText(uniqueID);
    }

}
