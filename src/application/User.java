package application;

public class User {

    //create an object of SingleObject
    private static User instance;
    private boolean isManager;
    private String globalID;

    //make the constructor private so that this class cannot be
    //instantiated
    private User(){
    }

    //Get the only object available
    public static User getInstance(){
        if (instance == null) {
            instance = new User();
            return instance;
        } else {
            return instance;
        }
    }

    public void initManager (boolean isManager) {
        this.isManager = isManager;
    }

    public void initGlobalID (String globalID) {
        this.globalID = globalID;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public String getGlobalID() {
        return globalID;
    }




}
