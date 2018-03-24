package application.view;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class AddDataScreenController extends AbstractController {

    @FXML private GridPane customerGrid;
    @FXML private GridPane concertGrid;
    @FXML private GridPane venueGrid;
    @FXML private GridPane ticketGrid;
    @FXML private GridPane bandGrid;
    @FXML private GridPane artistGrid;

    @FXML
    private void handleBack(){
        _mainApp.initOptionSelect(_mainApp.globalID);
    }
}
