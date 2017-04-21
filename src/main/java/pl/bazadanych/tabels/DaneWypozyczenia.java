package pl.bazadanych.tabels;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 2017-04-20.
 */
@DatabaseTable(tableName = "DANE_WYPOZYCZENIA")
public class DaneWypozyczenia implements BaseTabel{
    @DatabaseField(columnName = "ID_KLIENTA", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Klient klient;
    @DatabaseField(columnName = "ID_WYPOZYCZENIA", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Wypozyczenie wypozyczenie;
    @DatabaseField(columnName = "ID_EGZEMPLARZU", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Egzemplarz egzemplarz;

    public DaneWypozyczenia() {}

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Wypozyczenie getWypozyczenie() {
        return wypozyczenie;
    }

    public void setWypozyczenie(Wypozyczenie wypozyczenie) {
        this.wypozyczenie = wypozyczenie;
    }

    public Egzemplarz getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(Egzemplarz egzemplarz) {
        this.egzemplarz = egzemplarz;
    }
}
