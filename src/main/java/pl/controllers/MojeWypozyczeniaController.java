package pl.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Duration;
import pl.bazadanych.dao.DaneWypozyczeniaDao;
import pl.bazadanych.dao.EgzemplarzDao;
import pl.bazadanych.dao.FilmDao;
import pl.bazadanych.dao.WypozyczenieDao;
import pl.bazadanych.tables.DaneWypozyczenia;
import pl.bazadanych.tables.Egzemplarz;
import pl.bazadanych.tables.Film;
import pl.bazadanych.tables.Wypozyczenie;
import pl.tablesFx.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Mateusz on 2017-05-25.
 */
public class MojeWypozyczeniaController {
    @FXML
    private ListView<FilmFx>  reservationListView;
    @FXML
    private ListView<MojeWypozyczenia>  borrowsListView;


    ObservableList<DaneWypozyczeniaFx> daneWypozyczeniaFxList = FXCollections.observableArrayList();

    ObservableList<EgzemplarzFx> egzemplarzFxList = FXCollections.observableArrayList();

    ObservableList<FilmFx> filmFxList = FXCollections.observableArrayList();

    ObservableList<WypozyczenieFx> wypozyczenieFxList = FXCollections.observableArrayList();

    ObservableList<MojeWypozyczenia> mojeWypozyczeniaList = FXCollections.observableArrayList();

    private KontoFx kontoFx;

    private void getDaneWypozyczeniaFromDataBase(){
        DaneWypozyczeniaDao daneWypozyczeniaDao = new DaneWypozyczeniaDao();
        List<DaneWypozyczenia> daneWypozyczeniaList = daneWypozyczeniaDao.selectAll();
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
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();
        List<Egzemplarz> egzemplarzList = egzemplarzDao.selectAll();
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
        FilmDao filmDao = new FilmDao();
        List<Film> filmList = filmDao.selectAllFilm();

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
        WypozyczenieDao wypozyczenieDao = new WypozyczenieDao();
        List<Wypozyczenie> wypozyczenieList = wypozyczenieDao.selectAll();

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

    public void initialize(){
        this.kontoFx = Singleton.getInstance().getKontoFx();
        getDaneWypozyczeniaFromDataBase();
        getEgzemplarzFromDataBase();
        getFilmFromDataBase();
        getWypozyczenieFromDataBase();
        setMojeWypozyczenia();

    }
}
