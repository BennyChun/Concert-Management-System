package application.model;

public class Customer {

    private String custID;
    private String name;
    private String phoneNum;
    private String emailAddress;
    private String homeAddress;
    private String DOB;

    public Customer (String custID, String name, String phoneNum, String email, String addr, String DOB) {
        this.custID = custID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.emailAddress = email;
        this.homeAddress = addr;
        this.DOB = DOB;
    }

}
