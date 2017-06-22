package pl.bazadanych.tables;

/**
 * <h2>Klasa przechowująca dane rezerwacji filmu.</h2>
 * <p>Zawiera pola z tabeli REZERWACJE.</p>
 */
public class Rezerwacja {
    private int id;
    private int idFilmu;
    private int idKlienta;

    public Rezerwacja() {}
    /**
     * Metoda zwracająca wartość pola id
     * @return int id rezerwacji
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator rezerwacji
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
    /**
     * Metoda zwracająca wartość pola idKlienta
     * @return int id klienta
     */
    public int getIdKlienta() {
        return idKlienta;
    }
    /**
     * Metoda ustawiająca wartość pola idKlienta
     * @param idKlienta int identyfikator klienta
     */
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
