package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pl.accessories.Converters;
import pl.bazadanych.dao.KlientDao;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tables.Klient;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;
import pl.tablesFx.Singleton;

import java.util.List;


/**
 * Created by Mateusz on 2017-05-03.
 */
public class KlientEditController extends AdminController{


    protected KlientFx klientFx;
    protected KontoFx kontoFx;

    @FXML
    protected void updateKlientInDataBase(){

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
    }
    protected void setTextFields(){
        imieTextField.setText(this.klientFx.getImie());
        nazwiskoTextField.setText(this.klientFx.getNazwisko());
        emailTextField.setText(this.klientFx.getEmail());
        loginTextField.setText(this.kontoFx.getLogin());
        hasloPasswordField.setText(this.kontoFx.getHaslo());
        haslo2PasswordField.setText(this.kontoFx.getHaslo());
    }
    protected void getKlientFromDataBase(){
        KlientDao klientDao = new KlientDao();
        this.klientFx = Converters.toKlientFx(klientDao.findKlientById(this.kontoFx.getKlientfx()));
    }

    @FXML
    public void initialize()  {
        this.kontoFx = Singleton.getInstance().getKontoFx();
        getKlientFromDataBase();
        setTextFields();
    }

}
