package pl.controllers;

import javafx.fxml.FXML;
import pl.bazadanych.dao.DaneWypozyczeniaDao;
import pl.bazadanych.dao.WypozyczenieDao;
import pl.bazadanych.tables.DaneWypozyczenia;
import pl.tablesFx.DaneWypozyczeniaFx;
import pl.tablesFx.FilmFx;
import pl.accessories.Singleton;

/**
 * Created by Mateusz on 2017-05-24.
 */
public class WypozyczController extends FilmEditController{

    @FXML
    private void borrow(){
        DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
        manageSearch();
        setFreeEgzemplarz(daneWypozyczeniaFx);
        setKlient(daneWypozyczeniaFx);
        setWypozyczenie(daneWypozyczeniaFx);

        DaneWypozyczeniaDao daneWypozyczeniaDao = new DaneWypozyczeniaDao();

        daneWypozyczeniaDao.insertDaneWypozyczenia(daneWypozyczeniaFx);
        logger.logFileAndConsole("info", "Dodano wypozyczenie do BD.");
    }
    private void setFreeEgzemplarz(DaneWypozyczeniaFx daneWypozyczeniaFx){
        if(freeEgzemplarzFxList.size() != 0)
            daneWypozyczeniaFx.setIdEgzemplarzu(freeEgzemplarzFxList.get(0).getId());
        else
            warningWindow("Brak wolnych egzemplarzy");
    }
    private void setKlient(DaneWypozyczeniaFx daneWypozyczeniaFx){
        kontoFxObjectProperty.set(kontoListView.getSelectionModel().getSelectedItem());
        daneWypozyczeniaFx.setIdKlienta(kontoFxObjectProperty.getValue().getId());
    }
    private void setWypozyczenie(DaneWypozyczeniaFx daneWypozyczeniaFx){
        WypozyczenieDao wypozyczenieDao = new WypozyczenieDao();
        wypozyczenieDao.insertWypozyczenie();
        daneWypozyczeniaFx.setIdWypozyczenia(wypozyczenieDao.selectMaxId());
    }
    public void initialize(){
        this.filmFx = Singleton.getInstance().getFilmFx();
    }

}
