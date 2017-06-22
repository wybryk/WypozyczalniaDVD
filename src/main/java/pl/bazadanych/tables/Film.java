package pl.bazadanych.tables;

import java.sql.Date;
import java.time.LocalDate;

/**
 * <h2>Klasa przechowująca dane filmu</h2>
 * <p>Zawiera pola z tabeli FILM.</p>
 */
public class Film {

    private int id;
    private String nazwa;
    private String opis;
    private int ilosc;
    private Date premiera;
    private int gatunek;
    private int rezyser;

    public Film() {}
    /**
     * Metoda zwracająca wartość pola id
     * @return int id filmu
     */
    public int getId() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator filmu
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Metoda zwracająca wartość pola nazwa
     * @return String nazwa filmu
     */
    public String getNazwa() {
        return nazwa;
    }
    /**
     * Metoda ustawiająca wartość pola nazwa
     * @param nazwa String nazwa filmu
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    /**
     * Metoda zwracająca wartość pola opis
     * @return String opis filmu
     */
    public String getOpis() {
        return opis;
    }
    /**
     * Metoda ustawiająca wartość pola opis
     * @param opis String opis filmu
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }
    /**
     * Metoda zwracająca wartość pola ilosc
     * @return int ilość egzemplarzy filmu.
     */
    public int getIlosc() {
        return ilosc;
    }
    /**
     * Metoda ustawiająca wartość pola ilosc
     * @param ilosc int ilość egzemplarzy filmu.
     */
    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
    /**
     * Metoda zwracająca wartość pola premiera
     * @return Date data premiery
     */
    public Date getPremiera() {
        return premiera;
    }
    /**
     * Metoda ustawiająca wartość pola premiera
     * @param premiera data premiery
     */
    public void setPremiera(Date premiera) {
        this.premiera = premiera;
    }
    /**
     * Metoda zwracająca wartość pola gatunek
     * @return int id gatunku filmowego.
     */
    public int getGatunek() {
        return gatunek;
    }
    /**
     * Metoda ustawiająca wartość pola gatunek
     * @param gatunek id gatunku filmowego.
     */
    public void setGatunek(int gatunek) {
        this.gatunek = gatunek;
    }
    /**
     * Metoda zwracająca wartość pola rezyser
     * @return int id reżysera
     */
    public int getRezyser() {
        return rezyser;
    }
    /**
     * Metoda ustawiająca wartość pola rezyser
     * @param rezyser int id reżysera
     */
    public void setRezyser(int rezyser) {
        this.rezyser = rezyser;
    }


    @Override
    public String toString() {
        return "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                ", ilosc=" + ilosc +
                ", premiera=" + premiera +
                ", gatunek=" + gatunek +
                ", rezyser=" + rezyser +
                '}';
    }
}
