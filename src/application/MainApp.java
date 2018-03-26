package application;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import application.database.DatabaseManager;
import application.view.StartMenuController;
import application.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

        private Stage _primaryStage;
        private AnchorPane _startMenu;
        public Boolean isManager = true;
        public String globalID;

        @Override
        public void start(Stage primaryStage) {
                _primaryStage = primaryStage;
                _primaryStage.setTitle("Concert Manager");
                initStartMenu();
        }

        public void initStartMenu(){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/StartMenu.fxml"));
                        _startMenu = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_startMenu);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);
                        _primaryStage.sizeToScene();

                        //give the start menu controller access to the MainApp
                        StartMenuController controller = loader.getController();
                        controller.setMainApp(this);
                }catch(IOException e){
                        e.printStackTrace();
                }
        }

        public void initManagerLogin(){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/ManagerLogin.fxml"));
                        AnchorPane _playSelect = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_playSelect);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);


                        //give stage select controller access to the main app
                        ManagerLoginController controller = loader.getController();
                        controller.setMainApp(this);//this will set the main app scene to the stage select scene
                }catch(IOException e){
                        e.printStackTrace();
                }
        }

        public void initCustomerLogin(){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/CustomerLogin.fxml"));
                        AnchorPane _playSelect = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_playSelect);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);


                        //give stage select controller access to the main app
                        CustomerLoginController controller = loader.getController();
                        controller.setMainApp(this);//this will set the main app scene to the stage select scene
                }catch(IOException e){
                        e.printStackTrace();
                }
        }

        public void initOptionSelect(String uniqueIdentifier){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/OptionSelect.fxml"));
                        AnchorPane _playSelect = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_playSelect);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);


                        //give stage select controller access to the main app
                        OptionSelectController controller = loader.getController();
                        controller.setMainApp(this);//this will set the main app scene to the stage select scene
                        controller.setUniqueIdentifier(uniqueIdentifier);
                        User.getInstance().initGlobalID(uniqueIdentifier);
                        globalID = User.getInstance().getGlobalID();
                }catch(IOException e){
                        e.printStackTrace();
                }
        }

        public void initEditCustomerDetails(){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/EditCustomerDetails.fxml"));
                        AnchorPane _playSelect = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_playSelect);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);

                        //give stage select controller access to the main app
                        EditCustomerDetailsController controller = loader.getController();
                        controller.setMainApp(this); //this will set the main app scene to the stage select scene
                }catch(IOException e){
                        e.printStackTrace();
                }
        }

        public void initReserveTickets(){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/ReserveTickets.fxml"));
                        AnchorPane _playSelect = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_playSelect);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);

                        //give stage select controller access to the main app
                        ReserveTicketsController controller = loader.getController();
                        controller.setMainApp(this);//this will set the main app scene to the stage select scene
                }catch(IOException e){
                        e.printStackTrace();
                }
        }

        public void initSearch(){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/SearchScreen.fxml"));
                        AnchorPane _playSelect = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_playSelect);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);

                        //give stage select controller access to the main app
                        SearchScreenController controller = loader.getController();
                        controller.setMainApp(this);//this will set the main app scene to the stage select scene
                }catch(IOException e){
                        e.printStackTrace();
                }
        }

//        public void initAdvancedSearch(){
//                try {
//                        //load start menu from fxml file.
//                        FXMLLoader loader = new FXMLLoader();
//                        loader.setLocation(MainApp.class.getResource("view/AdvancedSearchScreen.fxml"));
//                        AnchorPane _playSelect = (AnchorPane) loader.load();
//
//                        // Show the scene containing the start menu
//                        Scene scene = new Scene(_playSelect);
//                        _primaryStage.setScene(scene);
//                        _primaryStage.show();
//                        _primaryStage.setResizable(false);
//
//                        //give stage select controller access to the main app
//                        AdvancedSearchScreenController controller = loader.getController();
//                        controller.setMainApp(this);//this will set the main app scene to the stage select scene
//                }catch(IOException e){
//                        e.printStackTrace();
//                }
//        }

        public void initAddData(){
                try {
                        //load start menu from fxml file.
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("view/AddDataScreen.fxml"));
                        AnchorPane _playSelect = (AnchorPane) loader.load();

                        // Show the scene containing the start menu
                        Scene scene = new Scene(_playSelect);
                        _primaryStage.setScene(scene);
                        _primaryStage.show();
                        _primaryStage.setResizable(false);

                        //give stage select controller access to the main app
                        AddDataScreenController controller = loader.getController();
                        controller.setMainApp(this);//this will set the main app scene to the stage select scene
                }catch(IOException e){
                        e.printStackTrace();
                }
        }
        /**
         * when this method gets called
         * it closes the primaryStage
         */
        public void close() {
                _primaryStage.close();
        }

        public static void main(String[] args) {
            DatabaseManager.initializeConnection();
             launch(args);
        }




}
