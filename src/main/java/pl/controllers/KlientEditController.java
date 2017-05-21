package pl.controllers;

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
public class KlientEditController extends BaseController{

    @FXML
    private TextField imieTextField, nazwiskoTextField, emailTextField, loginTextField;
    @FXML
    private PasswordField hasloPasswordField, haslo2PasswordField;
    @FXML
    private CheckBox adminCheckBox;

    private KlientFx klientFx;
    private KontoFx kontoFx;

    @FXML
    private void updateKlientInDataBase(){

        KlientFx klientFx = new KlientFx();
        KontoFx kontoFx = new KontoFx();

        klientFx.setId(this.klientFx.getId());
        klientFx.setImie(imieTextField.getText());
        klientFx.setNazwisko(nazwiskoTextField.getText());
        klientFx.setEmail(emailTextField.getText());
        kontoFx.setId(this.kontoFx.getId());
        kontoFx.setLogin(loginTextField.getText());
        kontoFx.setHaslo(hasloPasswordField.getText());
        String haslo2 = haslo2PasswordField.getText();
        if(adminCheckBox.isSelected())
            kontoFx.setAdmin(1);
        else kontoFx.setAdmin(0);

        if(haslo2.equals(kontoFx.getHaslo()) == true ) {
            KlientDao klientDao = new KlientDao();
            KontoDao kontoDao = new KontoDao();
            klientDao.updateKlient(klientFx);
            kontoDao.updateKonto(kontoFx);
        }
        clearKlientTextField();
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
    public void initialize()  {
        this.kontoFx = Singleton.getInstance().getKontoFx();
        this.klientFx = Singleton.getInstance().getKlientFx();
    }

}
