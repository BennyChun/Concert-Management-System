package application.model;

import javafx.beans.property.SimpleStringProperty;

public class Band {


    private final SimpleStringProperty stageName;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty formDate;

    public Band(String stageName, String genre, String formDate) {
        this.stageName = new SimpleStringProperty(stageName);
        this.genre = new SimpleStringProperty(genre);
        this.formDate = new SimpleStringProperty(formDate);
    }

    public String getStageName() {
        return stageName.get();
    }

    public SimpleStringProperty stageNameProperty() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName.set(stageName);
    }

    public String getGenre() {
        return genre.get();
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getFormDate() {
        return formDate.get();
    }

    public SimpleStringProperty formDateProperty() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate.set(formDate);
    }


}
