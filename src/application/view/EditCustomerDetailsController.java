package application.view;

import application.database.DatabaseManager;
import application.model.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditCustomerDetailsController extends AbstractController {

    @FXML private TextField custIDField;
    @FXML private TextField custNameField;
    @FXML private TextField addressField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField dobField;

    @FXML private TableView<String> customerTable;
    @FXML private TableColumn<String, String> custIDColumn;

    private StringProperty custIDProperty;

    @FXML
    private void initialize(){
        custIDColumn.setCellValueFactory(cellData -> cellData.getValue().getCustID());
        getCustID();
        customerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showCustomerID(newValue);
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

    private List<String> getCustIDQuery() {
        String sql = "SELECT CUSTOMERID FROM CUSTOMER";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        List<String> custIDArray = new ArrayList<>();
        try {
            while (rs.next()) {
                String custID = rs.getString(1);
                custIDArray.add(custID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseManager.closeStatement();
        return custIDArray;
    }

    public StringProperty getCustID(){
        List<String> idArray = getCustIDQuery();

        SimpleStringProperty x = new SimpleStringProperty(idArray.get(0));
        return x;

    }
}
