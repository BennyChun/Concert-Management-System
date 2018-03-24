package application.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ReserveTicketsController extends AbstractController {

    @FXML private TextField concertNameField;
    @FXML private TextField ticketIDField;
    @FXML private TextField seatField;
    @FXML private TextField priceField;
    @FXML private TextField venueField;
    @FXML private ComboBox<String> isVIP;
    @FXML private DatePicker dateField;

    @FXML private ComboBox<String> priceInequality;
    @FXML private ComboBox<String> dateInequality;


    @FXML
    private void initialize(){
        isVIP.setItems(FXCollections.observableArrayList(
                "Yes",
                "No"
        ));
        ObservableList<String> inequalities = FXCollections.observableArrayList(
                "=",
                ">",
                "<",
                ">=",
                "<="
        );
        priceInequality.setItems(inequalities);
        dateInequality.setItems(inequalities);

    }

    @FXML
    private void handleReserve(){
        System.out.println(concertNameField.getText());
        System.out.println(ticketIDField.getText());
        System.out.println(seatField.getText());
        System.out.println(priceField.getText());
        System.out.println(venueField.getText());
        System.out.println(isVIP.getEditor().getText());
        System.out.println(dateField.getValue());

        System.out.println(priceInequality.getEditor().getText());
        System.out.println(dateInequality.getEditor().getText());

    }

    @FXML
    private void handleSearch(){

    }

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
