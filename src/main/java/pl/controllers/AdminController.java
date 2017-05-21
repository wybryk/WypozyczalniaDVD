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

/**
 * Created by Mateusz on 2017-04-22.
 */
public class AdminController extends BaseController {

    private static AdminController instance = new AdminController();

    public static AdminController getInstance() {
        return instance;
    }

    private static final String EDIT_FXML = "/klientEdit.fxml";

    @FXML
    private ComboBox<GatunekFx> gatunekComboBox;
    @FXML
    private ListView<KontoFx> kontoListView;
    @FXML
    private TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField, kontoTextField, nazwaTextField,
            rezyserTextField;
    @FXML
    private PasswordField hasloPasswordField, haslo2PasswordField;
    @FXML
    private TextArea opisTextArea;
    @FXML
    private CheckBox adminCheckBox;
    @FXML
    private DatePicker premieraDatePicker;
    @FXML
    private Spinner<Integer> liczbaKopiSpinner;
    @FXML
    private ObservableList<KontoFx> kontoList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();

    private ObjectProperty<GatunekFx> gatunekFxObjectProperty = new SimpleObjectProperty<>();

    private ObjectProperty<KontoFx> kontoFxObjectProperty = new SimpleObjectProperty<>();

    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);

    private KontoFx kontoFx;
    private  KlientFx klientFx;

    public KontoFx getKontoFx() {
        return kontoFx;
    }

    public void setKontoFx(KontoFx kontoFx) {
        this.kontoFx = kontoFx;
    }

    public KlientFx getKlientFx() {
        return klientFx;
    }

    public void setKlientFx(KlientFx klientFx) {
        this.klientFx = klientFx;
    }

    @FXML
    private void addKlientToDataBase() {
        KlientFx klientFx = new KlientFx();
        KontoFx kontoFx = new KontoFx();

        klientFx.setImie(imieTextField.getText());
        klientFx.setNazwisko(nazwiskoTextField.getText());
        klientFx.setEmail(emailTextField.getText());
        kontoFx.setLogin(loginTextField.getText());
        kontoFx.setHaslo(hasloPasswordField.getText());
        String haslo2 = haslo2PasswordField.getText();

        if(adminCheckBox.isSelected())
            kontoFx.setAdmin(1);
        else kontoFx.setAdmin(0);

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
        });

        kontoListView.setItems(kontoList);
    }
    @FXML
    private Film getFilmValues() {
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
    @FXML
    private void clearKlientTextField(){
        imieTextField.clear();
        nazwiskoTextField.clear();
        emailTextField.clear();
        loginTextField.clear();
        hasloPasswordField.clear();
        haslo2PasswordField.clear();
    }
    @FXML
    private void clearFilmTextField(){
        nazwaTextField.clear();
        rezyserTextField.clear();
        opisTextArea.clear();
        gatunekComboBox.valueProperty().set(null);
        premieraDatePicker.setValue(null);
        valueFactory.setValue(1);
    }
    @FXML
    private void getKontoAndKlientFromListView(KontoFx kontoFx, KlientFx klientFx){
        kontoFxObjectProperty.set(kontoListView.getSelectionModel().getSelectedItem());
        kontoFx.setId(kontoFxObjectProperty.getValue().getId());
        klientFx.setId(kontoFxObjectProperty.getValue().getKlientfx());
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
        AdminController.getInstance().setKontoFx(kontoFx);
        AdminController.getInstance().setKlientFx(klientFx);

        openWindow(EDIT_FXML);
    }
    @FXML
    public void initialize() throws SQLException {
        GatunekDao gatunekDao = new GatunekDao();
        liczbaKopiSpinner.setValueFactory(valueFactory);
        gatunekList = gatunekDao.selectAll();
        gatunekComboBox.setItems(gatunekList);
    }



}
