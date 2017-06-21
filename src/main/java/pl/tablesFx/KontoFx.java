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
    private IntegerProperty admin = new SimpleIntegerProperty();
    private IntegerProperty klientfx = new SimpleIntegerProperty();

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

    public int getAdmin() {
        return admin.get();
    }

    public IntegerProperty adminProperty() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin.set(admin);
    }

    public int getKlientfx() {
        return klientfx.get();
    }

    public IntegerProperty klientfxProperty() {
        return klientfx;
    }

    public void setKlientfx(int klientfx) {
        this.klientfx.set(klientfx);
    }

    @Override
    public String toString() {
        return  "ID: " + id.getValue() +
                ", LOGIN: " + login.getValue() +
                ", CZY ADMIN?: " + admin.getValue() +
                ", KLIENT:" + klientfx.getValue() ;
    }
}
