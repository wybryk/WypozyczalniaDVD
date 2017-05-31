package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Damian on 2017-05-25.
 */
public class RezerwacjaFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty filmFx = new SimpleIntegerProperty();
    private IntegerProperty klientFx = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getFilmFx() {
        return filmFx.get();
    }

    public void setFilmFx(int filmFx) {
        this.filmFx.set(filmFx);
    }

    public int getKlientFx() {
        return klientFx.get();
    }

    public void setKlientFx(int klientFx) {
        this.klientFx.set(klientFx);
    }

    @Override
    public String toString() {
        return "ID: " + id.getValue() + "; FILM ID: " + filmFx.getValue() + "; KLIENT ID: " + klientFx.getValue() + ";";
    }
}
