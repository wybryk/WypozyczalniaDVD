package pl.bazadanych.tables;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 2017-04-20.
 */
@DatabaseTable(tableName = "REZYSER")
public class Rezyser implements BaseTable {
    @DatabaseField(generatedId = true, columnName = "ID_REZYSERA")
    private int id;
    @DatabaseField(columnName = "NAZWA")
    private String nazwa;
    @ForeignCollectionField
    private ForeignCollection<Film> film;

    public Rezyser() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {return nazwa;}

    public void setNazwa(String nazwa) {this.nazwa = nazwa;}

    public ForeignCollection<Film> getFilm() {
        return film;
    }

    public void setFilm(ForeignCollection<Film> film) {
        this.film = film;
    }
}
