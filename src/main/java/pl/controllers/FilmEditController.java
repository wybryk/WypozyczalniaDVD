package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SpinnerValueFactory;
import pl.accessories.Converters;
import pl.bazadanych.tables.DaneWypozyczenia;
import pl.bazadanych.tables.Egzemplarz;
import pl.bazadanych.tables.Wypozyczenie;
import pl.tablesFx.*;
import pl.accessories.Singleton;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static pl.accessories.Converters.*;

/**
 * <h2>Klasa kontrolera edycji danych filmu.</h2>
 * <p>Zawiera metody potrzebne do edycji danych filmu.</p>
 */
public class FilmEditController extends AdminController{

    protected ObservableList<EgzemplarzFx> egzemplarzFxList = FXCollections.observableArrayList();

    protected ObservableList<DaneWypozyczeniaFx> daneWypozyczeniaFxList = FXCollections.observableArrayList();

    protected ObservableList<WypozyczenieFx> wypozyczenieFxList = FXCollections.observableArrayList();

    protected ObservableList<EgzemplarzFx> freeEgzemplarzFxList = FXCollections.observableArrayList();

    protected FilmFx filmFx;

    /**
     * Metoda aktualizuje dane filmu w bazie danych.
     */
    @FXML
    private void updateFilmToDataBase(){
        FilmFx filmFx = getFilmValues();
        filmFx.setId(this.filmFx.getId());
        EgzemplarzFx egzemplarzFx = new EgzemplarzFx();

        if (this.filmFx.getIlosc() < filmFx.getIlosc()) {
            wypozyczalniaClient("Film", "find", toFilm(filmFx));
            egzemplarzFx.setIdFilmu(super.film.getId());
            for (int i = 0; i < (filmFx.getIlosc() - this.filmFx.getIlosc()); i++)
                wypozyczalniaClient("Egzemplarz", "insert", toEgzemplarz(egzemplarzFx));
            wypozyczalniaClient("Film", "update", toFilm(filmFx));
        }
        else if (this.filmFx.getIlosc() > filmFx.getIlosc()) {
            manageSearch();
            if (freeEgzemplarzFxList.size() >= ( this.filmFx.getIlosc() - filmFx.getIlosc())) {
                for (int i = freeEgzemplarzFxList.size() - 1; i > filmFx.getIlosc() - 1; i--) {
                    egzemplarzFx.setId(freeEgzemplarzFxList.get(i).getId());
                    wypozyczalniaClient("Egzemplarz", "deleteByIdEgzemplarzu", toEgzemplarz(egzemplarzFx));
                    System.out.println("Usuwam: " + egzemplarzFx.getId());
                }
                wypozyczalniaClient("Film", "update", toFilm(filmFx));
            } else
                warningWindow("Brak wolnych egzemplarzy");
        }

        this.filmFx = filmFx;
        freeEgzemplarzFxList.removeAll();
    }
    /**
     * Metoda pobiera egzemplarz danego filmu z bazy danych.
     */
    private void getEgzemplarzFromDataBase(){
        wypozyczalniaClient("Egzemplarz", "selectAll", null);
        ObservableList<Egzemplarz> egzemplarzList = FXCollections.observableArrayList(super.egzemplarzList);
        egzemplarzList.forEach(e->{
            if (e.getIdFilmu() == this.filmFx.getId()) {
                EgzemplarzFx egzemplarzFx = new EgzemplarzFx();
                egzemplarzFx.setId(e.getId());
                egzemplarzFx.setIdFilmu(e.getIdFilmu());
                this.egzemplarzFxList.add(egzemplarzFx);
            }
        });
    }
    /**
     * Metoda pobiera dane na temat wypożyczeń z bazy danych.
     */
    private void getDaneWypozyczeniaFromDataBase(){
        wypozyczalniaClient("DaneWypozyczenia", "selectAll", null);
        ObservableList<DaneWypozyczenia> daneWypozyczeniaList = FXCollections.observableArrayList(super.daneWypozyczeniaList);
        egzemplarzFxList.forEach(i -> {
            daneWypozyczeniaList.forEach(e-> {
                if ( e.getIdEgzemplarzu() == i.getId() ){
                    DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
                    daneWypozyczeniaFx.setIdEgzemplarzu(e.getIdEgzemplarzu());
                    daneWypozyczeniaFx.setIdKlienta(e.getIdKlienta());
                    daneWypozyczeniaFx.setIdWypozyczenia(e.getIdWypozyczenia());
                    this.daneWypozyczeniaFxList.add(daneWypozyczeniaFx);
                }
            });
        });
    }
    /**
     * Metoda pobiera dane na temat wypożyczenia danego filmu z bazy danych.
     */
    private void getWypozyczenieFromDataBase(){
        wypozyczalniaClient("Wypozyczenie", "selectAll", null);
        ObservableList<Wypozyczenie> wypozyczenieList = FXCollections.observableArrayList(super.wypozyczenieList);
        wypozyczenieList.forEach(e->{
            daneWypozyczeniaFxList.forEach(i->{
                if(e.getId() == i.getIdWypozyczenia()) {
                    WypozyczenieFx wypozyczenieFx = new WypozyczenieFx();
                    wypozyczenieFx.setId(e.getId());
                    wypozyczenieFx.setDataWypozyczenia((Date) e.getDataWypozyczenia());
                    wypozyczenieFx.setDataOddania((Date) e.getDataOddania());
                    this.wypozyczenieFxList.add(wypozyczenieFx);
                }
            });
        });
    }
    /**
     * Metoda wyszukująca wolny egzemplarz danego filmu.
     */
    private void searchFreeEgzemplarz(){

        int [] egzemplarzIndex = new int[egzemplarzFxList.size()];
        for(int i = 0; i < egzemplarzIndex.length; i++)
            egzemplarzIndex[i]=-1;
        for (int i = 0; i < egzemplarzFxList.size(); i++) {
            for(int j = 0; j < daneWypozyczeniaFxList.size(); j++) {
                if ( daneWypozyczeniaFxList.get(j).getIdEgzemplarzu() == egzemplarzFxList.get(i).getId()) {
                    egzemplarzIndex[i] = j;
                    continue;
                }
            }
        }
        for(int i = 0; i < egzemplarzIndex.length; i++){
            if(egzemplarzIndex[i] == -1){
                EgzemplarzFx egzemplarzFx = new EgzemplarzFx();
                egzemplarzFx.setId(egzemplarzFxList.get(i).getId());
                egzemplarzFx.setIdFilmu(egzemplarzFxList.get(i).getIdFilmu());
                this.freeEgzemplarzFxList.add(egzemplarzFx);
            }
        }
    }
    /**
     * Metoda wyszukująca egzemplarze danego filmu.
     */
    private void searchEgzemplarz(){

        int [] [] wypozyczenieIndex = new int[wypozyczenieFxList.size()][2];
        int [] egzemplarzIndex;
        if( wypozyczenieIndex.length == 0)
            egzemplarzIndex = new int[0];
        else
            egzemplarzIndex = new int[egzemplarzFxList.size()];
        for(int i = 0; i < wypozyczenieIndex.length; i++) {
            wypozyczenieIndex[i][1] = -1;
            wypozyczenieIndex[i][0] = 0;
        }
        for (int i = 0; i < wypozyczenieFxList.size(); i++) {
            for(int j = 0; j < daneWypozyczeniaFxList.size(); j++) {
                if ( daneWypozyczeniaFxList.get(j).getIdWypozyczenia() == wypozyczenieFxList.get(i).getId()) {
                    if( Converters.toDate(LocalDate.now()).after(wypozyczenieFxList.get(i).getDataOddania()))
                        wypozyczenieIndex[i][0] = daneWypozyczeniaFxList.get(j).getIdEgzemplarzu();
                    else {
                        wypozyczenieIndex[i][0] = daneWypozyczeniaFxList.get(j).getIdEgzemplarzu();
                        wypozyczenieIndex[i][1] = 0;
                    }
                }
            }
        }
        int indexEgz = 1; //index wypożyczonego filmu
        for(int i = 0; i < wypozyczenieIndex.length; i++)
            for(int j = 0; j < wypozyczenieIndex.length; j++) {
                if ( i != j)
                    if ( wypozyczenieIndex[j][1] == 0) {
                        indexEgz = wypozyczenieIndex[j][0];
                        if (wypozyczenieIndex[i][0] == indexEgz)
                            wypozyczenieIndex[i][1] = 0;
                    }
            }

        if ( (wypozyczenieIndex.length) != 0 ){
            for (int i = 0; i < egzemplarzFxList.size(); i++) {
                for(int j = 0; j < wypozyczenieIndex.length; j++) {
                    if ( wypozyczenieIndex[j][0] == egzemplarzFxList.get(i).getId() && wypozyczenieIndex[j][1] == -1) {
                        egzemplarzIndex[i] = wypozyczenieIndex[j][0];
                        continue;
                    }
                }
            }
        }
        for(int i = 0; i < egzemplarzIndex.length; i++){
            if(egzemplarzIndex[i] != 0){
                EgzemplarzFx egzemplarzFx = new EgzemplarzFx();
                egzemplarzFx.setId(egzemplarzIndex[i]);
                egzemplarzFx.setIdFilmu(findIdFilmu(egzemplarzIndex[i]));
                this.freeEgzemplarzFxList.add(egzemplarzFx);
            }
        }

    }
    /**
     * Metoda wyszukująca filmy o danym id na liście filmów.
     * @param id identyfikator filmu
     * @return int identyfikator elementu listy filmów.
     */
    private int findIdFilmu(int id){
        int idFilmu = -1;
        for(EgzemplarzFx e: egzemplarzFxList){
            if(e.getId() == id) {
                idFilmu = e.getIdFilmu();
            }
        }
        return idFilmu;
    }
    /**
     * Metoda zarządzająca wyszukiwaniem wolnego egzemplarza.
     * Wywołuje tylko inne metody.
     */
    protected void manageSearch(){
        getEgzemplarzFromDataBase();
        getDaneWypozyczeniaFromDataBase();
        getWypozyczenieFromDataBase();
        searchFreeEgzemplarz();
        searchEgzemplarz();
    }
    /**
     * Metoda ustawiająca wartości pól dla danego filmu.
     */
    private void setTextFields(){
        RezyserFx rezyserFx = new RezyserFx();
        rezyserFx.setId(filmFx.getRezyserFx());
        rezyserFx = findRezyser(rezyserFx);
        nazwaTextField.setText(filmFx.getNazwa());
        opisTextArea.setText(filmFx.getOpis());
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, filmFx.getIlosc());
        liczbaKopiSpinner.setValueFactory(valueFactory);
        premieraDatePicker.setValue(Converters.toLocalDate(filmFx.getPremiera()));
        gatunekComboBox.getSelectionModel().select(filmFx.getGatunekFx()-1);
        rezyserTextField.setText(rezyserFx.getNazwa());

    }
    /**
     * Metoda znajdująca reżysera o danym id i konwertująca go na obiekt typu RezyserFx.
     * @param rezyserFx rezyser parametr będący obiektem typu RezyserFx
     * @return obiekt typu RezyserFx
     */
    private RezyserFx findRezyser(RezyserFx rezyserFx){
        wypozyczalniaClient("Rezyser", "findById", toRezyser(rezyserFx));
        return toRezyserFx(super.rezyser);
    }

    @FXML
    public void initialize()  {
        this.filmFx = Singleton.getInstance().getFilmFx();
        liczbaKopiSpinner.setValueFactory(valueFactory);
        setGatunekFxList();
        gatunekComboBox.setItems(gatunekFxList);
        setTextFields();

    }
}
