package pl.controllers;

import javafx.fxml.FXML;
import pl.tablesFx.FilmFx;
import pl.tablesFx.Singleton;

/**
 * Created by Mateusz on 2017-05-24.
 */
public class WypozyczController extends AdminController{

    private FilmFx filmFx;
    @FXML
    private void borrow(){


    }

    public void initialize(){
        this.filmFx = Singleton.getInstance().getFilmFx();

    }

}
