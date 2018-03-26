package application.model;

import javafx.beans.property.SimpleStringProperty;

public class Artist {

    private final SimpleStringProperty aName;
    private final SimpleStringProperty country;
    private final SimpleStringProperty birth;
    private final SimpleStringProperty sName;

    public Artist(String aName, String country, String birth, String sName) {
        this.aName = new SimpleStringProperty(aName);
        this.country = new SimpleStringProperty(country);
        this.birth = new SimpleStringProperty(birth);
        this.sName = new SimpleStringProperty(sName);
    }


    public String getaName() {
        return aName.get();
    }

    public SimpleStringProperty aNameProperty() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName.set(aName);
    }

    public String getCountry() {
        return country.get();
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getBirth() {
        return birth.get();
    }

    public SimpleStringProperty birthProperty() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth.set(birth);
    }

    public String getsName() {
        return sName.get();
    }

    public SimpleStringProperty sNameProperty() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName.set(sName);
    }


}
