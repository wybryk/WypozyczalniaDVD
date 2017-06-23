package pl.tablesFx;

import javafx.beans.property.*;
import pl.bazadanych.tables.Klient;

/**
 * <h2>Klasa przechowująca dane konta w wypożyczalni</h2>
 * <p>Zawiera pola z tabeli KONTO.</p>
 */
public class KontoFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty login = new SimpleStringProperty();
    private StringProperty haslo = new SimpleStringProperty();
    private IntegerProperty admin = new SimpleIntegerProperty();
    private IntegerProperty klientfx = new SimpleIntegerProperty();
    /**
     * Metoda zwracająca wartość pola is
     * @return int id konto
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id identyfikator konto
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola login
     * @return String login klienta
     */
    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }
    /**
     * Metoda ustawiająca wartość pola login
     * @param login String login klienta
     */
    public void setLogin(String login) {
        this.login.set(login);
    }
    /**
     * Metoda zwracająca wartość pola haslo
     * @return String haslo klienta
     */
    public String getHaslo() {
        return haslo.get();
    }

    public StringProperty hasloProperty() {
        return haslo;
    }
    /**
     * Metoda ustawiająca wartość pola haslo
     * @param haslo String haslo klienta
     */
    public void setHaslo(String haslo) {
        this.haslo.set(haslo);
    }
    /**
     * Metoda zwracająca wartość pola admin
     * @return int boolean czy jest adminem
     */
    public int getAdmin() {
        return admin.get();
    }

    public IntegerProperty adminProperty() {
        return admin;
    }
    /**
     * Metoda ustawiająca wartość pola admin
     * @param admin boolean czy jest adminem
     */
    public void setAdmin(int admin) {
        this.admin.set(admin);
    }
    /**
     * Metoda zwracająca wartość pola klient
     * @return int id klienta
     */
    public int getKlientfx() {
        return klientfx.get();
    }

    public IntegerProperty klientfxProperty() {
        return klientfx;
    }
    /**
     * Metoda ustawiająca wartość pola klient
     * @param klientfx int identyfikator klienta
     */
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
