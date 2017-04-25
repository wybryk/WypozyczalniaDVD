package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pl.bazadanych.Connection;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.tables.Gatunek;
import pl.tablesFx.GatunekFx;


import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class KlientController {
    @FXML
    private ListView<GatunekFx> gatunekListView;
    @FXML
    private ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();

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
        gatunekListView.setItems(gatunekList);
    }

}
