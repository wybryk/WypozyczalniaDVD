package pl.bazadanych.tables;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Wypozyczenie extends BaseTable implements Serializable {
    private int id;
    private Date dataWypozyczenia;
    private Date dataOddania;

    public Wypozyczenie() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Date getDataOddania() {
        return dataOddania;
    }

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
