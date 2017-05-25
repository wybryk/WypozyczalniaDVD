package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;

/**
 * Created by Mateusz on 2017-05-25.
 */
public class WypozyczenieFx {

    private IntegerProperty idEgzemplarzu = new SimpleIntegerProperty();
    private ObjectProperty<Date> dataWypozyczenia = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dataOddania = new SimpleObjectProperty<>();

    public int getIdEgzemplarzu() {
        return idEgzemplarzu.get();
    }

    public IntegerProperty idEgzemplarzuProperty() {
        return idEgzemplarzu;
    }

    public void setIdEgzemplarzu(int idEgzemplarzu) {
        this.idEgzemplarzu.set(idEgzemplarzu);
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia.get();
    }

    public ObjectProperty<Date> dataWypozyczeniaProperty() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia.set(dataWypozyczenia);
    }

    public Date getDataOddania() {
        return dataOddania.get();
    }

    public ObjectProperty<Date> dataOddaniaProperty() {
        return dataOddania;
    }

    public void setDataOddania(Date dataOddania) {
        this.dataOddania.set(dataOddania);
    }
}
