package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pl.accessories.MojeWypozyczenia;
import pl.accessories.Singleton;
import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Mateusz on 2017-05-25.
 */
public class MojeWypozyczeniaController extends BaseController{
    @FXML
    private ListView<FilmFx>  reservationListView;
    @FXML
    private ListView<MojeWypozyczenia>  borrowsListView;

    ObservableList<DaneWypozyczeniaFx> daneWypozyczeniaFxList = FXCollections.observableArrayList();

    ObservableList<EgzemplarzFx> egzemplarzFxList = FXCollections.observableArrayList();

    ObservableList<FilmFx> filmFxList = FXCollections.observableArrayList();
    ObservableList<FilmFx> resFilmFxList = FXCollections.observableArrayList();

    ObservableList<WypozyczenieFx> wypozyczenieFxList = FXCollections.observableArrayList();

    ObservableList<MojeWypozyczenia> mojeWypozyczeniaList = FXCollections.observableArrayList();

    ObservableList<RezerwacjaFX> rezerwacjaFxList = FXCollections.observableArrayList();

    private KontoFx kontoFx;

    private void getDaneWypozyczeniaFromDataBase(){
        wypozyczalniaClient("DaneWypozyczenia", "selectAll", null);
        ObservableList<DaneWypozyczenia> daneWypozyczeniaList = FXCollections.observableArrayList(super.daneWypozyczeniaList);
        daneWypozyczeniaList.forEach(e->{
            if(e.getIdKlienta()==kontoFx.getKlientfx()){
                DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
                daneWypozyczeniaFx.setIdEgzemplarzu(e.getIdEgzemplarzu());
                daneWypozyczeniaFx.setIdKlienta(e.getIdKlienta());
                daneWypozyczeniaFx.setIdWypozyczenia(e.getIdWypozyczenia());

                daneWypozyczeniaFxList.add(daneWypozyczeniaFx);
            }
        });
    }

    private void getEgzemplarzFromDataBase(){
        wypozyczalniaClient("Egzemplarz", "selectAll", null);
        ObservableList<Egzemplarz> egzemplarzList = FXCollections.observableArrayList(super.egzemplarzList);
        egzemplarzList.forEach(e->{
            daneWypozyczeniaFxList.forEach(i-> {
                if (e.getId() == i.getIdEgzemplarzu()) {
                    EgzemplarzFx egzemplarzFx = new EgzemplarzFx();
                    egzemplarzFx.setId(e.getId());
                    egzemplarzFx.setIdFilmu(e.getIdFilmu());
                    this.egzemplarzFxList.add(egzemplarzFx);
                }
            });
        });
    }

    private void getFilmFromDataBase() {
        wypozyczalniaClient("Film", "selectAll", null);
        ObservableList<Film> filmList = FXCollections.observableArrayList(super.filmList);
        filmList.forEach(e -> {
            egzemplarzFxList.forEach(i->{
                if (e.getId() == i.getIdFilmu())
                {
                    FilmFx filmFx = new FilmFx();
                    filmFx.setId(e.getId());
                    filmFx.setNazwa(e.getNazwa());
                    filmFx.setIlosc(e.getIlosc());
                    filmFx.setOpis(e.getOpis());
                    filmFx.setPremiera(e.getPremiera());
                    filmFx.setGatunekFx(e.getGatunek());
                    filmFx.setRezyserFx(e.getRezyser());

                    filmFxList.add(filmFx);
                }
             });
        });
    }

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

    private void setMojeWypozyczenia(){
        egzemplarzFxList.forEach(i->{
            wypozyczenieFxList.forEach(j->{
                filmFxList.forEach(k->{
                    daneWypozyczeniaFxList.forEach(l -> {
                        if (l.getIdWypozyczenia() == j.getId() && l.getIdEgzemplarzu() == i.getId() && i.getIdFilmu() == k.getId()) {
                            MojeWypozyczenia mojeWypozyczenia = new MojeWypozyczenia();
                            mojeWypozyczenia.setEgzemplarzFx(i);
                            mojeWypozyczenia.setFilmFx(k);
                            mojeWypozyczenia.setWypozyczenieFx(j);
                            mojeWypozyczeniaList.add(mojeWypozyczenia);
                        }
                    });
                });
            });
        });
        borrowsListView.setItems(mojeWypozyczeniaList);
    }

    private void getReservationsFromDatabase() {
        wypozyczalniaClient("Rezerwacja", "selectAll", null);
        ObservableList<Rezerwacja> rezerwacjaList = FXCollections.observableArrayList(super.rezerwacjaList);
        rezerwacjaList.forEach(e -> {
            if (e.getIdKlienta() == kontoFx.getKlientfx()) {
                RezerwacjaFX rezerwacjaFX = new RezerwacjaFX();
                rezerwacjaFX.setId(e.getId());
                rezerwacjaFX.setFilmFx(e.getIdFilmu());
                rezerwacjaFX.setKlientFx(e.getIdKlienta());

                rezerwacjaFxList.add(rezerwacjaFX);
            }
        });
    }

    private void getResFilms() {
        if (rezerwacjaFxList.size() != 0) {
            wypozyczalniaClient("Film", "selectAll", null);
            ObservableList<Film> filmList = FXCollections.observableArrayList(super.filmList);
            filmList.forEach(e -> {
                rezerwacjaFxList.forEach(ev -> {
                    if (e.getId() == ev.getFilmFx()) {
                        FilmFx filmFx = new FilmFx();
                        filmFx.setId(e.getId());
                        filmFx.setNazwa(e.getNazwa());
                        filmFx.setRezyserFx(e.getRezyser());
                        filmFx.setGatunekFx(e.getGatunek());
                        filmFx.setOpis(e.getOpis());
                        filmFx.setIlosc(e.getIlosc());
                        filmFx.setPremiera(e.getPremiera());

                        resFilmFxList.add(filmFx);
                    }
                });
            });

            reservationListView.setItems(resFilmFxList);
        }
    }

    public void initialize(){
        this.kontoFx = Singleton.getInstance().getKontoFx();
        getDaneWypozyczeniaFromDataBase();
        getEgzemplarzFromDataBase();
        getFilmFromDataBase();
        getWypozyczenieFromDataBase();
        setMojeWypozyczenia();
        getReservationsFromDatabase();
        getResFilms();
    }
}
