package pl.bazadanych.tables;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class DaneWypozyczenia {
    private int idKlienta;
    private int idWypozyczenia;
    private int idEgzemplarzu;

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public int getIdWypozyczenia() {
        return idWypozyczenia;
    }

    public void setIdWypozyczenia(int idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
    }

    public int getIdEgzemplarzu() {
        return idEgzemplarzu;
    }

    public void setIdEgzemplarzu(int idEgzemplarzu) {
        this.idEgzemplarzu = idEgzemplarzu;
    }

    @Override
    public String toString() {
        return "DaneWypozyczenia{" +
                "idKlienta=" + idKlienta +
                ", idWypozyczenia=" + idWypozyczenia +
                ", idEgzemplarzu=" + idEgzemplarzu +
                '}';
    }
}
