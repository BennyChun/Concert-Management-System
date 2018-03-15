package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ManagerLoginController extends AbstractController {


    @FXML private TextField managerNumber;
    @FXML private TextField password;

    @FXML
    private void handleLogin(){
        System.out.println(managerNumber.getText() + " " + password.getText() );
    }
}
