package application.view;

import application.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EditCustomerDetailsController extends AbstractController {

    @FXML private TextField custIDField;
    @FXML private TextField custNameField;
    @FXML private TextField addressField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField dobField;

    @FXML private TableView<String> customerTable;
    @FXML private TableColumn<String, String> custIDColumn;

    @FXML
    private void initialize(){
        customerTable.getSelectionModel().selectedItemProperty().addListener()(
                (observable, oldValue, newValue) -> {
                    showCustomerID(newValue)
                });
    }

    private void showCustomerID(String custID){
        if(custID != null){
            custIDField.setText();
            custNameField.setText();
            addressField.setText();
            emailField.setText();
            phoneNumberField.setText();
            dobField.setText();
        }
    }

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }

}
