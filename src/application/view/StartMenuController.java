package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartMenuController extends AbstractController {

    @FXML private Button managerBtn;

    @FXML
    private void handleManager(){
        System.out.println("hello world");
        _mainApp.initManagerLogin();
    }

}
