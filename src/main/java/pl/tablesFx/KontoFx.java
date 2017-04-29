package pl.tablesFx;

import javafx.beans.property.*;
import pl.bazadanych.tables.Klient;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class KontoFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty login = new SimpleStringProperty();
    private StringProperty haslo = new SimpleStringProperty();
    private BooleanProperty admin = new SimpleBooleanProperty();
    private ObjectProperty<KlientFx> klientfx = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getHaslo() {
        return haslo.get();
    }

    public StringProperty hasloProperty() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo.set(haslo);
    }

    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }

    public KlientFx getKlientfx() {
        return klientfx.get();
    }

    public ObjectProperty<KlientFx> klientfxProperty() {
        return klientfx;
    }

    public void setKlientfx(KlientFx klientfx) {
        this.klientfx.set(klientfx);
    }

    @Override
    public String toString() {
        return  "id=" + id.getValue() +
                ", login=" + login.getValue() +
                ", haslo=" + haslo.getValue() +
                ", admin=" + admin.getValue() +
                '}';
    }
}
