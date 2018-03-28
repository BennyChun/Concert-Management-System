package application.view;

import application.database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javax.xml.crypto.Data;
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
    @FXML private TextField ticketIDField;
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
                id = id + "," + city;
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

    private int trueOrFalseChecker(String yesOrNo){
        if(yesOrNo.equals("Yes")){
            return 1;
        } else if(yesOrNo.equals("No")){
            return 0;
        }
        return 0;
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

    @FXML
    private void handleConcertDelete(){


        String concertIDgiven = "'" + concertIDField.getText() + "'";


        // check if concert field not empty   // check is there is a concert with concert id given

        if (concertIDField.getText().length() == 0){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Missing Information");
            errorAlert.setContentText("You need to input a Concert ID!");

            errorAlert.showAndWait();
            return;
        }

        if (!(concertIDField.getText().length() == 5)){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Wrong Concert ID");
            errorAlert.setContentText(concertIDgiven + " is not a correct concert ID.");

            errorAlert.showAndWait();
            return;
        }

        if (!checkIfConcertExists(concertIDgiven)){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Can't delete Concert");
            errorAlert.setContentText("There is not Concert with this concert ID: " + concertIDgiven);

            errorAlert.showAndWait();
        }



        if (checkIfConcertExists(concertIDgiven)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Concert");
            alert.setContentText("Are you sure you want to delete the Concert with is ID: " + concertIDgiven + " ?");


            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                String sql = "delete from concert where conc_id =" + concertIDgiven;
                int rowCount = DatabaseManager.sendUpdate(sql);
                System.out.println(rowCount);
                System.out.println(concertIDgiven);


                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Dialog");
                successAlert.setHeaderText("Updated Concert Details");
                successAlert.setContentText("Concert with ID: " + concertIDgiven +  " was deleted successfully!");

                successAlert.showAndWait();
            }
            else {
                // ... user chose CANCEL or closed the dialog
            }

        }
    }

    @FXML
    private void handleConcertUpdate(){
        String concertIDgiven ="";
        if (!concertIDField.getText().isEmpty())
            concertIDgiven = "'" + concertIDField.getText() + "'";
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Concert ID is not valid");
            alert.setContentText("Please enter a valid concert ID. Concert ID cannot be blank");
            alert.showAndWait();
            return;

        }


        if (checkIfConcertExists(concertIDgiven) == true) { // update/modify customer
            if(isValidConcert()) {
                Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                updateAlert.setTitle("Confirmation Dialog");
                updateAlert.setHeaderText("Updating Concert Details");
                updateAlert.setContentText("Are you sure the details are correct?");
                Optional<ButtonType> result = updateAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String updateConcertName = "'" + concertNameField.getText()+ "'";
                    int updateDuration = Integer.parseInt(durationField.getText());
                    String startDate = startDateField.getValue().toString();
                    startDate = startDate.replaceAll("-", "/");
                    String updateStartDate = "'" + startDate + "'";
                    int is19Plus = trueOrFalseChecker(is19PlusField.getValue());
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
                    String updateConcertName = "'" + concertNameField.getText()+ "'";
                    int updateDuration = Integer.parseInt(durationField.getText());
                    String startDate = startDateField.getValue().toString();
                    startDate = startDate.replaceAll("-", "/");
                    String updateStartDate = "'" + startDate + "'";
                    int is19Plus = trueOrFalseChecker(is19PlusField.getValue());

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

        if (concertIDField.getText() == null ||(concertIDField).getText().length() == 0 || concertIDField.getText().length() != 5) {
            errorMessage += "Not a valid concert ID!\n";
        }

        if (concertNameField.getText() == null || concertNameField.getText().length() == 0) {
            errorMessage += "Not a valid concert name!\n";
        }
        if (durationField.getText() == null || durationField.getText().length() == 0 || Integer.parseInt(durationField.getText()) <= 0 ) {
                errorMessage += "Not a valid duration!\n";
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

        String ticketIDgiven = "'" + ticketIDField.getText() + "'";

        // check if concert field not empty   // check is there is a concert with concert id given

        if (ticketIDField.getText().length() == 0){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Missing Information!");
            errorAlert.setContentText(ticketIDgiven + " is not a correct Ticket ID.");

            errorAlert.showAndWait();
            return;
        }

        if (!(ticketIDField.getText().length() == 10)){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Can't delete Ticket");
            errorAlert.setContentText("You did not input a correct Ticket ID!");

            errorAlert.showAndWait();
            return;
        }

        if (!checkIfTicketExists(ticketIDgiven)){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Can't delete Ticket");
            errorAlert.setContentText("There is not ticket with this Ticket ID: " + ticketIDgiven + " !");

            errorAlert.showAndWait();
        }



        if (checkIfTicketExists(ticketIDgiven)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Ticket");
            alert.setContentText("Are you sure you want to delete the ticket with this ID : " + ticketIDgiven+" ?");


            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                String sql = "delete from holdtickets where ticket_id =" + ticketIDgiven;
                int rowCount = DatabaseManager.sendUpdate(sql);
                System.out.println(rowCount);
                System.out.println(ticketIDgiven);


                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Dialog");
                successAlert.setHeaderText("Updated Ticket Details");
                successAlert.setContentText("Ticket with Ticket ID" + ticketIDgiven +  "was deleted successfully!");

                successAlert.showAndWait();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }


    }

    @FXML
    private void handleTicketUpdate(){
        String ticketIDGiven ="";
        if (!ticketIDField.getText().isEmpty())
            ticketIDGiven = "'" + ticketIDField.getText() + "'";
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Ticket ID is not valid");
            alert.setContentText("Please enter a valid ticket ID. Ticket ID cannot be blank");
            alert.showAndWait();
            return;

        }

        if (checkIfTicketExists(ticketIDGiven)) { // update/modify customer
            if(isValidTicket()) {
                Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                updateAlert.setTitle("Confirmation Dialog");
                updateAlert.setHeaderText("Updating Ticket Details");
                updateAlert.setContentText("Are you sure the details are correct?");
                Optional<ButtonType> result = updateAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String updateSeatNumber = "'" + seatNumberField.getText()+ "'";
                    int updateVIP = trueOrFalseChecker(ticketIsVIPField.getValue());
                    int cost = Integer.parseInt(ticketCostField.getText());
                    //parse venue name and city
                    String venueAndCity = ticketVenuePicker.getValue();
                    String updateTicketVenueName = "'" +  venueAndCity.split(",")[0] + "'";
                    String updateTicketCity = "'" + venueAndCity.split(",")[1] + "'";

                    String updateCustomerID = "'" + ticketCustIDField.getText() + "'";

                    String startDate = ticketStartDateField.getValue().toString();
                    startDate = startDate.replaceAll("-", "/");
                    String updateStartDate = "'" + startDate + "'";
                    int updateAvailable = trueOrFalseChecker(isAvailableField.getValue());

                    if (ticketCustIDField.getText().isEmpty()) {
                        String sql = "update holdtickets set " + "seat_num = " + updateSeatNumber + ", " + "start_date = " + updateStartDate + ", "
                                + "vip = " + updateVIP + ", " + "cost = " + cost + ", " + "city = " + updateTicketCity + ", " + "v_name = " + updateTicketVenueName +
                                ", " + "cust_id = " + "NULL" + ", " + "available = " + updateAvailable + " where ticket_id = " + ticketIDGiven;
                        DatabaseManager.sendUpdate(sql);
                    } else{
                        String sql = "update holdtickets set " + "seat_num = " + updateSeatNumber + ", " + "start_date = " + updateStartDate + ", "
                                + "vip = " + updateVIP + ", " + "cost = " + cost + ", " + "city = " + updateTicketCity + ", " + "v_name = " + updateTicketVenueName +
                                ", " + "cust_id = " + updateCustomerID + ", " + "available = " + updateAvailable + " where ticket_id = " + ticketIDGiven;
                        DatabaseManager.sendUpdate(sql);

                    }

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText("Update Ticket Details");
                    successAlert.setContentText(ticketIDField.getText() + " has been updated!");
                    successAlert.showAndWait();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Ticket ID does not exist");
            alert.setContentText("Please enter valid ticket to update!");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleTicketAdd(){

        String ticketIDGiven ="";
        if (!ticketIDField.getText().isEmpty())
            ticketIDGiven = "'" + ticketIDField.getText() + "'";
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Ticket ID is not valid");
            alert.setContentText("Please enter a valid ticket ID. Ticket ID cannot be blank");
            alert.showAndWait();
            return;

        }

        if (!checkIfTicketExists(ticketIDGiven)) {
            if(isValidTicket()) {
                Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                updateAlert.setTitle("Confirmation Dialog");
                updateAlert.setHeaderText("Updating Ticket Details");
                updateAlert.setContentText("Are you sure the details are correct?");
                Optional<ButtonType> result = updateAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String updateSeatNumber = "'" + seatNumberField.getText()+ "'";
                    int updateVIP = trueOrFalseChecker(ticketIsVIPField.getValue());
                    int cost = Integer.parseInt(ticketCostField.getText());
                    //parse venue name and city
                    String venueAndCity = ticketVenuePicker.getValue();
                    String updateTicketVenueName = "'" +  venueAndCity.split(",")[0] + "'";
                    String updateTicketCity = "'" + venueAndCity.split(",")[1] + "'";
                    String updateCustomerID = "'" + ticketCustIDField.getText() + "'";
                    String startDate = ticketStartDateField.getValue().toString();
                    startDate = startDate.replaceAll("-", "/");
                    String updateStartDate = "'" + startDate + "'";
                    int updateAvailable = trueOrFalseChecker(isAvailableField.getValue());
                    String updateConcertID = "'" + concertIDPicker.getValue() + "'";

                    String ticketSQL = "";
                    String sellsSQL = "";
                    if (ticketCustIDField.getText().isEmpty()) {
                        ticketSQL = "insert into holdtickets values " + "(" + ticketIDGiven + ", " + updateSeatNumber + ", " + updateStartDate + ", "
                                + updateVIP + ", " + cost + ", " + updateTicketCity + ", " + updateTicketVenueName + ", " + "NULL" +
                                ", " + updateAvailable + ")";
                        sellsSQL = "insert into sells values " + "(" + updateConcertID + ", " + ticketIDGiven + ")";
                    } else {
                         ticketSQL = "insert into holdtickets values " + "(" + ticketIDGiven + ", " + updateSeatNumber + ", " + updateStartDate + ", "
                                + updateVIP + ", " + cost + ", " + updateTicketCity + ", " + updateTicketVenueName + ", " + updateCustomerID +
                                ", " + updateAvailable + ")";
                         sellsSQL = "insert into sells values " + "(" + updateConcertID + ", " + ticketIDGiven + ")";

                    }
                    System.out.println(ticketSQL);
                    System.out.println(sellsSQL);
                    DatabaseManager.sendUpdate(ticketSQL);
                    DatabaseManager.sendUpdate(sellsSQL);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information Dialog");
                    successAlert.setHeaderText("Update Ticket Details");
                    successAlert.setContentText(ticketIDField.getText() + " has been updated!");
                    successAlert.showAndWait();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } else if(checkIfTicketExists(ticketIDGiven)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Ticket ID already exists");
            alert.setContentText("Please enter a new and valid ticket to update!");

            alert.showAndWait();
        }

    }

    private boolean checkIfTicketExists(String ticketID){
        String sql = "select ticket_id from holdtickets where ticket_id = " + ticketID;
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

    private boolean isValidTicket() {
        String errorMessage = "";

        if (ticketIDField.getText() == null || (ticketIDField).getText().length() == 0 || ticketIDField.getText().length() != 10) {
            errorMessage += "Not a valid ticket ID!\n";
        }
        if (seatNumberField.getText() == null || seatNumberField.getText().length() == 0 || seatNumberField.getText().length() != 3) {
            errorMessage += "Not a valid seat number!\n";
        }
        if (ticketIsVIPField.getValue() == null || ticketIsVIPField.getValue().length() == 0) {
            errorMessage += "Please specify if ticket is for VIPs or not!!\n";
        }
        if (ticketCostField.getText() == null || ticketCostField.getText().length() == 0 || Integer.parseInt(ticketCostField.getText()) <= 0) {
            errorMessage += "Not a valid ticket cost!\n";
        }
        if (ticketVenuePicker.getValue() == null || ticketVenuePicker.getValue().length() == 0) {
            errorMessage += "Not a valid venue!";
        }
        if (!ticketCustIDField.getText().isEmpty()){
            if ((!(checkIfCustomerExists(ticketCustIDField.getText())))) {
                errorMessage += "Customer does not exist!";
            }
        }
        if (ticketStartDateField.getValue() == null || ticketStartDateField.getValue().toString().length() == 0 ) {
            errorMessage += "Not a valid date of birth!\n";
        }
        if (isAvailableField.getValue() == null || isAvailableField.getValue().length() == 0) {
            errorMessage += "Please specify if ticket is available!\n";
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

    private boolean checkIfCustomerExists(String custID){
        String sql = "select cust_id from customers where cust_id = " + custID;
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

//================================================================================================================

    @FXML
    private void handleVenueDelete(){


        String venueNamegiven = "'" + venueNameField.getText() + "'";
        String venueCitygiven = "'" + cityField.getText() + "'";

        // check if concert field not empty   // check is there is a concert with concert id given


        if (venueNameField.getText().length() == 0){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Missing Information");
            errorAlert.setContentText("You need to input a Venue's Name and City.");

            errorAlert.showAndWait();
            return;
        }

        if (cityField.getText().length() == 0){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Missing Information");
            errorAlert.setContentText("You did not input the City the Venue: " + venueNamegiven + " is in.");

            errorAlert.showAndWait();
            return;
        }

        if (!checkIfVenueExists(venueNamegiven, venueCitygiven)){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Can't delete Venue");
            errorAlert.setContentText("There is no such Venue: " + venueNamegiven + " in " + venueCitygiven +" !");

            errorAlert.showAndWait();
        }



        if (checkIfVenueExists(venueNamegiven, venueCitygiven)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Venue");
            alert.setContentText("Are you sure you want to delete this venue: " + venueNamegiven + " in " + venueCitygiven +" ?");


            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                String sql = "delete from venue where v_name =" + venueNamegiven;
                int rowCount = DatabaseManager.sendUpdate(sql);
                System.out.println(rowCount);
                System.out.println(venueNamegiven);


                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Dialog");
                successAlert.setHeaderText("Updated Venue Details");
                successAlert.setContentText("Venue was deleted successfully!");

                successAlert.showAndWait();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    private void handleVenueUpdate(){

        String venueNameGiven ="";
        String venueCityGiven ="";
        if (!venueNameField.getText().isEmpty() || cityField.getText().isEmpty()) {
            venueNameGiven = "'" + venueNameField.getText() + "'";
            venueCityGiven = "'" + cityField.getText() + "'";
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Venue name or city is not valid");
            alert.setContentText("Please enter a valid venue name or city cannot be blank");
            alert.showAndWait();
            return;

        }


        if (checkIfVenueExists(venueNameGiven, venueCityGiven)) { // update/modify customer
            if(isValidVenue()) {
                Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                updateAlert.setTitle("Confirmation Dialog");
                updateAlert.setHeaderText("Updating Venue Details");
                updateAlert.setContentText("Are you sure your details are correct?");
                Optional<ButtonType> result = updateAlert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String updateCapacity = "'" + capacityField.getText()+ "'";
                    String updateAddress = "'" + streetAddressField.getText()+ "'";
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
                    String updateCapacity = "'" + capacityField.getText()+ "'";
                    String updateAddress = "'" + streetAddressField.getText()+ "'";
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

        String BandNamegiven = "'" + stageNameField.getText() + "'";

        // check if concert field not empty   // check is there is a concert with concert id given


        if (stageNameField.getText().length() == 0){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Missing Information");
            errorAlert.setContentText("You did not input a band name.");

            errorAlert.showAndWait();
            return;
        }

        if (!checkIfBandExists(BandNamegiven)){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Wrong Band Name");
            errorAlert.setContentText("There is no band with this band name: " + BandNamegiven + " !");

            errorAlert.showAndWait();
        }



        if (checkIfBandExists(BandNamegiven)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Band");
            alert.setContentText("Are you sure you want to delete this band: " + BandNamegiven + " ?");


            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                String sql = "delete from band where stage_name =" + BandNamegiven;
                int rowCount = DatabaseManager.sendUpdate(sql);
                System.out.println(rowCount);
                System.out.println(BandNamegiven);


                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Dialog");
                successAlert.setHeaderText("Updated Band Details");
                successAlert.setContentText("Band was deleted successfully!");

                successAlert.showAndWait();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }


    @FXML
    private void handleBandUpdate(){

    }

    private boolean checkIfBandExists(String BandName){

        String sql = "select stage_name from band where stage_name = " + BandName;
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

//================================================================================================================

    @FXML
    private void handleArtistDelete(){


        String ArtistNamegiven = "'" + artistNameField.getText() + "'";
        String StageNamegiven = "'" + artistStageNamePicker.getValue() + "'";


        if (artistNameField.getText().length() == 0){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Missing Information");
            errorAlert.setContentText("You did not input an artist name.");

            errorAlert.showAndWait();
            return;
        }

        if (artistStageNamePicker.getValue().length() == 0){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Missing Information");
            errorAlert.setContentText("You need to input" + artistNameField.getText()+ "'s stage name.");

            errorAlert.showAndWait();
            return;
        }

        if (!checkIfArtistExists(ArtistNamegiven, StageNamegiven)){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error Dialog");
            errorAlert.setHeaderText("Wrong Artist Name or Stage Name");
            errorAlert.setContentText("There is no artist with the following artist name and stage name: " + ArtistNamegiven+ " " + StageNamegiven + " !");

            errorAlert.showAndWait();
        }



        if (checkIfArtistExists(ArtistNamegiven, StageNamegiven)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Artist");
            alert.setContentText("Are you sure you want to delete this artist: " + ArtistNamegiven + ",  " + StageNamegiven + " ?");


            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                String sql = "delete from artist_partof where a_name =" + ArtistNamegiven;
                int rowCount = DatabaseManager.sendUpdate(sql);
                System.out.println(rowCount);
                System.out.println(ArtistNamegiven);


                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Dialog");
                successAlert.setHeaderText("Updated Artist Details");
                successAlert.setContentText("Artist: " + ArtistNamegiven + " with Stage Name: " + StageNamegiven + " was deleted successfully!");

                successAlert.showAndWait();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    private void handleArtistUpdate(){

    }


    private boolean checkIfArtistExists(String ArtistName, String StageName){

        String sql = "select a_name from artist_partof where a_name = " + ArtistName + "and stage_name = " + StageName;
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

//================================================================================================================













    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
