package application.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ticket {
    private final SimpleStringProperty concertName;
    private final SimpleStringProperty ticketID;
    private final SimpleStringProperty seatNum;
    private final SimpleIntegerProperty cost;
    private final SimpleStringProperty isVIP;
    private final SimpleStringProperty venueName;
    private final SimpleStringProperty date;

    // concertName is from a join with sells and concert
    public Ticket(String concertName, String ticketID, String seatNum, int cost, String isVIP, String venueName, String date) {
        this.concertName = new SimpleStringProperty(concertName);
        this.ticketID = new SimpleStringProperty(ticketID);
        this.seatNum = new SimpleStringProperty(seatNum);
        this.cost = new SimpleIntegerProperty(cost);
        this.isVIP = new SimpleStringProperty(isVIP);
        this.venueName = new SimpleStringProperty(venueName);
        this.date = new SimpleStringProperty(date);
    }

    public String getConcertName() {
        return concertName.get();
    }

    public SimpleStringProperty concertNameProperty() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName.set(concertName);
    }

    public String getTicketID() {
        return ticketID.get();
    }

    public SimpleStringProperty ticketIDProperty() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID.set(ticketID);
    }

    public String getSeatNum() {
        return seatNum.get();
    }

    public SimpleStringProperty seatNumProperty() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum.set(seatNum);
    }

    public int getCost() {
        return cost.get();
    }

    public SimpleIntegerProperty costProperty() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }

    public String getIsVIP() {
        return isVIP.get();
    }

    public SimpleStringProperty isVIPProperty() {
        return isVIP;
    }

    public void setIsVIP(String isVIP) {
        this.isVIP.set(isVIP);
    }

    public String getVenueName() {
        return venueName.get();
    }

    public SimpleStringProperty venueNameProperty() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName.set(venueName);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

}
