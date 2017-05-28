package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.accessories.Converters;
import pl.accessories.Singleton;
import pl.bazadanych.dao.*;
import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class AdminController extends KlientController {


    private static final String EDIT_KONTO_FXML = "/klientEdit.fxml";

    private static final String EDIT_FILM_FXML = "/filmEdit.fxml";

    private static final String BORROW_FILM_FXML = "/wypozycz.fxml";

    @FXML
    protected ComboBox<GatunekFx> gatunekComboBox;
    @FXML
    protected ListView<KontoFx> kontoListView;
    @FXML
    protected ListView<FilmFx> filmListView;
    @FXML
    protected TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField, kontoTextField, nazwaTextField,
            rezyserTextField, filmTextField;
    @FXML
    protected PasswordField hasloPasswordField, haslo2PasswordField;
    @FXML
    protected TextArea opisTextArea;
    @FXML
    protected CheckBox adminCheckBox;
    @FXML
    protected DatePicker premieraDatePicker;
    @FXML
    protected Spinner<Integer> liczbaKopiSpinner;
    @FXML
    private ObservableList<KontoFx> kontoFxList = FXCollections.observableArrayList();

    private ObjectProperty<GatunekFx> gatunekFxObjectProperty = new SimpleObjectProperty<>();
    protected ObjectProperty<KontoFx> kontoFxObjectProperty = new SimpleObjectProperty<>();
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
        List<Konto> kontoList = kontoDao.selectAll();

        kontoList.forEach(e->{
            if(nazwa.equals(Integer.toString(e.getId())) || nazwa.equalsIgnoreCase(e.getLogin()))
            {
                KontoFx kontoFx = setKontoFx(e);
                kontoFxList.add(kontoFx);
            }
            else if(nazwa.isEmpty())
            {
                KontoFx kontoFx = setKontoFx(e);
                kontoFxList.add(kontoFx);            }
        });
        kontoListView.setItems(kontoFxList);
        kontoTextField.clear();
    }

    private KontoFx setKontoFx(Konto e){
        KontoFx kontoFx = new KontoFx();
        kontoFx.setId(e.getId());
        kontoFx.setLogin(e.getLogin());
        kontoFx.setHaslo(e.getHaslo());
        kontoFx.setAdmin(e.getAdmin());
        kontoFx.setKlientfx(e.getKlient());
        return kontoFx;
    }
    @FXML
    protected FilmFx getFilmValues() {
        FilmFx filmFx = new FilmFx();
        filmFx.setNazwa(nazwaTextField.getText());
        filmFx.setOpis(opisTextArea.getText());
        RezyserDao rezyserDao = new RezyserDao();

        int id = rezyserDao.findRezyser(rezyserTextField.getText());
        if( id == 0 )
            id = rezyserDao.findRezyser(rezyserTextField.getText());

        filmFx.setRezyserFx(id);

        gatunekFxObjectProperty.set(gatunekComboBox.getSelectionModel().getSelectedItem());
        filmFx.setGatunekFx(gatunekFxObjectProperty.getValue().getId());

        filmFx.setPremiera(Converters.toDate(premieraDatePicker.getValue()));

        filmFx.setIlosc(liczbaKopiSpinner.getValue());

        return filmFx;
    }
    @FXML
    private void addFilmToDataBase(){
        FilmFx filmFx = getFilmValues();
        clearFilmTextField();
        FilmDao filmDao = new FilmDao();
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();

        filmDao.insertFilm(filmFx);

        egzemplarzDao.insertEgzemplarz(filmFx.getIlosc(), filmDao.findFilm(filmFx.getNazwa()));

    }

    private void clearKlientTextField(){
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

    private void getKontoFromListView(KontoFx kontoFx){
        kontoFxObjectProperty.set(kontoListView.getSelectionModel().getSelectedItem());
        kontoFx.setId(kontoFxObjectProperty.getValue().getId());
        kontoFx.setLogin(kontoFxObjectProperty.getValue().getLogin());
        kontoFx.setHaslo(kontoFxObjectProperty.getValue().getHaslo());
        kontoFx.setKlientfx(kontoFxObjectProperty.getValue().getKlientfx());
    }

    private void getFilmFromListView(FilmFx filmFx){
        filmFxObjectProperty.set(filmListView.getSelectionModel().getSelectedItem());
        filmFx.setId(filmFxObjectProperty.getValue().getId());
        filmFx.setNazwa(filmFxObjectProperty.getValue().getNazwa());
        filmFx.setOpis(filmFxObjectProperty.getValue().getOpis());
        filmFx.setIlosc(filmFxObjectProperty.getValue().getIlosc());
        filmFx.setPremiera(filmFxObjectProperty.getValue().getPremiera());
        filmFx.setGatunekFx(filmFxObjectProperty.getValue().getGatunekFx());
        filmFx.setRezyserFx(filmFxObjectProperty.getValue().getRezyserFx());
    }
    @FXML
    private void deleteKonto(){
        KontoFx kontoFx = new KontoFx();

        getKontoFromListView(kontoFx);

        KontoDao kontoDao = new KontoDao();
        KlientDao klientDao = new KlientDao();

        kontoDao.deleteKonto(kontoFx);
        klientDao.deleteKlient(kontoFx.getKlientfx());

    }
    @FXML
    private void editKonto(){

        KontoFx kontoFx = new KontoFx();
        getKontoFromListView(kontoFx);
        Singleton.getInstance().setKontoFx(kontoFx);

        openWindow(EDIT_KONTO_FXML);
    }
    @FXML
    private void deleteFilm() {
        FilmFx filmFx = new FilmFx();

        getFilmFromListView(filmFx);

        FilmDao filmDao = new FilmDao();
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();

        egzemplarzDao.deleteEgzemplarzByIdFilmu(filmFx);
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
        FilmFx filmFx = new FilmFx();
        getFilmFromListView(filmFx);
        Singleton.getInstance().setFilmFx(filmFx);

        openWindow(BORROW_FILM_FXML);
    }

    public void initialize() throws SQLException {
        liczbaKopiSpinner.setValueFactory(valueFactory);
        setGatunekFxList();
        gatunekComboBox.setItems(gatunekFxList);
    }



}
