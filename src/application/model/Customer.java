package application.model;

import javafx.beans.property.SimpleStringProperty;

public class Customer {

    private final SimpleStringProperty custID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty phoneNum;
    private final SimpleStringProperty emailAddress;
    private final SimpleStringProperty homeAddress;
    private final SimpleStringProperty DOB;

    public Customer (String custID, String name, String phoneNum, String email, String addr, String DOB) {
        this.custID = new SimpleStringProperty(custID);
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

    public String getCustID() {
        return custID.get();
    }
    public void setCustID(String id) {
        custID.set(id);
    }

    public String getPhoneNum() {
        return phoneNum.get();
    }
    public void setPhoneNum(String num) {
        phoneNum.set(num);
    }

    public String getEmail() {
        return emailAddress.get();
    }
    public void setEmail(String email) {
        emailAddress.set(email);
    }

    public String getAddress() {
        return homeAddress.get();
    }
    public void setAddress(String addr) {
        homeAddress.set(addr);
    }

    public String getDOB() {
        return emailAddress.get();
    }
    public void setDOB(String birthday) {
        DOB.set(birthday);
    }


}
