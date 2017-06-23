package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * <h2>Klasa przechowująca dane wypozyczenia</h2>
 * <p>Zawiera pola z tabeli DANE_WYPOZYCZENIA.</p>
 */
public class DaneWypozyczeniaFx {

    private IntegerProperty idEgzemplarzu = new SimpleIntegerProperty();
    private IntegerProperty idKlienta = new SimpleIntegerProperty();
    private IntegerProperty idWypozyczenia = new SimpleIntegerProperty();
    /**
     * Metoda zwracająca wartość pola idEgzemplarzu
     * @return int id egzemplarza
     */
    public int getIdEgzemplarzu() {
        return idEgzemplarzu.get();
    }

    public IntegerProperty idEgzemplarzuProperty() {
        return idEgzemplarzu;
    }
    /**
     * Metoda ustawiająca wartość pola idEgzemplarzu
     * @param idEgzemplarzu int identyfikator egzemlarza.
     */
    public void setIdEgzemplarzu(int idEgzemplarzu) {
        this.idEgzemplarzu.set(idEgzemplarzu);
    }
    /**
     * Metoda zwracająca wartość pola idKlienta
     * @return int id klienta
     */
    public int getIdKlienta() {
        return idKlienta.get();
    }

    public IntegerProperty idKlientaProperty() {
        return idKlienta;
    }
    /**
     * Metoda ustawiająca wartość pola idKlienta
     * @param idKlienta int identyfikator klienta
     */
    public void setIdKlienta(int idKlienta) {
        this.idKlienta.set(idKlienta);
    }
    /**
     * Metoda zwracająca wartość pola idWypozyczenia
     * @return int id wypożyczenia
     */
    public int getIdWypozyczenia() {
        return idWypozyczenia.get();
    }

    public IntegerProperty idWypozyczeniaProperty() {
        return idWypozyczenia;
    }
    /**
     * Metoda ustawiająca wartość pola idWypozyczenia
     * @param idWypozyczenia int identyfikator wypozyczenia
     */
    public void setIdWypozyczenia(int idWypozyczenia) {
        this.idWypozyczenia.set(idWypozyczenia);
    }
}
