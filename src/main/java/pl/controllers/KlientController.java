package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.bazadanych.Connection;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.dao.KlientDao;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tables.Gatunek;
import pl.bazadanych.tables.Klient;
import pl.bazadanych.tables.Konto;
import pl.tablesFx.GatunekFx;


import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class KlientController extends BaseController{
    @FXML
    private ListView<GatunekFx> gatunekListView;
    @FXML
    private TextField imie, nazwisko, email, login, haslo, haslo2;
    @FXML
    protected ObservableList<GatunekFx> gatunekFxList = FXCollections.observableArrayList();

    @FXML
    protected void saveGatunek()  {

        Connection.initDataBase();
        GatunekDao gatunekDao = new GatunekDao(Connection.getConnectionSource());

        List<Gatunek> gatunek = gatunekDao.queryforAll(Gatunek.class);
        gatunek.forEach(e->{
            GatunekFx gatunekFx = new GatunekFx();
            gatunekFx.setId(e.getId());
            gatunekFx.setNazwa(e.getNazwa());
            gatunekFxList.add(gatunekFx);
        });
        Connection.disconnect();
    }

    @FXML
    private void createOrUpdateKlientInDataBase(){
        Klient klient = new Klient();
        Konto konto = new Konto();
        klient.setImie(imie.getText());
        klient.setNazwisko(nazwisko.getText());
        klient.setEmail(email.getText());
        konto.setLogin(login.getText());
        konto.setHaslo(haslo.getText());
        String hasloString = haslo2.getText();

        if(hasloString.equals(konto.getHaslo()) == true ) {
            Connection.initDataBase();
            KlientDao klientDao = new KlientDao(Connection.getConnectionSource());
            KontoDao kontoDao = new KontoDao(Connection.getConnectionSource());
            klientDao.createOrUpdateTabel(klient);
            kontoDao.createOrUpdateTabel(konto);
            Connection.disconnect();
        }
    }

    @FXML
    public void initialize() throws SQLException {
        saveGatunek();
        gatunekListView.setItems(gatunekFxList);
    }

}
