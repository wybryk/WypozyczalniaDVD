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
import pl.bazadanych.dao.DaneWypozyczeniaDao;
import pl.bazadanych.dao.EgzemplarzDao;
import pl.bazadanych.dao.FilmDao;
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
    private ListView<FilmFx>  borrowsListView;


    ObservableList<DaneWypozyczeniaFx> daneWypozyczeniaList = FXCollections.observableArrayList();

    ObservableList<EgzemplarzFx> egzemplarzList = FXCollections.observableArrayList();

    ObservableList<FilmFx> filmList = FXCollections.observableArrayList();

    private KontoFx kontoFx;

    private void getDaneWypozyczeniaFromDataBase(){
        DaneWypozyczeniaDao daneWypozyczeniaDao = new DaneWypozyczeniaDao();
        List<DaneWypozyczeniaFx> daneWypozyczeniaFxList = daneWypozyczeniaDao.selectAll();
        daneWypozyczeniaFxList.forEach(e->{
            if(e.getIdKlienta()==kontoFx.getKlientfx()){
                DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
                daneWypozyczeniaFx.setIdEgzemplarzu(e.getIdEgzemplarzu());
                daneWypozyczeniaFx.setIdKlienta(e.getIdKlienta());
                daneWypozyczeniaFx.setIdWypozyczenia(e.getIdWypozyczenia());

                daneWypozyczeniaList.add(daneWypozyczeniaFx);
            }
        });
    }
    private void getEgzemplarzFromDataBase(){
        getDaneWypozyczeniaFromDataBase();
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();
        List<EgzemplarzFx> egzemplarzFxList = egzemplarzDao.selectAll();
        egzemplarzFxList.forEach(e->{
            daneWypozyczeniaList.forEach(i-> {
                if (e.getId() == i.getIdEgzemplarzu()) {
                    EgzemplarzFx egzemplarzFx = new EgzemplarzFx();
                    egzemplarzFx.setId(e.getId());
                    egzemplarzFx.setIdFilmu(e.getIdFilmu());

                    egzemplarzList.add(egzemplarzFx);
                }
            });
        });
    }
    public void getFilmFromDataBase() {
        getEgzemplarzFromDataBase();
        FilmDao filmDao = new FilmDao();
        List<FilmFx> filmFxList = filmDao.selectAllFilm();

        filmFxList.forEach(e -> {
            egzemplarzList.forEach(i->{
                if (e.getId() == i.getIdFilmu())
                {   FilmFx filmFx = new FilmFx();

                    filmFx.setId(e.getId());
                    filmFx.setNazwa(e.getNazwa());
                    filmFx.setIlosc(e.getIlosc());
                    filmFx.setOpis(e.getOpis());
                    filmFx.setPremiera(e.getPremiera());
                    filmFx.setGatunekFx(e.getGatunekFx());
                    filmFx.setRezyserFx(e.getRezyserFx());

                    filmList.add(filmFx);
                }
             });
        });
        borrowsListView.setItems(filmList);
    }

    public void initialize(){
        this.kontoFx = Singleton.getInstance().getKontoFx();
        getFilmFromDataBase();
    }
}
