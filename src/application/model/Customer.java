package application.model;

import javafx.beans.property.SimpleStringProperty;

public class Customer {

    private final SimpleStringProperty customerID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty phoneNum;
    private final SimpleStringProperty emailAddress;
    private final SimpleStringProperty homeAddress;
    private final SimpleStringProperty DOB;

    public Customer (String customerID) {
        this.customerID = new SimpleStringProperty(customerID);
        name = null;
        phoneNum = null;
        emailAddress = null;
        homeAddress =null;
        DOB = null;
    }

    public Customer (String custID, String name, String phoneNum, String email, String addr, String DOB) {
        this.customerID = new SimpleStringProperty(custID);
        this.name = new SimpleStringProperty(name);
        this.phoneNum = new SimpleStringProperty(phoneNum);
        this.emailAddress = new SimpleStringProperty(email);
        this.homeAddress = new SimpleStringProperty(addr);
        this.DOB = new SimpleStringProperty(DOB);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getCustomerID() {
        return customerID.get();
    }
    public void setCustomerID(String id) {
        customerID.set(id);
    }

    public SimpleStringProperty customerIDProperty() {
        return customerID;
    }


    public String getPhoneNum() {
        return phoneNum.get();
    }
    public void setPhoneNum(String num) {
        phoneNum.set(num);
    }

    public SimpleStringProperty phoneNumProperty() {
        return phoneNum;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }
    public void setEmailAddress(String email) {
        emailAddress.set(email);
    }

    public SimpleStringProperty emailAddressProperty() {
        return emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress.get();
    }
    public void setHomeAddress(String addr) {
        homeAddress.set(addr);
    }

    public SimpleStringProperty homeAddressProperty() {
        return homeAddress;
    }

    public String getDOB() {
        return emailAddress.get();
    }
    public void setDOB(String birthday) {
        DOB.set(birthday);
    }

    public SimpleStringProperty DOBProperty() {
        return DOB;
    }
}
