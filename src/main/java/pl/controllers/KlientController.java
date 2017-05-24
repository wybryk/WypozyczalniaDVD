package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.*;

import pl.bazadanych.Connections;
import pl.bazadanych.dao.FilmDao;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.dao.KlientDao;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tables.Klient;
import pl.bazadanych.tables.Konto;
import pl.tablesFx.FilmFx;
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
    protected ListView<FilmFx> filmListView;
    @FXML
    private ObservableList<FilmFx> filmList = FXCollections.observableArrayList();
    @FXML
    protected TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField, hasloTextField, haslo2TextField, filmTextField;
    @FXML
    protected ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();

<<<<<<< HEAD
=======
    private static final String LOGIN_FXML = "/logowanie.fxml";

    @FXML
    private void createOrUpdateKlientInDataBase(){
        Klient klient = new Klient();
        Konto konto = new Konto();
        klient.setImie(imieTextField.getText());
        klient.setNazwisko(nazwiskoTextField.getText());
        klient.setEmail(emailTextField.getText());
        konto.setLogin(loginTextField.getText());
        konto.setHaslo(hasloTextField.getText());
        String haslo2 = haslo2TextField.getText();

        if(haslo2.equals(konto.getHaslo()) == true ) {
            KlientDao klientDao = new KlientDao();
            KontoDao kontoDao = new KontoDao();
            klientDao.insertKlient(klient);
            int id = klientDao.findKlient(klient);
            kontoDao.insertKonto(konto, id);
        }
    }
>>>>>>> refs/remotes/origin/to-co-Damian-robi

    @FXML
    public void initialize() throws SQLException {
        GatunekDao gatunekDao = new GatunekDao();
        gatunekList = gatunekDao.selectAll();
        gatunekListView.setItems(gatunekList);
    }

    @FXML
    public void findMovie() {
        String title = filmTextField.getText();
        FilmDao filmDao = new FilmDao();
        List<FilmFx> filmFxList = filmDao.selectAllFilm();

        filmFxList.forEach(e -> {
            if (title.equals(e.getNazwa()))
            {
                FilmFx filmFx = new FilmFx();
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
        filmListView.setItems(filmList);
    }

    public void logOut(ActionEvent actionEvent) {
        changeWindow(actionEvent, LOGIN_FXML);
    }

    public void viewBorrowed(ActionEvent actionEvent) {
    }

    public void viewAccount(ActionEvent actionEvent) {
    }

    public void deleteMovie(ActionEvent actionEvent) {
    }

    public void editMovie(ActionEvent actionEvent) {
    }

    public void borrowMovie(ActionEvent actionEvent) {
    }
}
