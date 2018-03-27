package application.view;

import application.User;
import application.database.DatabaseManager;
import application.model.Artist;
import application.model.Customer;
import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @FXML private ComboBox<String> groupByOption;

    @FXML private TableView<Ticket> ticketTable;
    @FXML private TableColumn concertNameColumn;
    @FXML private TableColumn ticketIDColumn;
    @FXML private TableColumn seatColumn;
    @FXML private TableColumn priceColumn;
    @FXML private TableColumn isVIPColumn;
    @FXML private TableColumn venueNameColumn;
    @FXML private TableColumn dateColumn;

    private ObservableList<Ticket> data = FXCollections.observableArrayList();


    @FXML
    private void initialize(){
        isVIP.setItems(FXCollections.observableArrayList(
                "Yes",
                "No"
        ));
        priceInequality.setItems(FXCollections.observableArrayList(
                "=",
                ">",
                "<",
                ">=",
                "<=",
                "Highest",
                "Lowest"
        ));
        dateInequality.setItems(FXCollections.observableArrayList(
                "=",
                ">",
                "<",
                ">=",
                "<="
        ));
        groupByOption.setItems(FXCollections.observableArrayList(
                "Venue Name",
                "Concert Name",
                "Venue City"
        ));
        setTicketData();
        concertNameColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("concertName"));
        ticketIDColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("ticketID"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("seatNum"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Ticket, Integer>("cost"));
        isVIPColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("isVIP"));
        venueNameColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("venueName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Ticket, String>("date"));
        ticketTable.setItems(data);
        ticketTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    fillTicketFields(newValue);
                });
    }

    @FXML
    private void handleReserve(){
        String reserveTicket = "UPDATE HOLDTICKETS SET CUST_ID = " + "'" + User.getInstance().getGlobalID() + "'," + " AVAILABLE = 0 WHERE TICKET_ID = '" + ticketIDField.getText() + "'";
        System.out.println(reserveTicket);
        DatabaseManager.sendUpdate(reserveTicket);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Reserve Ticket");
        alert.setContentText("Are you sure you want to reserve this ticket?");
        alert.showAndWait();

        Alert finishAlert = new Alert(Alert.AlertType.INFORMATION);
        finishAlert.setTitle("Confirmation Dialog");
        finishAlert.setHeaderText("Reserve Ticket");
        finishAlert.setContentText("Congratulatons. You are going to the event! Your ticket id is : " + ticketIDField.getText());
        finishAlert.showAndWait();
    }

    @FXML
    private void handleSearch(){

    }

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }

    private void setTicketData() {
        String sql =  "SELECT C.CONC_NAME, H.TICKET_ID, H.SEAT_NUM, H.COST, H.VIP, H.V_NAME, H.START_DATE FROM HOLDTICKETS H, SELLS S, CONCERT C WHERE S.CONC_ID = C.CONC_ID AND S.TICKET_ID = H.TICKET_ID AND H.AVAILABLE = 1";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String name = rs.getString(1);
                String tid = rs.getString(2);
                String seat = rs.getString(3);
                int cost = rs.getInt(4);
                int vip = rs.getInt(5);
                String isVIP;
                if (vip == 1) {
                    isVIP = "Yes";
                } else {
                    isVIP = "No";
                }
                String venueName = rs.getString(6);
                String date = rs.getString(7);
                Ticket t = new Ticket(name, tid, seat, cost, isVIP, venueName, date);
                data.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
        }
        DatabaseManager.closeStatement();
    }

    private void fillTicketFields(Ticket temp) {
        concertNameField.setText(temp.getConcertName());
        ticketIDField.setText(temp.getTicketID());
        seatField.setText(temp.getSeatNum());
        priceField.setText(Integer.toString(temp.getCost()));
        isVIP.setValue(temp.getIsVIP());
        venueField.setText(temp.getVenueName());
        String stringDate = temp.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        LocalDate localDate = LocalDate.parse(stringDate, formatter);
        dateField.setValue(localDate);

    }
}
