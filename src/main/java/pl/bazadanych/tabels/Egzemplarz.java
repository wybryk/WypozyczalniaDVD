package pl.bazadanych.tabels;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 2017-04-20.
 */
@DatabaseTable(tableName = "EGZEMPLARZ")
public class Egzemplarz implements BaseTabel{
    @DatabaseField(generatedId = true, columnName = "ID_EGZEMLARZU")
    private int id;
    @DatabaseField(columnName = "ID_FILMU", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Film film;
    @ForeignCollectionField
    private ForeignCollection<DaneWypozyczenia> daneWypozyczenia;
    public Egzemplarz() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public ForeignCollection<DaneWypozyczenia> getDaneWypozyczenia() {
        return daneWypozyczenia;
    }

    public void setDaneWypozyczenia(ForeignCollection<DaneWypozyczenia> daneWypozyczenia) {
        this.daneWypozyczenia = daneWypozyczenia;
    }
}
