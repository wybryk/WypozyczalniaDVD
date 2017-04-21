package pl.bazadanych.tabels;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 2017-04-20.
 */
@DatabaseTable(tableName = "REZYSER")
public class Rezyser implements BaseTabel{
    @DatabaseField(generatedId = true, columnName = "ID_REZYSERA")
    private int id;
    @DatabaseField(columnName = "IMIE")
    private String imie;
    @DatabaseField(columnName = "NAZWISKO")
    private String nazwisko;
    @ForeignCollectionField
    private ForeignCollection<Film> film;

    public Rezyser() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public ForeignCollection<Film> getFilm() {
        return film;
    }

    public void setFilm(ForeignCollection<Film> film) {
        this.film = film;
    }
}
