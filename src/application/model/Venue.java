package application.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Venue {
    private final SimpleStringProperty venueName;
    private final SimpleStringProperty city;
    private final SimpleIntegerProperty capacity;
    private final SimpleStringProperty streetAddr;

    public Venue (String name, String city, int capacity, String addr) {
        this.venueName = new SimpleStringProperty(name);
        this.city = new SimpleStringProperty(city);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.streetAddr = new SimpleStringProperty(addr);
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

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public String getStreetAddr() {
        return streetAddr.get();
    }

    public SimpleStringProperty streetAddrProperty() {
        return streetAddr;
    }

    public void setStreetAddr(String streetAddr) {
        this.streetAddr.set(streetAddr);
    }
}
