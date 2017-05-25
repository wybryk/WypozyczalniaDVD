package pl.controllers;

import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;
import pl.tablesFx.Singleton;

/**
 * Created by Mateusz on 2017-05-24.
 */
public class KlientEditKlientController extends KlientEditController{

    @Override
    protected String getKlientValues(KlientFx klientFx, KontoFx kontoFx){

        klientFx.setImie(imieTextField.getText());
        klientFx.setNazwisko(nazwiskoTextField.getText());
        klientFx.setEmail(emailTextField.getText());
        kontoFx.setLogin(loginTextField.getText());
        kontoFx.setHaslo(hasloPasswordField.getText());
        return haslo2PasswordField.getText();
    }
}
