package application.view;

import application.MainApp;
import application.User;
import application.database.DatabaseManager;
import application.model.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EditCustomerDetailsController extends AbstractController {

    @FXML private TextField custIDField;
    @FXML private TextField custNameField;
    @FXML private TextField addressField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField dobField;

    @FXML private CheckBox frequentCustomerBox;

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn custIDColumn;
    private ObservableList<Customer> data = FXCollections.observableArrayList();


    @FXML
    private void initialize(){
        setCustomerDataForTable();
        custIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerID"));
        customerTable.setItems(data);
        if(!User.getInstance().getIsManager()){
            frequentCustomerBox.setVisible(false);
        }

        showCustomerDetails(null);

        customerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showCustomerDetails(newValue);
                    fillFields();
                });
    }

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }


    /**
     * Sets edit customer details data for table to populate
     */
    private void setCustomerDataForTable () {
        String sql;
        if (User.getInstance().getIsManager()) {
            sql = "select cust_id from customers";
        } else {
            sql = "select cust_id from customers where cust_id = " +  "'" + User.getInstance().getGlobalID() + "'";
        }
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String custID = rs.getString(1);

                Customer c = new Customer(custID);
                data.add(c); }
        } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection Failed! Check output console");
            }
        DatabaseManager.closeStatement();
    }

    private void fillFields () {
        String queryCustID = customerTable.getSelectionModel().getSelectedItem().getCustomerID();
        String sql = "select * from customers where cust_id = " + "'" + queryCustID + "'";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String custID = rs.getString(1);
                String custName = rs.getString(2);
                String address = rs.getString(3);
                String dob = rs.getString(4);
                String phoneNumber = rs.getString(5);
                String email = rs.getString(6);

                Customer c = new Customer(custID, custName, address, email, phoneNumber, dob);

                custIDField.setText(custID);
                custNameField.setText(custName);
                addressField.setText(address);
                emailField.setText(email);
                phoneNumberField.setText(phoneNumber);
                dobField.setText(dob);
                System.out.println(custName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
        }
        DatabaseManager.closeStatement();
    }

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            // Fill the labels with info from the person object.
            custIDField.setText(customer.getCustomerID());

        } else {
            // Creation is null, remove all the text.
            custIDField.setText("");

        }
    }

    @FXML
    private void handleUpdate(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Update Customer Details");
        alert.setContentText("Are you sure you want to update these details?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (isValidInput()){
                String updateName = "'" + custNameField.getText() +"'";
                String updateAddress = "'" + addressField.getText() + "'";
                String updateEmail = "'" + emailField.getText()+ "'";
                String updatePhoneNumber = "'" + phoneNumberField.getText() +"'";
                String updateDOB = "'" + dobField.getText() + "'";
                String sql = "update customers set " +"cust_name = " + updateName + ", "+ "address = " + updateAddress + ", "+ "email = " + updateEmail + ", "
                       +"phone = " +updatePhoneNumber+ ", "+ "birth = " + updateDOB +" where cust_id = " +"'"+ custIDField.getText() +"'";
                int rowCount = DatabaseManager.sendUpdate(sql);
                System.out.println(rowCount);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Dialog");
                successAlert.setHeaderText("Upate Customer Details");
                successAlert.setContentText("Upadate successful!");

                successAlert.showAndWait();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    private Boolean isValidInput(){
        String errorMessage = "";

        if (custNameField.getText() == null || custNameField.getText().length() == 0) {
            errorMessage += "Not a valid first name!\n";
        }
        //if (addressField.getText() == null || addressField.getText().length() == 0) {
        //    errorMessage += "No valid last name!\n";
        //}
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Not a valid e-mail address!\n";
        }
        //if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) {
        //    errorMessage += "No valid street!\n";
        //}
        if (dobField.getText() == null || dobField.getText().length() == 0 ) {
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
}
