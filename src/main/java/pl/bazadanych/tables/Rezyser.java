package pl.bazadanych.tables;

/**
 * <h2>Klasa przechowująca dane reżysera.</h2>
 * <p>Zawiera pola z tabeli REZYSER.</p>
 */
public class Rezyser {
    private int id;
    private String nazwa;

    public Rezyser() {}
    /**
     * Metoda zwracająca wartość pola id
     * @return int id reżysera
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola idKlienta
     * @param id int identyfikator reżysera
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda zwracająca wartość pola nazwa
     * @return String nazwa reżysera
     */
    public String getNazwa() {return nazwa;}
    /**
     * Metoda ustawiająca wartość pola idKlienta
     * @param nazwa String nazwa reżysera
     */
    public void setNazwa(String nazwa) {this.nazwa = nazwa;}

    @Override
    public String toString() {
        return "Rezyser{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
