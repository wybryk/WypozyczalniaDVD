package pl.tablesFx;

import javafx.beans.property.*;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class GatunekFx {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    @Override
    public String toString() {
        return  nazwa.getValue();
    }
}
