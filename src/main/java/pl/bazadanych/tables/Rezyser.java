package pl.bazadanych.tables;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Rezyser {
    private int id;
    private String nazwa;

    public Rezyser() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {return nazwa;}

    public void setNazwa(String nazwa) {this.nazwa = nazwa;}

    @Override
    public String toString() {
        return "Rezyser{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
