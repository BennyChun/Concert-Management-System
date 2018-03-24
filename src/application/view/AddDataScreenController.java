package application.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddDataScreenController extends AbstractController {

//====================================================================================================
    @FXML private GridPane customerGrid;
    @FXML private TextField customerNameField;
    @FXML private TextField customerIDField;
    @FXML private TextField addressField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField emailField;
    @FXML private DatePicker dateOfBirthField;

//====================================================================================================
    @FXML private GridPane concertGrid;
    @FXML private TextField concertNameField;
    @FXML private TextField concertIDField;
    @FXML private TextField durationField;
    @FXML private DatePicker startDateField;
    @FXML private ComboBox<String> is19PlusField;
//====================================================================================================
    @FXML private GridPane venueGrid;
    @FXML private TextField venueNameField;
    @FXML private TextField capacityField;
    @FXML private TextField cityField;
    @FXML private TextField streetAddressField;
//====================================================================================================
    @FXML private GridPane ticketGrid;
    @FXML private TextField tickedIDField;
    @FXML private TextField seatNumberField;
    @FXML private ComboBox<String> ticketIsVIPField;
    @FXML private TextField ticketCostField;
    @FXML private TextField ticketVenueField;
    @FXML private TextField ticketCityField;
    @FXML private TextField ticketCustIDField;
    @FXML private DatePicker ticketStartDateField;
    @FXML private ComboBox<String> isAvailableField;

//====================================================================================================
    @FXML private GridPane bandGrid;
    @FXML private TextField stageNameField;
    @FXML private TextField genreField;
    @FXML private DatePicker formationDateField;

//====================================================================================================
    @FXML private GridPane artistGrid;
    @FXML private TextField artistNameField;
    @FXML private TextField originField;
    @FXML private DatePicker artistDateOfBirthField;
    @FXML private TextField artistStageNameField;


    @FXML
    private void initialize(){
        ObservableList<String> yesNoOptions = FXCollections.observableArrayList(
                "Yes",
                "No"
        );
        is19PlusField.setItems(yesNoOptions);
        isAvailableField.setItems(yesNoOptions);
        ticketIsVIPField.setItems(yesNoOptions);
    }

    @FXML
    private void handleCustomer(){
        customerGrid.setVisible(true);
        concertGrid.setVisible(false);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(false);
        artistGrid.setVisible(false);

    }

    @FXML
    private void handleConcert(){
        customerGrid.setVisible(false);
        concertGrid.setVisible(true);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(false);
        artistGrid.setVisible(false);
    }

    @FXML
    private void handleVenue(){
        customerGrid.setVisible(false);
        concertGrid.setVisible(false);
        venueGrid.setVisible(true);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(false);
        artistGrid.setVisible(false);

    }

    @FXML
    private void handleTicket(){
        customerGrid.setVisible(false);
        concertGrid.setVisible(false);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(true);
        bandGrid.setVisible(false);
        artistGrid.setVisible(false);
    }

    @FXML
    private void handleBand(){
        customerGrid.setVisible(false);
        concertGrid.setVisible(false);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(true);
        artistGrid.setVisible(false);

    }

    @FXML
    private void handleArtist(){
        customerGrid.setVisible(false);
        concertGrid.setVisible(false);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(false);
        artistGrid.setVisible(true);
    }


    @FXML
    private void handleCustomerUpdate(){

    }

    @FXML
    private void handleConcertUpdate(){

    }

    @FXML
    private void handleTicketUpdate(){

    }

    @FXML
    private void handleVenueUpdate(){

    }

    @FXML
    private void handleBandUpdate(){

    }

    @FXML
    private void handleArtistUpdate(){

    }


    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
