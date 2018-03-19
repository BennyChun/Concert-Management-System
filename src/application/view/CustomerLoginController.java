package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Random;

public class CustomerLoginController extends AbstractController {


    @FXML private TextField customerNumberField;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField emailField;
    @FXML private DatePicker dobField;

    @FXML private Text customerNumberLabel;
    @FXML private Text nameLabel;
    @FXML private Text addressLabel;
    @FXML private Text phoneNumberLabel;
    @FXML private Text emailLabel;
    @FXML private Text dobLabel;

    private Boolean isNewCust = false;

    @FXML
    private void handleContinue(){
        if (!isNewCust) {
            System.out.println(customerNumberField.getText());
            _mainApp.initOptionSelect(customerNumberField.getText());
        } else if(isNewCust){
            // need to store these details into SQL DB
            System.out.println(nameField.getText());
            System.out.println(addressField.getText());
            System.out.println(phoneNumberField.getText());
            System.out.println(emailLabel.getText());
            System.out.println(dobField.getValue());
            String customerID = generateCustID();
            _mainApp.initOptionSelect(customerID);

        }
    }

    private String generateCustID() {
        String custID = "";
        for(int i=0; i < 10; i++) {
           Random rnd = new Random();
           custID = custID + rnd.nextInt(9);
        }
        return custID;
    }

    @FXML
    private void handleRegister(){
        isNewCust = true;
        // hide cust number
        customerNumberField.setVisible(false);
        customerNumberLabel.setVisible(false);
        setRegisterFields();

    }

    @FXML
    private void handleBack(){
        _mainApp.initStartMenu();
    }

    private void setRegisterFields(){
        nameField.setVisible(true);
        nameLabel.setVisible(true);
        addressField.setVisible(true);
        addressLabel.setVisible(true);
        phoneNumberField.setVisible(true);
        phoneNumberLabel.setVisible(true);
        emailField.setVisible(true);
        emailLabel.setVisible(true);
        dobField.setVisible(true);
        dobLabel.setVisible(true);
    }
}
