package pl.tablesFx;

import javafx.beans.property.*;


/**
 * <h2>Klasa przechowująca dane gatunku filmowego</h2>
 * <p>Zawiera pola z tabeli GATUNEK.</p>
 */
public class GatunekFx {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    /**
     * Metoda zwracająca wartość pola id
     * @return int id gatunku
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator gatunku
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola nazwa
     * @return String nazwa gatunku.
     */
    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }
    /**
     * Metoda ustawiająca wartość pola nazwa
     * @param nazwa String nazwa gatunku.
     */
    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    @Override
    public String toString() {
        return  nazwa.getValue();
    }


}
