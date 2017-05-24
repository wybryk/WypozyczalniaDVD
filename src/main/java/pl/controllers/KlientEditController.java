package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.bazadanych.dao.KlientDao;
import pl.bazadanych.dao.KontoDao;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;


/**
 * Created by Mateusz on 2017-05-03.
 */
public class KlientEditController extends AdminController{


    private KlientFx klientFx;
    private KontoFx kontoFx;

    @FXML
    private void updateKlientInDataBase(){

        KlientFx klientFx = new KlientFx();
        KontoFx kontoFx = new KontoFx();
        String haslo2 = getKlientValues(klientFx, kontoFx);
        kontoFx.setId(this.kontoFx.getId());
        klientFx.setId(this.klientFx.getId());

        if(haslo2.equals(kontoFx.getHaslo()) == true ) {
            KlientDao klientDao = new KlientDao();
            KontoDao kontoDao = new KontoDao();
            klientDao.updateKlient(klientFx);
            kontoDao.updateKonto(kontoFx);
        }
        clearKlientTextField();
    }

    @FXML
    public void initialize()  {
        this.kontoFx = AdminController.getInstance().getKontoFx();
        this.klientFx = AdminController.getInstance().getKlientFx();
    }

}
