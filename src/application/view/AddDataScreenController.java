package application.view;

import application.database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddDataScreenController extends AbstractController {

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
    private void handleConcert(){
        concertGrid.setVisible(true);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(false);
        artistGrid.setVisible(false);
    }

    @FXML
    private void handleVenue(){
        concertGrid.setVisible(false);
        venueGrid.setVisible(true);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(false);
        artistGrid.setVisible(false);

    }

    @FXML
    private void handleTicket(){
        concertGrid.setVisible(false);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(true);
        bandGrid.setVisible(false);
        artistGrid.setVisible(false);
    }

    @FXML
    private void handleBand(){
        concertGrid.setVisible(false);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(true);
        artistGrid.setVisible(false);

    }

    @FXML
    private void handleArtist(){
        concertGrid.setVisible(false);
        venueGrid.setVisible(false);
        ticketGrid.setVisible(false);
        bandGrid.setVisible(false);
        artistGrid.setVisible(true);
    }


    private boolean checkIfConcertExists(String concertID){

        String sql = "select conc_id from concert where conc_id = " + concertID;
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
    private void handleConcertDelete(){


    String concertIDgiven = "'" + concertIDField.getText() + "'";

    // check if concert field not empty   // check is there is a concert with concert id given


        if (!(concertIDField.getText().length() == 5)){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error Dialog");
        errorAlert.setHeaderText("Can't delete Concert");
        errorAlert.setContentText("You did not input a correct concert id.");

        errorAlert.showAndWait();
    }

        if (!checkIfConcertExists(concertIDgiven)){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error Dialog");
        errorAlert.setHeaderText("Can't delete Concert");
        errorAlert.setContentText("There is not concert with this concert id.");

        errorAlert.showAndWait();
    }



        if (checkIfConcertExists(concertIDgiven)) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Concert");
        alert.setContentText("Are you sure you want to delete this concert?");


        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            String sql = "delete from concert where conc_id =" + concertIDgiven;
            int rowCount = DatabaseManager.sendUpdate(sql);
            System.out.println(rowCount);
            System.out.println(concertIDgiven);


            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Dialog");
            successAlert.setHeaderText("Update Customer Details");
            successAlert.setContentText("Concert was deleted successfully!");

            successAlert.showAndWait();
        }
        else {
            // ... user chose CANCEL or closed the dialog
        }

    }
}

    @FXML
    private void handleTicketDelete(){

    }

    @FXML
    private void handleVenueDelete(){

    }

    @FXML
    private void handleBandDelete(){

    }

    @FXML
    private void handleArtistDelete(){

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
