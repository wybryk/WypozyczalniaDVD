package pl.bazadanych.tables;

import java.io.Serializable;

/**
 * <h2>Klasa przechowująca dane egzemplarza filmu.</h2>
 * <p>Zawiera pola z tabeli EGZEMPLARZ.</p>
 */
public class Egzemplarz extends BaseTable implements Serializable {
    private int id;
    private int idFilmu;

    public Egzemplarz() {}
    /**
     * Metoda zwracająca wartość pola idd
     * @return int id egzemplarza
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator egzemplarza
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda zwracająca wartość pola idFilmu
     * @return int id filmu
     */
    public int getIdFilmu() {
        return idFilmu;
    }
    /**
     * Metoda ustawiająca wartość pola idFilmu
     * @param idFilmu int identyfikator filmu
     */
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
