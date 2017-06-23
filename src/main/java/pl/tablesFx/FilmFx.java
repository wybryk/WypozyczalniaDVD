package pl.tablesFx;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

/**
 * <h2>Klasa przechowująca dane filmu</h2>
 * <p>Zawiera pola z tabeli FILM.</p>
 */
public class FilmFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private StringProperty opis = new SimpleStringProperty();
    private IntegerProperty ilosc = new SimpleIntegerProperty();
    private ObjectProperty<Date> premiera = new SimpleObjectProperty<>();
    private IntegerProperty gatunekFx = new SimpleIntegerProperty();
    private IntegerProperty rezyserFx = new SimpleIntegerProperty();
    /**
     * Metoda zwracająca wartość pola id
     * @return int id filmu
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator filmu
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola nazwa
     * @return String nazwa filmu
     */
    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }
    /**
     * Metoda ustawiająca wartość pola nazwa
     * @param nazwa String nazwa filmu
     */
    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }
    /**
     * Metoda zwracająca wartość pola opis
     * @return String opis filmu
     */
    public String getOpis() {
        return opis.get();
    }

    public StringProperty opisProperty() {
        return opis;
    }
    /**
     * Metoda ustawiająca wartość pola opis
     * @param opis String opis filmu
     */
    public void setOpis(String opis) {
        this.opis.set(opis);
    }
    /**
     * Metoda zwracająca wartość pola ilosc
     * @return int ilość egzemplarzy filmu.
     */
    public int getIlosc() {
        return ilosc.get();
    }

    public IntegerProperty iloscProperty() {
        return ilosc;
    }
    /**
     * Metoda ustawiająca wartość pola ilosc
     * @param ilosc int ilość egzemplarzy filmu.
     */
    public void setIlosc(int ilosc) {
        this.ilosc.set(ilosc);
    }
    /**
     * Metoda zwracająca wartość pola premiera
     * @return Date data premiery
     */
    public Date getPremiera() {
        return premiera.get();
    }

    public ObjectProperty<Date> premieraProperty() {
        return premiera;
    }
    /**
     * Metoda ustawiająca wartość pola premiera
     * @param premiera data premiery
     */
    public void setPremiera(Date premiera) {
        this.premiera.set(premiera);
    }
    /**
     * Metoda zwracająca wartość pola gatunek
     * @return int id gatunku filmowego.
     */
    public int getGatunekFx() {
        return gatunekFx.get();
    }

    public IntegerProperty gatunekFxProperty() {
        return gatunekFx;
    }
    /**
     * Metoda ustawiająca wartość pola gatunek
     * @param gatunekFx id gatunku filmowego.
     */
    public void setGatunekFx(int gatunekFx) {
        this.gatunekFx.set(gatunekFx);
    }
    /**
     * Metoda zwracająca wartość pola rezyser
     * @return int id reżysera
     */
    public int getRezyserFx() {
        return rezyserFx.get();
    }

    public IntegerProperty rezyserFxProperty() {
        return rezyserFx;
    }
    /**
     * Metoda ustawiająca wartość pola rezyser
     * @param rezyserFx int id reżysera
     */
    public void setRezyserFx(int rezyserFx) {
        this.rezyserFx.set(rezyserFx);
    }

    @Override
    public String toString() {
        return " " + nazwa.getValue() +
                "\n Premiera: " + premiera.getValue() +
                "\n Opis: " + opis.getValue() + "";
    }
}
