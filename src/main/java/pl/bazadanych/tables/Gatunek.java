package pl.bazadanych.tables;

/**
 * <h2>Klasa przechowująca dane gatunku filmowego</h2>
 * <p>Zawiera pola z tabeli GATUNEK.</p>
 */
public class Gatunek {

    private int id;
    private String nazwa;

    public Gatunek(){}
    /**
     * Metoda zwracająca wartość pola id
     * @return int id gatunku
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator gatunku
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda zwracająca wartość pola nazwa
     * @return String nazwa gatunku.
     */
    public String getNazwa() {
        return nazwa;
    }
    /**
     * Metoda ustawiająca wartość pola nazwa
     * @param nazwa String nazwa gatunku.
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

}
