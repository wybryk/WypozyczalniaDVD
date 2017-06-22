package pl.bazadanych.tables;

/**
 * <h2>Klasa przechowująca dane klienta wypozyczalni.</h2>
 * <p>Zawiera pola z tabeli KLIENT.</p>
 */
public class Klient {

    private int id;
    private String imie;
    private String nazwisko;
    private String email;

    public Klient(){}
    /**
     * Metoda zwracająca wartość pola id
     * @return int id klienta
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator klienta
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda zwracająca wartość pola imie
     * @return String imie klienta
     */
    public String getImie() {
        return imie;
    }
    /**
     * Metoda ustawiająca wartość pola imie
     * @param imie String imie klienta
     */
    public void setImie(String imie) {
        this.imie = imie;
    }
    /**
     * Metoda zwracająca wartość pola nazwisko
     * @return String nazwisko klienta
     */
    public String getNazwisko() {
        return nazwisko;
    }
    /**
     * Metoda ustawiająca wartość pola nazwisko
     * @param nazwisko String nazwisko klienta
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    /**
     * Metoda zwracająca wartość pola email
     * @return String email klienta
     */
    public String getEmail() {
        return email;
    }
    /**
     * Metoda ustawiająca wartość pola email
     * @param email String email klienta
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
