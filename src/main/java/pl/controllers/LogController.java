package pl.controllers;

/**
 * Created by Mateusz on 2017-04-22.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.bazadanych.Connection;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tables.Konto;

import java.util.List;


public class LogController extends BaseController{

    private static final String KLIENT_FXML = "/klient.fxml";
    private static final String ADMIN_FXML = "/admin.fxml";

    @FXML
    private TextField loginText, hasloText;

    @FXML
    private void logIn(ActionEvent event)
    {
        String login, haslo;
        login = loginText.getText();
        haslo = hasloText.getText();

        Connection.initDataBase();
        KontoDao kontoDao = new KontoDao(Connection.getConnectionSource());

        List<Konto> konto = kontoDao.queryforAll(Konto.class);
        konto.forEach(e->{
            //System.out.println(e);
            if(login.equals(e.getLogin()) && haslo.equals(e.getHaslo()))
            {
                if(e.isAdmin()==true) {
                    changeWindow(event, ADMIN_FXML);
                    //System.out.println("Zalogowano admin");
                }
                else {
                    changeWindow(event, KLIENT_FXML);
                    //System.out.println("Zalogowano klient");
                }
            }
        });
    }
}
