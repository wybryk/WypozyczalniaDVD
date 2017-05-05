package pl.controllers;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.bazadanych.Connections;
import pl.bazadanych.dao.*;
import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class AdminController extends BaseController {

    private static final String EDIT_FXML = "/klientEdit.fxml";

    @FXML
    private ComboBox<GatunekFx> gatunekComboBox;
    @FXML
    private ListView<KontoFx> kontoListView;
    //@FXML private TabableView klientTableView;
    // @FXML TableColumn<Klient, String> idColumn;
    @FXML
    private TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField, hasloTextField, haslo2TextField,
            kontoTextField, nazwaTextField, rezyserTextField;
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
    protected ObservableList<GatunekFx> gatunekList = FXCollections.observableArrayList();

    private ObjectProperty<GatunekFx> gatunekFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<KontoFx> kontoFxObjectProperty = new SimpleObjectProperty<>();

    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);

    @FXML
    private void addKlientToDataBase() {
        Klient klient = new Klient();
        Konto konto = new Konto();

        klient.setImie(imieTextField.getText());
        klient.setNazwisko(nazwiskoTextField.getText());
        klient.setEmail(emailTextField.getText());
        konto.setLogin(loginTextField.getText());
        konto.setHaslo(hasloTextField.getText());
        String haslo2 = haslo2TextField.getText();

        if(adminCheckBox.isSelected())
            konto.setAdmin(1);
        else konto.setAdmin(0);

        if(haslo2.equals(konto.getHaslo()) == true ) {
            KlientDao klientDao = new KlientDao();
            KontoDao kontoDao = new KontoDao();
            klientDao.insertKlient(klient);
            int id = klientDao.findKlient(klient);
            kontoDao.insertKonto(konto, id);
        }
    }

    @FXML
    private void findKontoButton() {

        String nazwa = kontoTextField.getText();
        KontoDao kontoDao = new KontoDao();
        List<KontoFx> kontoFxList = kontoDao.selectAll();

        kontoFxList.forEach(e->{
            if(nazwa.equals(e.getId()) || nazwa.equalsIgnoreCase(e.getLogin()))
            {
                KontoFx kontoFx = new KontoFx();
                kontoFx.setId(e.getId());
                kontoFx.setLogin(e.getLogin());
                kontoFx.setHaslo(e.getHaslo());
                kontoFx.setAdmin(e.getAdmin());

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

        System.out.println(film);

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
    private void deleteKontoButton(){
        Konto konto = new Konto();
        Klient klient = new Klient();
        kontoFxObjectProperty.set(kontoListView.getSelectionModel().getSelectedItem());
        konto.setId(kontoFxObjectProperty.getValue().getId());
        klient.setId(kontoFxObjectProperty.getValue().getKlientfx());

        KontoDao kontoDao = new KontoDao();
        KlientDao klientDao = new KlientDao();

        kontoDao.deleteKonto(konto);
        klientDao.deleteKlient(klient);
    }

    @FXML
    private void editKontoButton(){

    }
    @FXML
    public void initialize() throws SQLException {
        GatunekDao gatunekDao = new GatunekDao();
        liczbaKopiSpinner.setValueFactory(valueFactory);
        gatunekList = gatunekDao.selectAll();
        gatunekComboBox.setItems(gatunekList);
    }

}
