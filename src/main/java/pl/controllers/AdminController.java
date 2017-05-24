package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.bazadanych.dao.*;
import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import javafx.event.ActionEvent;

import javax.script.Bindings;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class AdminController extends KlientController {


    private static final String EDIT_KONTO_FXML = "/klientEdit.fxml";

    private static final String EDIT_FILM_FXML = "/filmEdit.fxml";

    @FXML
    protected ComboBox<GatunekFx> gatunekComboBox;
    @FXML
    private ListView<KontoFx> kontoListView;
    @FXML
    protected ListView<FilmFx> filmListView;
    @FXML
    protected TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField, kontoTextField, nazwaTextField,
            rezyserTextField, filmTextField;
    @FXML
    protected PasswordField hasloPasswordField, haslo2PasswordField;
    @FXML
    private TextArea opisTextArea;
    @FXML
    protected CheckBox adminCheckBox;
    @FXML
    protected DatePicker premieraDatePicker;
    @FXML
    protected Spinner<Integer> liczbaKopiSpinner;
    @FXML
    private ObservableList<KontoFx> kontoList = FXCollections.observableArrayList();
    @FXML
    protected ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<FilmFx> filmList = FXCollections.observableArrayList();
    private ObjectProperty<GatunekFx> gatunekFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<KontoFx> kontoFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<FilmFx> filmFxObjectProperty = new SimpleObjectProperty<>();
    protected SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);


    protected String getKlientValues(KlientFx klientFx, KontoFx kontoFx){

        if(adminCheckBox.isSelected())
            kontoFx.setAdmin(1);
        else
            kontoFx.setAdmin(0);

        klientFx.setImie(imieTextField.getText());
        klientFx.setNazwisko(nazwiskoTextField.getText());
        klientFx.setEmail(emailTextField.getText());
        kontoFx.setLogin(loginTextField.getText());
        kontoFx.setHaslo(hasloPasswordField.getText());
        return haslo2PasswordField.getText();
    }
    @FXML
    private void addKlientToDataBase() {
        KlientFx klientFx = new KlientFx();
        KontoFx kontoFx = new KontoFx();
        String haslo2 = getKlientValues(klientFx, kontoFx);

        if(haslo2.equals(kontoFx.getHaslo()) == true ) {
            KlientDao klientDao = new KlientDao();
            KontoDao kontoDao = new KontoDao();
            klientDao.insertKlient(klientFx);
            int id = klientDao.findKlient(klientFx);
            kontoDao.insertKonto(kontoFx, id);
        }
        clearKlientTextField();
    }
    @FXML
    private void findKontoButton() {
        kontoListView.getItems().clear();
        String nazwa = kontoTextField.getText();
        KontoDao kontoDao = new KontoDao();
        List<KontoFx> kontoFxList = kontoDao.selectAll();

        kontoFxList.forEach(e->{
            if(nazwa.equals(Integer.toString(e.getId())) || nazwa.equalsIgnoreCase(e.getLogin()))
            {
                KontoFx kontoFx = new KontoFx();
                kontoFx.setId(e.getId());
                kontoFx.setLogin(e.getLogin());
                kontoFx.setHaslo(e.getHaslo());
                kontoFx.setAdmin(e.getAdmin());
                kontoFx.setKlientfx(e.getKlientfx());

                kontoList.add(kontoFx);
            }
            else if(nazwa.isEmpty())
            {
                kontoList = FXCollections.observableList(kontoFxList);
            }
        });
        kontoListView.setItems(kontoList);
        kontoTextField.clear();
    }
    @FXML
    protected Film getFilmValues() {
        Film film = new Film();
        film.setNazwa(nazwaTextField.getText());
        film.setOpis(opisTextArea.getText());
        RezyserDao rezyserDao = new RezyserDao();

        int id = rezyserDao.findRezyser(rezyserTextField.getText());
        if( id == 0 )
            id = rezyserDao.findRezyser(rezyserTextField.getText());

        film.setRezyser(id);

        gatunekFxObjectProperty.set(gatunekComboBox.getSelectionModel().getSelectedItem());
        film.setGatunek(gatunekFxObjectProperty.getValue().getId());

        film.setPremiera(premieraDatePicker.getValue());

        film.setIlosc(liczbaKopiSpinner.getValue());

        clearFilmTextField();

        return film;
    }
    @FXML
    private void addFilmToDataBase(){
        Film film = getFilmValues();
        FilmDao filmDao = new FilmDao();
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();

        filmDao.insertFilm(film);

        egzemplarzDao.insertEgzemplarz(film.getIlosc(), filmDao.findFilm(film.getNazwa()));

    }

    protected void clearKlientTextField(){
        imieTextField.clear();
        nazwiskoTextField.clear();
        emailTextField.clear();
        loginTextField.clear();
        hasloPasswordField.clear();
        haslo2PasswordField.clear();
    }

    private void clearFilmTextField(){
        nazwaTextField.clear();
        rezyserTextField.clear();
        opisTextArea.clear();
        gatunekComboBox.valueProperty().set(null);
        premieraDatePicker.setValue(null);
        valueFactory.setValue(1);
    }

    private void getKontoAndKlientFromListView(KontoFx kontoFx, KlientFx klientFx){
        kontoFxObjectProperty.set(kontoListView.getSelectionModel().getSelectedItem());
        kontoFx.setId(kontoFxObjectProperty.getValue().getId());
        klientFx.setId(kontoFxObjectProperty.getValue().getKlientfx());
    }

    private void getFilmFromListView(FilmFx filmFx){
        filmFxObjectProperty.set(filmListView.getSelectionModel().getSelectedItem());
        filmFx.setId(filmFxObjectProperty.getValue().getId());
    }
    @FXML
    private void deleteKonto(){
        KontoFx kontoFx = new KontoFx();
        KlientFx klientFx = new KlientFx();

        getKontoAndKlientFromListView(kontoFx, klientFx);

        KontoDao kontoDao = new KontoDao();
        KlientDao klientDao = new KlientDao();

        kontoDao.deleteKonto(kontoFx);
        //klientDao.deleteKlient(klientFx);

    }
    @FXML
    private void editKonto(){

        KlientFx klientFx = new KlientFx();
        KontoFx kontoFx = new KontoFx();
        getKontoAndKlientFromListView(kontoFx, klientFx);
        Singleton.getInstance().setKontoFx(kontoFx);
        Singleton.getInstance().setKlientFx(klientFx);

        openWindow(EDIT_KONTO_FXML);
    }

    public void findFilm() {
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
    private void deleteFilm() {
        FilmFx filmFx = new FilmFx();

        getFilmFromListView(filmFx);

        FilmDao filmDao = new FilmDao();
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();

        egzemplarzDao.deleteEgzemplarz(filmFx);
        filmDao.deleteFilm(filmFx);

    }
    @FXML
    private void editFilm() {
        FilmFx filmFx = new FilmFx();
        getFilmFromListView(filmFx);
        Singleton.getInstance().setFilmFx(filmFx);

        openWindow(EDIT_FILM_FXML);
    }
    @FXML
    private void borrowFilm() {

    }
    @FXML
    public void initialize() throws SQLException {
        GatunekDao gatunekDao = new GatunekDao();
        liczbaKopiSpinner.setValueFactory(valueFactory);
        gatunekList = gatunekDao.selectAll();
        gatunekComboBox.setItems(gatunekList);
    }



}
