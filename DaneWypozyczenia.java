package pl.bazadanych.tables;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class DaneWypozyczenia {
    private int klient;
    private int wypozyczenie;
    private int egzemplarz;

    public DaneWypozyczenia() {}

    public int getKlient() {
        return klient;
    }

    public void setKlient(int klient) {
        this.klient = klient;
    }

    public int getWypozyczenie() {
        return wypozyczenie;
    }

    public void setWypozyczenie(int wypozyczenie) {
        this.wypozyczenie = wypozyczenie;
    }

    public int getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(int egzemplarz) {
        this.egzemplarz = egzemplarz;
    }
}
