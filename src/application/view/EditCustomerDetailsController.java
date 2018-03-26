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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
