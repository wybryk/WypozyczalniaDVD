package pl.tablesFx;

import javafx.beans.property.*;


/**
 * <h2>Klasa przechowująca dane klienta wypozyczalni.</h2>
 * <p>Zawiera pola z tabeli KLIENT.</p>
 */
public class KlientFx  {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    /**
     * Metoda zwracająca wartość pola id
     * @return int id klienta
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator klienta
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola imie
     * @return String imie klienta
     */
    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }
    /**
     * Metoda ustawiająca wartość pola imie
     * @param imie String imie klienta
     */
    public void setImie(String imie) {
        this.imie.set(imie);
    }
    /**
     * Metoda zwracająca wartość pola nazwisko
     * @return String nazwisko klienta
     */
    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }
    /**
     * Metoda ustawiająca wartość pola nazwisko
     * @param nazwisko String nazwisko klienta
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }
    /**
     * Metoda zwracająca wartość pola email
     * @return String email klienta
     */
    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }
    /**
     * Metoda ustawiająca wartość pola email
     * @param email String email klienta
     */
    public void setEmail(String email) {
        this.email.set(email);
    }


    @Override
    public String toString() {
        return  id.getValue() +" "+
                imie.getValue() +" "+
                nazwisko.getValue() +" "+
                email.getValue();
    }

}
