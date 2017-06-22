package pl.bazadanych.tables;

/**
 * <h2>Klasa przechowująca dane wypozyczenia</h2>
 * <p>Zawiera pola z tabeli DANE_WYPOZYCZENIA.</p>
 */
public class DaneWypozyczenia {
    private int idKlienta;
    private int idWypozyczenia;
    private int idEgzemplarzu;
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
    /**
     * Metoda zwracająca wartość pola idWypozyczenia
     * @return int id wypożyczenia
     */
    public int getIdWypozyczenia() {
        return idWypozyczenia;
    }
    /**
     * Metoda ustawiająca wartość pola idWypozyczenia
     * @param idWypozyczenia int identyfikator wypozyczenia
     */
    public void setIdWypozyczenia(int idWypozyczenia) {
        this.idWypozyczenia = idWypozyczenia;
    }
    /**
     * Metoda zwracająca wartość pola idEgzemplarzu
     * @return int id egzemplarza
     */
    public int getIdEgzemplarzu() {
        return idEgzemplarzu;
    }
    /**
     * Metoda ustawiająca wartość pola idEgzemplarzu
     * @param idEgzemplarzu int identyfikator egzemlarza.
     */
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
