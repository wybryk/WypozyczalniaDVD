package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.*;

import pl.accessories.Converters;
import pl.accessories.LoggerClass;
import pl.accessories.Singleton;
import pl.bazadanych.dao.FilmDao;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.dao.KlientDao;
import pl.bazadanych.dao.RezerwacjaDao;
import pl.bazadanych.tables.Film;
import pl.bazadanych.tables.Gatunek;
import pl.tablesFx.*;


import java.sql.SQLException;
import java.util.List;
import java.util.regex.*;

/**
 * <h2>Klasa kontrolera widoku klienta.</h2>
 * <p>Zawiera metody potrzebne do obsługi widoku klienta.</p>
 */
public class KlientController extends BaseController{
    @FXML
    private ListView<GatunekFx> gatunekListView;
    @FXML
    private ListView<FilmFx> filmListView;
    @FXML
    private ObservableList<FilmFx> filmFxList = FXCollections.observableArrayList();
    @FXML
    private TextField filmTextField;
    @FXML
    protected ObservableList<GatunekFx> gatunekFxList = FXCollections.observableArrayList();

    private ObjectProperty<GatunekFx> gatunekFxObjectProperty = new SimpleObjectProperty<>();

    private static final String EDIT_KONTO_FXML = "/klientEditKlient.fxml";

    private static final String WYPOZYCZENIA_FXML = "/mojeWypozyczenia.fxml";

    private static final String LOGIN_FXML = "/logowanie.fxml";

    private ObjectProperty<FilmFx> filmFxObjectProperty = new SimpleObjectProperty<>();

    protected LoggerClass logger = new LoggerClass();

    @FXML
    public void initialize() throws SQLException {
        setGatunekFxList();
        gatunekListView.setItems(gatunekFxList);
    }
    /**
     * Metoda tworzy listę gatunków filmowych.
     */
    protected void setGatunekFxList(){
        GatunekDao gatunekDao = new GatunekDao();
        List<Gatunek> gatunekList = gatunekDao.selectAll();
        gatunekList.forEach(e->{
            GatunekFx gatunekFx = new GatunekFx();
            gatunekFx.setId(e.getId());
            gatunekFx.setNazwa(e.getNazwa());
            gatunekFxList.add(gatunekFx);
        });
    }
    /**
     * Metoda wyszukuje film w bazie filmów przy użyciu wyrażenia regularnego.
     */
    @FXML
    protected void findMovie() {
        filmListView.getItems().clear();
        String title = filmTextField.getText();
        FilmDao filmDao = new FilmDao();
        List<Film> filmList = filmDao.selectAllFilm();
        filmList.forEach(e -> {
            if (Pattern.matches("[a-zA-Z0-9\\s]*" + title + "[a-zA-Z0-9\\s]*", e.getNazwa())) {
                FilmFx filmFx = setFilmFx(e);
                filmFxList.add(filmFx);
            }
            else if (title.isEmpty()) {
                FilmFx filmFx = setFilmFx(e);
                filmFxList.add(filmFx);
            }
        });
        logger.logFileAndConsole("info", "Wyszukano film po nazwie.");
        filmListView.setItems(filmFxList);
        //czyscimy liste przy kazdym wywolaniu
        filmList.clear();
    }
    /**
     * Metoda wyszukuje filmy w bazie filmów po kategorii.
     */
    @FXML
    private void findMovieByCategory(){
        filmListView.getItems().clear();
        FilmDao filmDao = new FilmDao();
        List<Film> filmList = filmDao.selectAllFilm();

        GatunekFx gatunekFx = new GatunekFx();
        getGatunekFromListView(gatunekFx);
        filmList.forEach(e -> {
            if (e.getGatunek() == gatunekFx.getId())
            {
                FilmFx filmFx = setFilmFx(e);
                filmFxList.add(filmFx);
            }
        });
        filmListView.setItems(filmFxList);
        logger.logFileAndConsole("info", "Wyszukano film po kategorii.");
    }
    /**
     * Metoda tworzy obiekt typu FilmFX
     * @param e obiekt typu Film
     * @return obiekt typu FilmFx
     */
    protected FilmFx setFilmFx(Film e){
        FilmFx filmFx = new FilmFx();
        filmFx.setId(e.getId());
        filmFx.setNazwa(e.getNazwa());
        filmFx.setIlosc(e.getIlosc());
        filmFx.setOpis(e.getOpis());
        filmFx.setPremiera(e.getPremiera());
        filmFx.setGatunekFx(e.getGatunek());
        filmFx.setRezyserFx(e.getRezyser());
        return filmFx;
    }
    /**
     * Metoda pobiera obiekt typu GatunekFx z listy gatunków.
     * @param gatunekFx obiekt typu GatunekFx
     */
    @FXML
    private void getGatunekFromListView(GatunekFx gatunekFx){
        gatunekFxObjectProperty.set(gatunekListView.getSelectionModel().getSelectedItem());
        gatunekFx.setId(gatunekFxObjectProperty.getValue().getId());
    }
    /**
     * Metoda wylogowuje z okna klienta.
     * @param actionEvent obiekt typu ActionEvent
     */
    @FXML
    protected void logOut(ActionEvent actionEvent) {
        logger.logFileAndConsole("info", "Wylogowanie.");
        changeWindow(actionEvent, LOGIN_FXML);
    }
    /**
     * Metoda wyświetla okno z wypozyczeniami danego klienta.
     * @param actionEvent obiekt typu ActionEvent
     */
    public void viewBorrowed(ActionEvent actionEvent) {
        openWindow(WYPOZYCZENIA_FXML);
    }
    /**
     * Metoda wyświetla okno z danymi klienta.
     * @param actionEvent obiekt typu ActionEvent
     */
    public void viewAccount(ActionEvent actionEvent) {
        KontoFx kontoFx = Singleton.getInstance().getKontoFx();
        KlientDao klientDao = new KlientDao();
        Singleton.getInstance().setKlientFx(Converters.toKlientFx(klientDao.findKlientById(kontoFx.getKlientfx())));
        openWindow(EDIT_KONTO_FXML);
    }
    /**
     * Metoda pobiera obiekt typu FilmFx z listy filmów.
     * @param filmFx obiekt typu FilmFx
     */
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
    /**
     * Metoda rezerwuje film dla danego klienta.
     * @param actionEvent obiekt typu ActionEvent
     */
    public void reserveMovie(ActionEvent actionEvent) {
        FilmFx filmFx = new FilmFx();
        getFilmFromListView(filmFx);
        //
        KontoFx kontoFx = Singleton.getInstance().getKontoFx();
        int klientID = kontoFx.getKlientfx();

        RezerwacjaDao rezerwacjaDao = new RezerwacjaDao();
        rezerwacjaDao.insertRezerwacje(filmFx, klientID);
        //
        logger.logFileAndConsole("info", "Klient stworzyl nowa rezerwacje.");
        warningWindow("Dodano rezerwację.");
    }
}
