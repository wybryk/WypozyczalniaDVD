package pl.bazadanych.tables;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Egzemplarz {
    private int id;
    private int idFilmu;

    public Egzemplarz() {}

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
}
