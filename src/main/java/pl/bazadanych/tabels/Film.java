package pl.bazadanych.tabels;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Mateusz on 2017-04-20.
 */
@DatabaseTable(tableName = "FILM")
public class Film implements BaseTabel{

    @DatabaseField(columnName = "ID_FILMU", generatedId = true)
    private int id;
    @DatabaseField(columnName = "NAZWA")
    private String nazwa;
    @DatabaseField(columnName = "OPIS")
    private String opis;
    @DatabaseField(columnName = "ILOSC")
    private int ilosc;
    @DatabaseField(columnName = "PREMIERA")
    private Date premiera;
    @DatabaseField(columnName = "ID_GATUNKU", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Gatunek gatunek;
    @DatabaseField(columnName = "ID_REZYSERA", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Rezyser rezyser;
    @ForeignCollectionField
    private ForeignCollection<Egzemplarz> egzemplarz;

    public Film() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public Date getPremiera() {
        return premiera;
    }

    public void setPremiera(Date premiera) {
        this.premiera = premiera;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }

    public void setGatunek(Gatunek gatunek) {
        this.gatunek = gatunek;
    }

    public Rezyser getRezyser() {
        return rezyser;
    }

    public void setRezyser(Rezyser rezyser) {
        this.rezyser = rezyser;
    }

    public ForeignCollection<Egzemplarz> getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(ForeignCollection<Egzemplarz> egzemplarz) {
        this.egzemplarz = egzemplarz;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                ", ilosc=" + ilosc +
                ", premiera=" + premiera +
                ", gatunek=" + gatunek +
                '}';
    }
}
