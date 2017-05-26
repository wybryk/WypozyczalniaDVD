package pl.bazadanych.tables;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Film {

    private int id;
    private String nazwa;
    private String opis;
    private int ilosc;
    private Date premiera;
    private int gatunek;
    private int rezyser;

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

    public int getGatunek() {
        return gatunek;
    }

    public void setGatunek(int gatunek) {
        this.gatunek = gatunek;
    }

    public int getRezyser() {
        return rezyser;
    }

    public void setRezyser(int rezyser) {
        this.rezyser = rezyser;
    }


    @Override
    public String toString() {
        return "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                ", ilosc=" + ilosc +
                ", premiera=" + premiera +
                ", gatunek=" + gatunek +
                ", rezyser=" + rezyser +
                '}';
    }
}
