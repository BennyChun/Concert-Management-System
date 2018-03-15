package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CustomerLoginController extends AbstractController {


    @FXML private TextField customerNumber;

    @FXML
    private void handleLogin(){
        System.out.println(customerNumber.getText());
    }
}
