package application.view;

import application.User;
import application.database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomPasswordField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerLoginController extends AbstractController {


    @FXML private TextField managerNumber;
    @FXML private CustomPasswordField password;


    @FXML
    private void handleLogin(){
        System.out.println(managerNumber.getText() + " " + password.getText());
        if (!isValidUsername()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Incorrect Username");
            alert.setContentText("Please try again with a valid manager username!");
            alert.showAndWait();
        } else {
            if (password.getText().equals("pw")) {
                User.getInstance().initManager(true);
                _mainApp.initOptionSelect(managerNumber.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Incorrect Password");
                alert.setContentText("Please try again with the correct password!");
                alert.showAndWait();
            }
        }


    }

    /**
     * Checks if manager's username exist in DB
     * @return true if username exists in database
     */
    private boolean isValidUsername () {
        String sql = "select username from concert_manager where username = " + "'" + managerNumber.getText() + "'";
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

    @FXML
    private void handleBack(){
        _mainApp.initStartMenu();
    }
}
