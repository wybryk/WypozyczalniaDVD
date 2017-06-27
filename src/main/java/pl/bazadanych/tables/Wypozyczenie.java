package pl.bazadanych.tables;

import java.io.Serializable;
import java.util.Date;

/**
 * <h2>Klasa przechowująca dane wypożyczenia danego filmu.</h2>
 * <p>Zawiera pola z tabeli WYPOZYCZENIA.</p>
 */
public class Wypozyczenie extends BaseTable implements Serializable {
    private int id;
    private Date dataWypozyczenia;
    private Date dataOddania;

    public Wypozyczenie() {}
    /**
     * Metoda zwracająca wartość pola id
     * @return int id wypożyczenia
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator wypożyczenia
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda zwracająca wartość pola dataWypozyczenia
     * @return Data data wypożyczenia
     */
    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }
    /**
     * Metoda ustawiająca wartość pola dataWypozyczenia
     * @param dataWypozyczenia Data data wypożyczenia
     */
    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }
    /**
     * Metoda zwracająca wartość pola dataOddania
     * @return Data data oddania
     */
    public Date getDataOddania() {
        return dataOddania;
    }
    /**
     * Metoda ustawiająca wartość pola dataOddania
     * @param dataOddania Data data oddania
     */
    public void setDataOddania(Date dataOddania) {
        this.dataOddania = dataOddania;
    }

    @Override
    public String toString() {
        return "Wypozyczenie{" +
                "id=" + id +
                ", dataWypozyczenia=" + dataWypozyczenia +
                ", dataOddania=" + dataOddania +
                '}';
    }
}
