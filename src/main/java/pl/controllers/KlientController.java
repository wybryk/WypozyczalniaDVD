package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import pl.tablesFx.*;


import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class KlientController extends BaseController{
    @FXML
    private ListView<GatunekFx> gatunekListView;
    @FXML
    private ListView<FilmFx> filmListView;
    @FXML
    private ObservableList<FilmFx> filmList = FXCollections.observableArrayList();
    @FXML
    private TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField, hasloTextField, haslo2TextField, filmTextField;
    @FXML
    private ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();

    private ObjectProperty<GatunekFx> gatunekFxObjectProperty = new SimpleObjectProperty<>();


    protected static final String LOGIN_FXML = "/logowanie.fxml";

    private static final String EDIT_KONTO_FXML = "/klientEditKlient.fxml";

    private static final String WYPOZYCZENIA_FXML = "/mojeWypozyczenia.fxml";

    @FXML
    private void updateKlientInDataBase(){
        KlientFx klientFx = new KlientFx();
        KontoFx kontoFx = new KontoFx();
        klientFx.setImie(imieTextField.getText());
        klientFx.setNazwisko(nazwiskoTextField.getText());
        klientFx.setEmail(emailTextField.getText());
        kontoFx.setLogin(loginTextField.getText());
        kontoFx.setHaslo(hasloTextField.getText());
        String haslo2 = haslo2TextField.getText();

        if(haslo2.equals(kontoFx.getHaslo()) == true ) {
            KlientDao klientDao = new KlientDao();
            KontoDao kontoDao = new KontoDao();
            klientDao.updateKlient(klientFx);
            kontoFx.setId(klientDao.findKlient(klientFx));
            kontoDao.updateKonto(kontoFx);
        }
    }
    @FXML
    public void initialize() throws SQLException {
        GatunekDao gatunekDao = new GatunekDao();
        gatunekList = gatunekDao.selectAll();
        gatunekListView.setItems(gatunekList);
    }
    @FXML
    public void findMovie() {
        filmListView.getItems().clear();
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
            else if(title.isEmpty()){
                filmList = FXCollections.observableList(filmFxList);
            }
        });
        filmListView.setItems(filmList);
    }
    @FXML
    private void findMovieByCategory(){
        filmListView.getItems().clear();
        FilmDao filmDao = new FilmDao();
        List<FilmFx> filmFxList = filmDao.selectAllFilm();

        GatunekFx gatunekFx = new GatunekFx();
        getGatunekFromListView(gatunekFx);
        System.out.println(gatunekFx.getId());
        filmFxList.forEach(e -> {
            if (e.getGatunekFx() == gatunekFx.getId())
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
    @FXML
    private void getGatunekFromListView(GatunekFx gatunekFx){
        gatunekFxObjectProperty.set(gatunekListView.getSelectionModel().getSelectedItem());
        gatunekFx.setId(gatunekFxObjectProperty.getValue().getId());
    }
    @FXML
    protected void logOut(ActionEvent actionEvent) {
        changeWindow(actionEvent, LOGIN_FXML);
    }

    public void viewBorrowed(ActionEvent actionEvent) {
        openWindow(WYPOZYCZENIA_FXML);
    }

    public void viewAccount(ActionEvent actionEvent) {
        KontoFx kontoFx = Singleton.getInstance().getKontoFx();
        KlientDao klientDao = new KlientDao();
        Singleton.getInstance().setKlientFx(klientDao.findKlientById(kontoFx.getKlientfx()));
        openWindow(EDIT_KONTO_FXML);
    }

    public void reserveMovie(ActionEvent actionEvent) {
    }
}
