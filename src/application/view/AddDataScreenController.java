package application.view;

import javafx.fxml.FXML;
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
    @FXML private TextField dateOfBirthField;

//====================================================================================================
    @FXML private GridPane concertGrid;
    @FXML private TextField customerNameField;
    @FXML private TextField customerIDField;
    @FXML private TextField addressField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField emailField;
    @FXML private TextField dateOfBirthField;
//====================================================================================================
    @FXML private GridPane venueGrid;

//====================================================================================================
    @FXML private GridPane ticketGrid;
    @FXML private TextField tickedIDField;
    @FXML private TextField seatNumberField;
    @FXML private TextField dateOfBirthField;
    @FXML private TextField customerNameField;
    @FXML private TextField customerIDField;
    @FXML private TextField addressField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField emailField;
    @FXML private TextField dateOfBirthField;

//====================================================================================================
    @FXML private GridPane bandGrid;
    @FXML private TextField stageNameField;
    @FXML private TextField customerIDField;
    @FXML private TextField formationDateField;

//====================================================================================================
    @FXML private GridPane artistGrid;



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
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
