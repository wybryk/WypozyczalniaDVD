package pl.bazadanych.tables;

/**
 * <h2>Klasa przechowująca dane egzemplarza filmu.</h2>
 * <p>Zawiera pola z tabeli EGZEMPLARZ.</p>
 */
public class Egzemplarz {
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
}
