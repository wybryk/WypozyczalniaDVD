package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import pl.bazadanych.Connections;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.dao.KlientDao;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tables.Klient;
import pl.bazadanych.tables.Konto;
import pl.tablesFx.GatunekFx;


import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class KlientController extends BaseController{
    @FXML
    private ListView<GatunekFx> gatunekListView;
    @FXML
    protected TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField, hasloTextField, haslo2TextField;
    @FXML
    protected ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws SQLException {
        GatunekDao gatunekDao = new GatunekDao();
        gatunekList = gatunekDao.selectAll();
        gatunekListView.setItems(gatunekList);
    }

}
