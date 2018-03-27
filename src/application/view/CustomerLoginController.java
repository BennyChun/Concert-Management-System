package application.view;

import application.User;
import application.database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
        User.getInstance().initManager(false);
        if (!isNewCust) {
            if(isValidUsername()) {
                _mainApp.initOptionSelect(customerNumberField.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Not a valid customer ID");
                alert.setContentText("Please try again with a valid customer ID or select register!");
                alert.showAndWait();
            }
        } else if(isNewCust){
            // TODO : Check fields and insert new tuple SQL
            // need to store these details into SQL DB
            String customerID = generateCustID();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Update Customer Details");
            alert.setContentText("Are you sure your details are correct?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                if (isValidInput()){
                    //getting variable values
                    String newName = "'" + nameField.getText() +"'";
                    String newAddress = "'" + addressField.getText() + "'";
                    String newEmail = "'" + emailField.getText()+ "'";
                    String newPhoneNumber = "'" + phoneNumberField.getText() +"'";

                    //replace dob - with / to fit with our schema
                    String dob = dobField.getValue().toString();
                    dob = dob.replaceAll("-","/");
                    String newDOB = "'" + dob + "'";

                    //SQL query
                    String sql = "insert into customers values " + "('" + customerID + "', " + newName + ", " + newAddress + ", "
                                    + newDOB + ", " + newPhoneNumber + ", " + newEmail + ")";
                    DatabaseManager.sendUpdate(sql);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText("Upate Customer Details");
                    successAlert.setContentText("Register successful, here is your new customer ID: " + customerID);
                    successAlert.showAndWait();
                    _mainApp.initOptionSelect(customerID);
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }


        }
    }

    private String generateCustID() {
        String custID = "";
        for(int i=0; i < 6; i++) {
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

    private Boolean isValidInput(){
        String errorMessage = "";

        if (nameField.getText() == null ||(nameField).getText().length() == 0) {
            errorMessage += "Not a valid name!\n";
        }
        if (addressField.getText() != null || addressField.getText().length() != 0) {
            if(addressField.getText().length() > 40){
                errorMessage += "Not a valid address!\n";
            }
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Not a valid e-mail address!\n";
        }
        System.out.println(phoneNumberField.getText().length());
        if(phoneNumberField.getText() != null || phoneNumberField.getText().length() != 0) {
            if (phoneNumberField.getText().length() != 12) {
                errorMessage += "Not a valid phone number!\n";
            }
        }
        //TODO regex check on DOB
        String dob = dobField.getValue().toString();
        dob = dob.replaceAll("-", "/");
        if (dob == null || dob.length() == 0 ) {
            errorMessage += "Not a valid date of birth!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
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

    private boolean isValidUsername () {
        String sql = "select cust_id from customers where cust_id = " + "'" + customerNumberField.getText() + "'";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseManager.closeStatement();
        return false;
    }
}
