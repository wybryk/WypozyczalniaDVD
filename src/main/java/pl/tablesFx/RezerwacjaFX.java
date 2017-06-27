package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import pl.bazadanych.tables.BaseTable;


public class RezerwacjaFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty filmFx = new SimpleIntegerProperty();
    private IntegerProperty klientFx = new SimpleIntegerProperty();
    /**
     * Metoda zwracająca wartość pola id
     * @return int id rezerwacji
     */
    public int getId() {
        return id.get();
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator rezerwacji
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola idFilmu
     * @return int id filmu
     */
    public int getFilmFx() {
        return filmFx.get();
    }
    /**
     * Metoda ustawiająca wartość pola idFilmu
     * @param filmFx int identyfikator filmu
     */
    public void setFilmFx(int filmFx) {
        this.filmFx.set(filmFx);
    }
    /**
     * Metoda zwracająca wartość pola idKlienta
     * @return int id klienta
     */
    public int getKlientFx() {
        return klientFx.get();
    }
    /**
     * Metoda ustawiająca wartość pola idKlienta
     * @param klientFx int identyfikator klienta
     */
    public void setKlientFx(int klientFx) {
        this.klientFx.set(klientFx);
    }

    @Override
    public String toString() {
        return "ID: " + id.getValue() + "; FILM ID: " + filmFx.getValue() + "; KLIENT ID: " + klientFx.getValue() + ";";
    }
}
