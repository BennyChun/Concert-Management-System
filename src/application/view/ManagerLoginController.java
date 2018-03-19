package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomPasswordField;

public class ManagerLoginController extends AbstractController {


    @FXML private TextField managerNumber;
    @FXML private CustomPasswordField password;


    @FXML
    private void handleLogin(){
        System.out.println(managerNumber.getText() + " " + password.getText() );
        if(password.getText().equals("pw")){
            _mainApp.initOptionSelect(managerNumber.getText());
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incorrect Password");
            alert.setContentText("Please try again with the correct password!");
            alert.showAndWait();
        }


    }

    @FXML
    private void handleBack(){
        _mainApp.initStartMenu();
    }
}
