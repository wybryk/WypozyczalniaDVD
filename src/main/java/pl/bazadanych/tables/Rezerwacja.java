package pl.bazadanych.tables;

/**
 * Created by Damian on 2017-05-31.
 */
public class Rezerwacja {
    private int id;
    private int idFilmu;
    private int idKlienta;

    public Rezerwacja() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFilmu() {
        return idFilmu;
    }

    public void setIdFilmu(int idFilmu) {
        this.idFilmu = idFilmu;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    @Override
    public String toString() {
        return "Rezerwacja{" +
                "id=" + id +
                ", ID Filmu=" + idFilmu +
                ", ID Klienta=" + idKlienta +
                '}';
    }
}
