package pl.controllers;

import javafx.fxml.FXML;
import pl.bazadanych.dao.FilmDao;
import pl.bazadanych.dao.GatunekDao;
import pl.bazadanych.tables.Film;
import pl.tablesFx.FilmFx;

/**
 * Created by Mateusz on 2017-05-24.
 */
public class FilmEditController extends AdminController{

    private FilmFx filmFx;

    @FXML
    private void updateFilmToDataBase(){
        Film film = getFilmValues();
        film.setId(filmFx.getId());
        System.out.println(film);
        FilmDao filmDao = new FilmDao();
        //EgzemplarzDao egzemplarzDao = new EgzemplarzDao();

        filmDao.updateFilm(film);

        //egzemplarzDao.insertEgzemplarz(film.getIlosc(), filmDao.findFilm(film.getNazwa()));
    }
    @FXML
    public void initialize()  {
        this.filmFx = AdminController.getInstance().getFilmFx();
        GatunekDao gatunekDao = new GatunekDao();
        liczbaKopiSpinner.setValueFactory(valueFactory);
        gatunekList = gatunekDao.selectAll();
        gatunekComboBox.setItems(gatunekList);
    }
}
