package application.view;

import javafx.fxml.FXML;

public class AdvancedSearchScreenController extends AbstractController {

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }

}
