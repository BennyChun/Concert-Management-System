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
    @FXML private ComboBox<String> ticketVenuePicker;
    @FXML private TextField ticketCustIDField;
    @FXML private DatePicker ticketStartDateField;
    @FXML private ComboBox<String> isAvailableField;
    @FXML private ComboBox<String> concertIDPicker;

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
    @FXML private ComboBox<String> artistStageNamePicker;


    @FXML
    private void initialize(){
        ObservableList<String> yesNoOptions = FXCollections.observableArrayList(
                "Yes",
                "No"
        );
        is19PlusField.setItems(yesNoOptions);
        isAvailableField.setItems(yesNoOptions);
        ticketIsVIPField.setItems(yesNoOptions);
        setConcertIDPicker();
        setVenueNamePicker();
        setArtistNamePicker();
    }

    private void setConcertIDPicker() {
        ObservableList<String> concertIDs = FXCollections.observableArrayList();
        String sql = "select conc_id from concert";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String id = rs.getString(1);
                concertIDs.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseManager.closeStatement();
        concertIDPicker.setItems(concertIDs);
    }

    private void setVenueNamePicker() {
        ObservableList<String> venueNames= FXCollections.observableArrayList();
        String sql = "select v_name, city from venue";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String id = rs.getString(1);
                String city = rs.getString(2);
                id = id + ", " + city;
                venueNames.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseManager.closeStatement();
        ticketVenuePicker.setItems(venueNames);
    }

    private void setArtistNamePicker() {
        ObservableList<String> stageNames= FXCollections.observableArrayList();
        String sql = "select a_name from artist_partof";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String id = rs.getString(1);
                stageNames.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseManager.closeStatement();
        artistStageNamePicker.setItems(stageNames);
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

//================================================================================================================

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

    private int trueOrFalseChecker(String yesOrNo){
        if(yesOrNo.equals("Yes")){
            return 1;
        } else if(yesOrNo.equals("No")){
            return 0;
        }
        return 0;
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
            errorAlert.setContentText("There is no concert with this concert id.");

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
    private void handleConcertUpdate(){
        String concertIDgiven = "'" + concertIDField.getText() + "'";
        String updateConcertName = "'" + concertNameField.getText()+ "'";
        int updateDuration = Integer.parseInt(durationField.getText());
        String startDate = startDateField.getValue().toString();
        startDate = startDate.replaceAll("-", "/");
        String updateStartDate = "'" + startDate + "'";
        int is19Plus = trueOrFalseChecker(is19PlusField.getValue());;

        if (checkIfConcertExists(concertIDgiven) == true) { // update/modify customer
            if(isValidConcert()) {
                Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                updateAlert.setTitle("Confirmation Dialog");
                updateAlert.setHeaderText("Updating Concert Details");
                updateAlert.setContentText("Are you sure the details are correct?");
                Optional<ButtonType> result = updateAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String sql = "update concert set " +"conc_id = " + concertIDgiven + ", "+ "conc_name = " + updateConcertName + ", "+ "duration = " + updateDuration + ", "
                            +"startDate = " +updateStartDate+ ", " + "adults_only = "+ is19Plus +" where conc_id = " +concertIDgiven;
                    DatabaseManager.sendUpdate(sql);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText("Update Venue Details");
                    successAlert.setContentText(venueNameField.getText() + " has been updated!");
                    successAlert.showAndWait();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } else if (checkIfConcertExists(concertIDgiven) == false) { // add new customer
            if(isValidConcert()) {
                Alert addAlert = new Alert(Alert.AlertType.CONFIRMATION);
                addAlert.setTitle("Confirmation Dialog");
                addAlert.setHeaderText("Adding Concert Details");
                addAlert.setContentText("Are you sure the details are correct?");
                Optional<ButtonType> result = addAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String sql = "insert into concert values " + "(" + concertIDgiven + ", " + updateConcertName + ", " + updateDuration + ", "
                            + updateStartDate + ", " + is19Plus +  ")";
                    System.out.println(sql);
                    DatabaseManager.sendUpdate(sql);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText("Adding Venue");
                    successAlert.setContentText(venueNameField.getText() + " has been added!");
                    successAlert.showAndWait();

                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        }

    }

    private boolean isValidConcert(){
        String errorMessage = "";

        if (concertIDField.getText() == null ||(concertIDField).getText().length() == 0) {
            if(concertIDField.getText().length() != 5) {
                errorMessage += "Not a valid concert ID!\n";
            }
        }
        if (concertNameField.getText() == null || concertNameField.getText().length() == 0) {
            errorMessage += "Not a concert name!\n";
        }
        if (durationField.getText() != null || durationField.getText().length() != 0) {
            if (Integer.parseInt(durationField.getText()) <= 0 ) {
                errorMessage += "Not a valid duration!\n";
            }
        }
        if(startDateField.getValue() == null || startDateField.getValue().toString().length() == 0) {
            errorMessage += "Not a valid street address!\n";
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


//================================================================================================================



    @FXML
    private void handleTicketDelete(){

    }

    @FXML
    private void handleTicketUpdate(){

    }

    @FXML
    private void handleTicketAdd(){

    }

//================================================================================================================

    @FXML
    private void handleVenueDelete(){

    }

    @FXML
    private void handleVenueUpdate(){
        String venueNameGiven = "'" + venueNameField.getText() + "'";
        String venueCityGiven = "'" + cityField.getText() + "'";
        String updateCapacity = "'" + capacityField.getText()+ "'";
        String updateAddress = "'" + streetAddressField.getText()+ "'";
        if (checkIfVenueExists(venueNameGiven, venueCityGiven)) { // update/modify customer
            if(isValidVenue()) {
                Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                updateAlert.setTitle("Confirmation Dialog");
                updateAlert.setHeaderText("Updating Venue Details");
                updateAlert.setContentText("Are you sure your details are correct?");
                Optional<ButtonType> result = updateAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String sql = "update venue set " +"v_name = " + venueNameGiven + ", "+ "city = " + venueCityGiven + ", "+ "capacity = " + updateCapacity + ", "
                            +"street_addr = " +updateAddress+" where v_name = " +venueNameGiven + " and city = " + venueCityGiven;
                    System.out.println(sql);
                    DatabaseManager.sendUpdate(sql);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText("Update Venue Details");
                    successAlert.setContentText(venueNameField.getText() + " has been updated!");
                    successAlert.showAndWait();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } else if (!checkIfVenueExists(venueNameGiven, venueCityGiven)) { // add new customer
            if(isValidVenue()) {
                Alert addAlert = new Alert(Alert.AlertType.CONFIRMATION);
                addAlert.setTitle("Confirmation Dialog");
                addAlert.setHeaderText("Adding Venue Details");
                addAlert.setContentText("Are you sure your details are correct?");
                Optional<ButtonType> result = addAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //int capacity = Integer.parseInt(capacityField.getText().toString());
                    String sql = "insert into venue values " + "(" + venueNameGiven + ", " + venueCityGiven + ", " + updateCapacity + ", "
                            + updateAddress + ")";
                    System.out.println(sql);
                    DatabaseManager.sendUpdate(sql);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText("Adding Venue");
                    successAlert.setContentText(venueNameField.getText() + " has been added!");
                    successAlert.showAndWait();

                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        }

    }
    private boolean checkIfVenueExists(String venueName, String venueCity){

        String sql = "select v_name, city from venue where v_name = " + venueName +" and city = " + venueCity;
        System.out.println(sql);
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
    private boolean isValidVenue(){
        String errorMessage = "";

        if (venueNameField.getText() == null ||(venueNameField).getText().length() == 0) {
            errorMessage += "Not a valid venue name!\n";
        }
        if (cityField.getText() == null || cityField.getText().length() == 0) {
                errorMessage += "Not a valid city!\n";
        }
        if (capacityField.getText() == null || capacityField.getText().length() == 0) {
                errorMessage += "Not a valid capacity!\n";
        }
        if(streetAddressField.getText() == null || streetAddressField.getText().length() == 0) {
                errorMessage += "Not a valid street address!\n";
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


//================================================================================================================

    @FXML
    private void handleBandDelete(){

    }


    @FXML
    private void handleBandUpdate(){

    }


//================================================================================================================

    @FXML
    private void handleArtistDelete(){

    }

    @FXML
    private void handleArtistUpdate(){

    }

//================================================================================================================













    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
