package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
    @FXML
    private CheckBox admin;
    @FXML
    private TextField loginText, hasloText;
    @FXML
    private ObservableList<Gatunek> gatunki = FXCollections.observableArrayList();
    @FXML
    private void addKlientToDataBase()
    {
        Klient klient = new Klient();
        Konto konto = new Konto();
        klient.setImie(imie.getText());
        klient.setNazwisko(nazwisko.getText());
        klient.setEmail(email.getText());
        konto.setLogin(login.getText());
        konto.setHaslo(haslo.getText());
        String hasloString = haslo2.getText();
        konto.setAdmin(admin.isSelected());
        konto.setKlient(klient);
        if(hasloString.equals(konto.getHaslo()) == true ) {
            Connection.initDataBase();
            KlientDao klientDao = new KlientDao(Connection.getConnectionSource());
            KontoDao kontoDao = new KontoDao(Connection.getConnectionSource());
            klientDao.createOrUpdateTabel(klient);
            kontoDao.createOrUpdateTabel(konto);
            Connection.disconnect();
        }
    }
    /*private void LogIn()
    {
        String login, haslo;
        login = loginText.getText();
        haslo = hasloText.getText();

        Connection.initDataBase();
        KontoDao kontoDao = new KontoDao(Connection.getConnectionSource());

        List<Konto> kontoList = kontoDao.queryforAll(Konto.class);
        kontoList.forEach(e->{
            Konto konto = new Konto();
            konto.setLogin(e.getLogin());
            konto.setHaslo(e.getHaslo());
        });

    }
    @FXML
    private void saveGatunki() {

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
    }*/
    @FXML
    public void initialize() throws SQLException {
    }
}