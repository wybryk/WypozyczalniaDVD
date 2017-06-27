package pl.bazadanych.tables;

import java.io.Serializable;

/**
 * <h2>Klasa przechowująca dane konta w wypożyczalni</h2>
 * <p>Zawiera pola z tabeli KONTO.</p>
 */
public class Konto extends BaseTable implements Serializable{
    private int id;
    private String login;
    private String haslo;
    private int admin;
    private int klient;

    public Konto() {}
    /**
     * Metoda zwracająca wartość pola is
     * @return int id konto
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id identyfikator konto
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda zwracająca wartość pola login
     * @return String login klienta
     */
    public String getLogin() {
        return login;
    }
    /**
     * Metoda ustawiająca wartość pola login
     * @param login String login klienta
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * Metoda zwracająca wartość pola haslo
     * @return String haslo klienta
     */
    public String getHaslo() {
        return haslo;
    }
    /**
     * Metoda ustawiająca wartość pola haslo
     * @param haslo String haslo klienta
     */
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    /**
     * Metoda zwracająca wartość pola admin
     * @return int boolean czy jest adminem
     */
    public int getAdmin() {
        return admin;
    }
    /**
     * Metoda ustawiająca wartość pola admin
     * @param admin boolean czy jest adminem
     */
    public void setAdmin(int admin) {
        this.admin = admin;
    }
    /**
     * Metoda zwracająca wartość pola klient
     * @return int id klienta
     */
    public int getKlient() {
        return klient;
    }
    /**
     * Metoda ustawiająca wartość pola klient
     * @param klient int identyfikator klienta
     */
    public void setKlient(int klient) {
        this.klient = klient;
    }

    @Override
    public String toString() {
        return "Konto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", admin=" + admin +
                ", klient=" + klient +
                '}';
    }
}
