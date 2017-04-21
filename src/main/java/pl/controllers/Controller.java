package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.bazadanych.Connection;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.dao.KlientDao;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tabels.Gatunek;
import pl.bazadanych.tabels.Klient;
import pl.bazadanych.tabels.Konto;

import java.sql.SQLException;
import java.util.*;
import java.util.List;


public class Controller {
    @FXML
    private ListView<Gatunek> gatunkiList;
    @FXML
    private ComboBox<Gatunek> gatunkiBox;
    @FXML
    private TextField imie, nazwisko, email, login, haslo, haslo2;

    private ObservableList<Gatunek> gatunki = FXCollections.observableArrayList();
    @FXML
    public void addKlientToDataBase()
    {
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
    public void saveGatunki() {

        Connection.initDataBase();
        GatunekDao gatunekDao = new GatunekDao(Connection.getConnectionSource());

        List<Gatunek> gatunek = gatunekDao.queryforAll(Gatunek.class);
        gatunek.forEach(e->{
            Gatunek g = new Gatunek();
            g.setId(e.getId());
            g.setNazwa(e.getNazwa());
            this.gatunki.add(g);
        });
        Connection.disconnect();
    }
    @FXML
    public void initialize() throws SQLException {
        saveGatunki();
        //gatunkiList.setItems(gatunki);
        //gatunkiBox.setItems(gatunki);
    }
}