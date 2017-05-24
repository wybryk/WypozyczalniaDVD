package pl.tablesFx;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Mateusz on 2017-04-26.
 */
public class FilmFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private StringProperty opis = new SimpleStringProperty();
    private IntegerProperty ilosc = new SimpleIntegerProperty();
    private ObjectProperty<Date> premiera = new SimpleObjectProperty<>();
    private IntegerProperty gatunekFx = new SimpleIntegerProperty();
    private IntegerProperty rezyserFx = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getOpis() {
        return opis.get();
    }

    public StringProperty opisProperty() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis.set(opis);
    }

    public int getIlosc() {
        return ilosc.get();
    }

    public IntegerProperty iloscProperty() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc.set(ilosc);
    }

    public Date getPremiera() {
        return premiera.get();
    }

    public ObjectProperty<Date> premieraProperty() {
        return premiera;
    }

    public void setPremiera(Date premiera) {
        this.premiera.set(premiera);
    }

    public int getGatunekFx() {
        return gatunekFx.get();
    }

    public IntegerProperty gatunekFxProperty() {
        return gatunekFx;
    }

    public void setGatunekFx(int gatunekFx) {
        this.gatunekFx.set(gatunekFx);
    }

    public int getRezyserFx() {
        return rezyserFx.get();
    }

    public IntegerProperty rezyserFxProperty() {
        return rezyserFx;
    }

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
