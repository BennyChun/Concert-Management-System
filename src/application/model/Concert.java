package application.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Concert {

    private final SimpleStringProperty concertID;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty duration;
    private final SimpleStringProperty date;
    private final SimpleStringProperty isAdultShow;

    public Concert (String concertID, String name, int duration, String date, String isAdultShow) {
        this.concertID = new SimpleStringProperty(concertID);
        this.name = new SimpleStringProperty(name);
        this.duration = new SimpleIntegerProperty(duration);
        this.date = new SimpleStringProperty(date);
        this.isAdultShow = new SimpleStringProperty(isAdultShow);
    }

    public String getConcertID() {
        return concertID.get();
    }

    public SimpleStringProperty concertIDProperty() {
        return concertID;
    }

    public void setConcertID(String concertID) {
        this.concertID.set(concertID);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
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

    public String getIsAdultShow() {
        return isAdultShow.get();
    }

    public SimpleStringProperty isAdultShowProperty() {
        return isAdultShow;
    }

    public void setIsAdultShow(String isAdultShow) {
        this.isAdultShow.set(isAdultShow);
    }
}
