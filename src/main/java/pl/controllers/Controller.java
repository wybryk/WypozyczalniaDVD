package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import pl.bazadanych.tables.*;
import pl.bazadanych.tablesFx.GatunekFx;

import java.sql.SQLException;
import java.util.List;


public class Controller {
    @FXML
    private ListView<GatunekFx> gatunekListView;
    @FXML
    private ComboBox<GatunekFx> gatunekComboBox;
    @FXML
    private TextField imie, nazwisko, email, login, haslo, haslo2;
    @FXML
    private CheckBox admin;
    @FXML
    private TextField loginText, hasloText;
    @FXML
    private ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();
    private ObjectProperty<GatunekFx> gatunek = new SimpleObjectProperty<>();
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
    /*@FXML
    private void LogIn()
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
    }*/
    @FXML
    private void saveGatunek()  {

        Connection.initDataBase();
        GatunekDao gatunekDao = new GatunekDao(Connection.getConnectionSource());

        List<Gatunek> gatunek = gatunekDao.queryforAll(Gatunek.class);
        gatunek.forEach(e->{
            GatunekFx gatunekFx = new GatunekFx();
            gatunekFx.setId(e.getId());
            gatunekFx.setNazwa(e.getNazwa());
            gatunekList.add(gatunekFx);
        });
        Connection.disconnect();
    }
    @FXML
    public void initialize() throws SQLException {
        saveGatunek();
        gatunekComboBox.setItems(gatunekList);
        gatunekListView.setItems(gatunekList);
    }
}