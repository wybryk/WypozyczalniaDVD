package pl.tablesFx;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by Mateusz on 2017-04-26.
 */
public class FilmFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private StringProperty opis = new SimpleStringProperty();
    private IntegerProperty ilosc = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> premiera = new SimpleObjectProperty<>();
    private ObjectProperty<GatunekFx> gatunekFx = new SimpleObjectProperty<>();
    private ObjectProperty<RezyserFx> rezyserFx = new SimpleObjectProperty<>();

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

    public LocalDate getPremiera() {
        return premiera.get();
    }

    public ObjectProperty<LocalDate> premieraProperty() {
        return premiera;
    }

    public void setPremiera(LocalDate premiera) {
        this.premiera.set(premiera);
    }

    public GatunekFx getGatunekFx() {
        return gatunekFx.get();
    }

    public ObjectProperty<GatunekFx> gatunekFxProperty() {
        return gatunekFx;
    }

    public void setGatunekFx(GatunekFx gatunekFx) {
        this.gatunekFx.set(gatunekFx);
    }

    public RezyserFx getRezyserFx() {
        return rezyserFx.get();
    }

    public ObjectProperty<RezyserFx> rezyserFxProperty() {
        return rezyserFx;
    }

    public void setRezyserFx(RezyserFx rezyserFx) {
        this.rezyserFx.set(rezyserFx);
    }
}
