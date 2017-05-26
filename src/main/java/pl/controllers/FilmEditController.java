package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SpinnerValueFactory;
import pl.accessories.Converters;
import pl.bazadanych.dao.EgzemplarzDao;
import pl.bazadanych.dao.FilmDao;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.dao.RezyserDao;
import pl.bazadanych.tables.Film;
import pl.bazadanych.tables.Rezyser;
import pl.tablesFx.FilmFx;
import pl.tablesFx.GatunekFx;
import pl.tablesFx.RezyserFx;
import pl.tablesFx.Singleton;

/**
 * Created by Mateusz on 2017-05-24.
 */
public class FilmEditController extends AdminController{

    @FXML
    protected ObservableList<GatunekFx> gatunekFxList = FXCollections.observableArrayList();
    private FilmFx filmFx;

    @FXML
    private void updateFilmToDataBase(){
        FilmFx filmFx = getFilmValues();
        filmFx.setId(this.filmFx.getId());
        FilmDao filmDao = new FilmDao();
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();

        filmDao.updateFilm(filmFx);

        if(this.filmFx.getIlosc() < filmFx.getIlosc())
            egzemplarzDao.insertEgzemplarz(filmFx.getIlosc()-this.filmFx.getIlosc(), filmDao.findFilm(filmFx.getNazwa()));
        else if(this.filmFx.getIlosc() > filmFx.getIlosc()) {
            //egzemplarzDao.deleteEgzemplarz(filmFx);
        }
    }
    private void setTextFields(){
        nazwaTextField.setText(filmFx.getNazwa());
        opisTextArea.setText(filmFx.getOpis());
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, filmFx.getIlosc());
        liczbaKopiSpinner.setValueFactory(valueFactory);
        premieraDatePicker.setValue(Converters.toLocalDate(filmFx.getPremiera()));
        gatunekComboBox.getSelectionModel().select(filmFx.getGatunekFx()-1);
        rezyserTextField.setText(findRezyser(filmFx.getRezyserFx()).getNazwa());

    }
    private RezyserFx findRezyser(int id){
        Rezyser rezyser = new Rezyser();
        RezyserDao rezyserDao = new RezyserDao();
        rezyser = rezyserDao.findRezyserById(id);
        return Converters.toRezyserFx(rezyser);
    }
    @FXML
    public void initialize()  {
        this.filmFx = Singleton.getInstance().getFilmFx();
        liczbaKopiSpinner.setValueFactory(valueFactory);
        setGatunekFxList(gatunekFxList);
        gatunekComboBox.setItems(gatunekFxList);
        setTextFields();
    }
}
