package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.bazadanych.Connection;
import pl.bazadanych.dao.*;
import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Mateusz on 2017-04-22.
 */
public class AdminController extends KlientController {

    @FXML
    private ComboBox<GatunekFx> gatunekComboBox;
    @FXML
    private ListView<KontoFx> kontoListView;
    //@FXML private TabableView klientTableView;
   // @FXML TableColumn<KlientFx, String> idColumn;
    @FXML
    private TextField imie, nazwisko, email, login, haslo, haslo2, kontoTextField, nazwaTextField, rezyserTextField;
    @FXML
    private TextArea opisTextArea;
    @FXML
    private CheckBox adminCheckBox;
    @FXML
    private DatePicker premieraDatePicker;
    @FXML
    private Spinner<Integer> liczbaKopiSpinner;
    @FXML
    private ObservableList<KlientFx> klientList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<RezyserFx> rezyserFxList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<KontoFx> kontoList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<FilmFx> filmFxList = FXCollections.observableArrayList();

    private ObjectProperty<GatunekFx> gatunekFxObjectProperty = new SimpleObjectProperty<>();

    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);

    @FXML
    private void addKlientToDataBase() {
        Klient klient = new Klient();
        Konto konto = new Konto();
        klient.setImie(imie.getText());
        klient.setNazwisko(nazwisko.getText());
        klient.setEmail(email.getText());
        konto.setLogin(login.getText());
        konto.setHaslo(haslo.getText());
        String hasloString = haslo2.getText();
        konto.setAdmin(adminCheckBox.isSelected());
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

    @FXML
    private void kontoButton() {
        Connection.initDataBase();
        String nazwa = kontoTextField.getText();
        KontoDao kontoDao = new KontoDao(Connection.getConnectionSource());

        List<Konto> konto = kontoDao.queryforAll(Konto.class);
        konto.forEach(e->{
            KontoFx kontoFx = new KontoFx();
            if(nazwa.equals(e.getId()) || nazwa.equalsIgnoreCase(e.getLogin()))
            {
                kontoFx.setId(e.getId());
                kontoFx.setLogin(e.getLogin());
                kontoFx.setHaslo(e.getHaslo());
                kontoFx.setAdmin(e.isAdmin());

                kontoList.add(kontoFx);
            }
        });
        Connection.disconnect();
        kontoListView.setItems(kontoList);
    }

    private KlientFx findKlientById(int id) {

        Connection.initDataBase();
        KlientDao klientDao = new KlientDao(Connection.getConnectionSource());

        Klient klient = klientDao.findById(Klient.class, id);

        KlientFx klientFx = convertKlientFx(klient);

        return klientFx;
    }

    private KlientFx convertKlientFx(Klient klient){
        KlientFx klientFx = new KlientFx();
        klientFx.setId(klient.getId());
        klientFx.setImie(klient.getImie());
        klientFx.setNazwisko(klient.getNazwisko());
        klientFx.setEmail(klient.getEmail());
        return klientFx;
    }

    @FXML
    private Film getFilmValues() {
        Film film = new Film();
        film.setNazwa(nazwaTextField.getText());
        film.setOpis(opisTextArea.getText());

        int id = findRezyser(rezyserTextField.getText());
        RezyserDao rezyserDao = new RezyserDao(Connection.getConnectionSource());
        Rezyser rezyser = rezyserDao.findById(Rezyser.class, id);
        film.setRezyser(rezyser);

        gatunekFxObjectProperty.set(gatunekComboBox.getSelectionModel().getSelectedItem());
        GatunekDao gatunekDao = new GatunekDao(Connection.getConnectionSource());
        Gatunek gatunek = gatunekDao.findById(Gatunek.class, gatunekFxObjectProperty.getValue().getId());
        film.setGatunek(gatunek);

        film.setPremiera(convertDate(premieraDatePicker.getValue()));

        film.setIlosc(liczbaKopiSpinner.getValue());

        return film;
    }

    @FXML
    private void addFilmToDataBase(){
        Film film = getFilmValues();
        Connection.initDataBase();
        FilmDao filmDao = new FilmDao(Connection.getConnectionSource());
        filmDao.createOrUpdateTabel(film);
        Connection.disconnect();

        addEgzemplarzToDataBase(film.getIlosc(), film);
    }

    private List<Rezyser> getRezyserFromDataBase(){
        Connection.initDataBase();
        RezyserDao rezyserDao = new RezyserDao(Connection.getConnectionSource());

        List<Rezyser> rezyser = rezyserDao.queryforAll(Rezyser.class);
       /* rezyser.forEach(e->{
            RezyserFx rezyserFx = new RezyserFx();
            rezyserFx.setId(e.getId());
            rezyserFx.setNazwa(e.getNazwa());
            rezyserFxList.add(rezyserFx);
        });*/
        Connection.disconnect();
        return rezyser;

    }

    private int findRezyser( String rezyserNazwa){
        List<Rezyser> rezyserList = getRezyserFromDataBase();
        Rezyser rezyser = new Rezyser();
        boolean exist = false;
        int id = 0;

        for(Rezyser e : rezyserList){
            if(rezyserNazwa.equals(e.getNazwa())){
                exist = true;
                id = e.getId();
                break;
            }
            else{
                continue;
            }
        }
        if( exist == false)
            id = addRezyserToDataBase(rezyserNazwa);

        return id;
    }

    private int addRezyserToDataBase(String rezyserNazwa) {
        Connection.initDataBase();
        Rezyser rezyser = new Rezyser();
        rezyser.setNazwa(rezyserNazwa);
        RezyserDao rezyserDao = new RezyserDao(Connection.getConnectionSource());
        rezyserDao.createOrUpdateTabel(rezyser);
        Connection.disconnect();
        return rezyser.getId();
    }

    private Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date convertDate(LocalDate localDate) {
        Date date = asDate(localDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
        System.out.println("formatedDate : " + formatedDate);

        try {
            date = format.parse(formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private void getFilmFromDataBase(){
        Connection.initDataBase();
        FilmDao filmDao = new FilmDao(Connection.getConnectionSource());

        List<Film> filmList = filmDao.queryforAll(Film.class);

        filmList.forEach(e->{
            FilmFx filmFx = new FilmFx();
            filmFx.setId(e.getId());
            filmFx.setNazwa(e.getNazwa());
            filmFxList.add(filmFx);
        });

        Connection.disconnect();
    }

    private int findFilm(String nazwa){
        getFilmFromDataBase();
        int id = 0;
        for(FilmFx e: filmFxList){
            if( nazwa.equals(e.getNazwa())) {
                id = e.getId();
                break;
            }
        }
        return id;
    }

    private void addEgzemplarzToDataBase(int n, Film film ){
        Egzemplarz egzemplarz = new Egzemplarz();

        egzemplarz.setFilm(film);
        Connection.initDataBase();
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao(Connection.getConnectionSource());
        for(int i = 0; i < n; i++)
            egzemplarzDao.createTabel(egzemplarz);
        Connection.disconnect();
    }


    @FXML
    public void initialize() throws SQLException {
        liczbaKopiSpinner.setValueFactory(valueFactory);
        saveGatunek();
        gatunekComboBox.setItems(gatunekFxList);
    }

}
