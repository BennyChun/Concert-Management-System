package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class SearchScreenController extends AbstractController{

    @FXML private TableView concertTable;
    @FXML private TableView bandTable;
    @FXML private TableView artistTable;
    @FXML private TableView venueTable;


    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
