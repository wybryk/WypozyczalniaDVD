package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Mateusz on 2017-05-25.
 */
public class DaneWypozyczeniaFx {

    private IntegerProperty idEgzemplarzu = new SimpleIntegerProperty();
    private IntegerProperty idKlienta = new SimpleIntegerProperty();
    private IntegerProperty idWypozyczenia = new SimpleIntegerProperty();

    public int getIdEgzemplarzu() {
        return idEgzemplarzu.get();
    }

    public IntegerProperty idEgzemplarzuProperty() {
        return idEgzemplarzu;
    }

    public void setIdEgzemplarzu(int idEgzemplarzu) {
        this.idEgzemplarzu.set(idEgzemplarzu);
    }

    public int getIdKlienta() {
        return idKlienta.get();
    }

    public IntegerProperty idKlientaProperty() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta.set(idKlienta);
    }

    public int getIdWypozyczenia() {
        return idWypozyczenia.get();
    }

    public IntegerProperty idWypozyczeniaProperty() {
        return idWypozyczenia;
    }

    public void setIdWypozyczenia(int idWypozyczenia) {
        this.idWypozyczenia.set(idWypozyczenia);
    }
}
