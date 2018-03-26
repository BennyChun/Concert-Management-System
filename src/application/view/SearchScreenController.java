package application.view;

import application.User;
import application.database.DatabaseManager;
import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchScreenController extends AbstractController{

//==========================================================================================
    @FXML private TableView<Concert> concertTable;
    @FXML private TableColumn concertNameColumn;
    @FXML private TableColumn concertIDColumn;
    @FXML private TableColumn durationColumn;
    @FXML private TableColumn startDateColumn;
    @FXML private TableColumn is19PlusColumn;

//==========================================================================================
    @FXML private TableView<Band> bandTable;
    @FXML private TableColumn stageNameColumn;
    @FXML private TableColumn genreColumn;
    @FXML private TableColumn formationDateColumn;

//==========================================================================================
    @FXML private TableView<Artist> artistTable;
    @FXML private TableColumn artistNameColumn;
    @FXML private TableColumn dateOfBirthColumn;
    @FXML private TableColumn originColumn;
    @FXML private TableColumn artistStageNameColumn;

//==========================================================================================
    @FXML private TableView<Venue> venueTable;
    @FXML private TableColumn venueNameColumn;
    @FXML private TableColumn capacityColumn;
    @FXML private TableColumn streetAddresssColumn;
    @FXML private TableColumn cityColumn;

    private ObservableList<Concert> con_data = FXCollections.observableArrayList();
    private ObservableList<Venue> ven_data = FXCollections.observableArrayList();
    private ObservableList<Band> band_data = FXCollections.observableArrayList();
    private ObservableList<Artist> artist_data = FXCollections.observableArrayList();

    @FXML
    private void handleConcert(){
        concertTable.setVisible(true);
        bandTable.setVisible(false);
        artistTable.setVisible(false);
        venueTable.setVisible(false);
        setConcertTable();
        concertNameColumn.setCellValueFactory(new PropertyValueFactory<Concert, String>("name"));
        concertIDColumn.setCellValueFactory(new PropertyValueFactory<Concert, String>("concertID"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<Concert, Integer>("duration"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Concert, String>("date"));
        is19PlusColumn.setCellValueFactory(new PropertyValueFactory<Concert, String>("isAdultShow"));
        concertTable.setItems(con_data);
    }

    @FXML
    private void handleVenue(){
        venueTable.setVisible(true);
        concertTable.setVisible(false);
        bandTable.setVisible(false);
        artistTable.setVisible(false);
        setVenueTable();
        venueNameColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("venueName"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("city"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Venue, Integer>("capacity"));
        streetAddresssColumn.setCellValueFactory(new PropertyValueFactory<Venue, String>("streetAddr"));
        venueTable.setItems(ven_data);

    }

    @FXML
    private void handleBand(){
        bandTable.setVisible(true);
        concertTable.setVisible(false);
        artistTable.setVisible(false);
        venueTable.setVisible(false);
        setBandTable();
        stageNameColumn.setCellValueFactory(new PropertyValueFactory<Band, String>("stageName"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Band, String>("genre"));
        formationDateColumn.setCellValueFactory(new PropertyValueFactory<Band, String>("formDate"));
        bandTable.setItems(band_data);
    }

    @FXML
    private void handleArtist(){
        artistTable.setVisible(true);
        concertTable.setVisible(false);
        bandTable.setVisible(false);
        venueTable.setVisible(false);
        setArtistTable();
        artistNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("aName"));
        originColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("country"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("birth"));
        artistStageNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("sName"));
        artistTable.setItems(artist_data);
    }

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }

    /**
     * Sets concert data for table to display
     */
    private void setConcertTable () {

        con_data.clear();
        String sql = "select * from concert";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String cid = rs.getString(1);
                String cname = rs.getString(2);
                int dur = rs.getInt(3);
                String date = rs.getString(4);
                int adults_only = rs.getInt(5);
                String adults;

                if (adults_only == 1) {
                    adults = "Yes";
                } else {
                    adults = "No";
                }
                Concert c = new Concert(cid, cname, dur, date, adults);
                con_data.add(c);
                }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
        }
        DatabaseManager.closeStatement();
    }

    /**
     * Sets venue data for table to display
     */
    private void setVenueTable () {

        ven_data.clear();
        String sql = "select * from venue";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String name = rs.getString(1);
                String city = rs.getString(2);
                int cap = rs.getInt(3);
                String addr = rs.getString(4);

                Venue v = new Venue(name, city, cap, addr);
                ven_data.add(v);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
        }
        DatabaseManager.closeStatement();
    }

    /**
     * Sets band data for table to display
     */
    private void setBandTable () {
        band_data.clear();
        String sql = "select * from band";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String sname = rs.getString(1);
                String genre = rs.getString(2);
                String formDate = rs.getString(3);

                Band b = new Band(sname, genre, formDate);
                band_data.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
        }
        DatabaseManager.closeStatement();
    }

    /**
     * Sets band data for table to display
     */
    private void setArtistTable () {
        artist_data.clear();
        String sql = "select * from artist_partof";
        ResultSet rs = DatabaseManager.sendQuery(sql);
        try {
            while (rs.next()) {
                String aname = rs.getString(1);
                String country = rs.getString(2);
                String birth = rs.getString(3);
                String sname = rs.getString(4);
                Artist a = new Artist(aname, country, birth, sname);
                artist_data.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
        }
        DatabaseManager.closeStatement();
    }




}
