package pl.controllers;

/**
 * Created by Mateusz on 2017-04-22.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.bazadanych.dao.KontoDao;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;
import pl.tablesFx.Singleton;

import java.util.List;


public class LogController extends BaseController{

    private static final String KLIENT_FXML = "/klient.fxml";
    private static final String ADMIN_FXML = "/admin.fxml";

    @FXML
    private TextField loginText, hasloText;

    @FXML
    private void logIn(ActionEvent event) {
        String login, haslo;
        login = loginText.getText();
        haslo = hasloText.getText();
        KontoDao kontoDao = new KontoDao();

        List<KontoFx> kontoList = kontoDao.selectAll();

        for(KontoFx e : kontoList){
            //System.out.println(e);
            if(login.equals(e.getLogin()) && haslo.equals(e.getHaslo()))
            {
                if (e.getAdmin() == 1) {
                    changeWindow(event, ADMIN_FXML);
                    System.out.println("Zalogowano admin");
                }
                else if (e.getAdmin() == 0) {
                    Singleton.getInstance().setKontoFx(e);
                    changeWindow(event, KLIENT_FXML);
                    System.out.println("Zalogowano klient");
                }
                break;
            }
        }
    }


}
