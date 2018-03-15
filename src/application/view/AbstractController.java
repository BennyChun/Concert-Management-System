package application.view;

import application.MainApp;
import javafx.stage.Stage;

public abstract class AbstractController {


    // Reference to the main application.
    protected MainApp _mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        _mainApp = mainApp;
    }

}
