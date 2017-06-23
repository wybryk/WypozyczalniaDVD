package pl.bazadanych.tables;

import java.io.Serializable;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Egzemplarz extends BaseTable implements Serializable {
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

    @Override
    public String toString() {
        return "Egzemplarz{" +
                "id=" + id +
                ", idFilmu=" + idFilmu +
                '}';
    }
}
