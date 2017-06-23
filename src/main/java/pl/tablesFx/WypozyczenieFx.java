package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;

/**
 * <h2>Klasa przechowująca dane wypożyczenia danego filmu.</h2>
 * <p>Zawiera pola z tabeli WYPOZYCZENIA.</p>
 */
public class WypozyczenieFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<Date> dataWypozyczenia = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dataOddania = new SimpleObjectProperty<>();
    /**
     * Metoda zwracająca wartość pola id
     * @return int id wypożyczenia
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator wypożyczenia
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola dataWypozyczenia
     * @return Data data wypożyczenia
     */
    public Date getDataWypozyczenia() {
        return dataWypozyczenia.get();
    }

    public ObjectProperty<Date> dataWypozyczeniaProperty() {
        return dataWypozyczenia;
    }
    /**
     * Metoda ustawiająca wartość pola dataWypozyczenia
     * @param dataWypozyczenia Data data wypożyczenia
     */
    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia.set(dataWypozyczenia);
    }
    /**
     * Metoda zwracająca wartość pola dataOddania
     * @return Data data oddania
     */
    public Date getDataOddania() {
        return dataOddania.get();
    }

    public ObjectProperty<Date> dataOddaniaProperty() {
        return dataOddania;
    }
    /**
     * Metoda ustawiająca wartość pola dataOddania
     * @param dataOddania Data data oddania
     */
    public void setDataOddania(Date dataOddania) {
        this.dataOddania.set(dataOddania);
    }
}
