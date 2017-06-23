package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <h2>Klasa przechowująca dane reżysera.</h2>
 * <p>Zawiera pola z tabeli REZYSER.</p>
 */
public class RezyserFx {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    /**
     * Metoda zwracająca wartość pola id
     * @return int id reżysera
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola idKlienta
     * @param id int identyfikator reżysera
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola nazwa
     * @return String nazwa reżysera
     */
    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }
    /**
     * Metoda ustawiająca wartość pola idKlienta
     * @param nazwa String nazwa reżysera
     */
    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }
}
