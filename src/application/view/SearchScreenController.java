package application.view;

import application.model.Concert;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SearchScreenController extends AbstractController{

//==========================================================================================
    @FXML private TableView<Concert> concertTable;
    @FXML private TableColumn concertNameColumn;
    @FXML private TableColumn concertIDColumn;
    @FXML private TableColumn durationColumn;
    @FXML private TableColumn startDateColumn;
    @FXML private TableColumn is19PlusColumn;

//==========================================================================================
    @FXML private TableView bandTable;
    @FXML private TableColumn stageNameColumn;
    @FXML private TableColumn genreColumn;
    @FXML private TableColumn formationDateColumn;

//==========================================================================================
    @FXML private TableView artistTable;
    @FXML private TableColumn artistNameColumn;
    @FXML private TableColumn dateOfBirthColumn;
    @FXML private TableColumn originColumn;
    @FXML private TableColumn artistStageNameColumn;

//==========================================================================================
    @FXML private TableView venueTable;
    @FXML private TableColumn venueNameColumn;
    @FXML private TableColumn capacityColumn;
    @FXML private TableColumn streetAddresssColumn;
    @FXML private TableColumn cityColumn;


    @FXML
    private void handleConcert(){
        concertTable.setVisible(true);
        bandTable.setVisible(false);
        artistTable.setVisible(false);
        venueTable.setVisible(false);
    }

    @FXML
    private void handleVenue(){
        venueTable.setVisible(true);
        concertTable.setVisible(false);
        bandTable.setVisible(false);
        artistTable.setVisible(false);

    }

    @FXML
    private void handleBand(){
        bandTable.setVisible(true);
        concertTable.setVisible(false);
        artistTable.setVisible(false);
        venueTable.setVisible(false);
    }

    @FXML
    private void handleArtist(){
        artistTable.setVisible(true);
        concertTable.setVisible(false);
        bandTable.setVisible(false);
        venueTable.setVisible(false);
    }

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
