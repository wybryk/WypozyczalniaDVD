package pl.bazadanych.tables;

import java.io.Serializable;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Gatunek extends BaseTable implements Serializable {

    private int id;
    private String nazwa;

    public Gatunek(){}

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

    @Override
    public String toString() {
        return "Gatunek{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
