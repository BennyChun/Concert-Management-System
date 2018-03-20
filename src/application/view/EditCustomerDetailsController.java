package application.view;

import javafx.fxml.FXML;

public class EditCustomerDetailsController extends AbstractController {

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }

}
