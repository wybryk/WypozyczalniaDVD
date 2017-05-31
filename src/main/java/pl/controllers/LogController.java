package pl.controllers;

/**
 * Created by Mateusz on 2017-04-22.
 */

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import pl.accessories.Converters;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tables.Konto;
import pl.accessories.Singleton;

import java.util.List;




public class LogController extends BaseController{

    private static final String KLIENT_FXML = "/klient.fxml";
    private static final String ADMIN_FXML = "/admin.fxml";

    @FXML
    private TextField loginText, hasloText;
    @FXML
    private AnchorPane ap;

    @FXML
    public void initialize() {
        this.ap.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                logIn(event);
                event.consume();
            }
        });
    }
    @FXML
    private void logIn(Event event) {
        String login, haslo;
        boolean exist = false;
        login = loginText.getText();
        haslo = hasloText.getText();
        KontoDao kontoDao = new KontoDao();

        List<Konto> kontoList = kontoDao.selectAll();

        for(Konto e : kontoList){
            //System.out.println(e);
            if(login.equals(e.getLogin()) && haslo.equals(e.getHaslo()))
            {
                if (e.getAdmin() == 1) {
                    changeWindow(event, ADMIN_FXML);
                    System.out.println("Zalogowano admin");
                }
                else if (e.getAdmin() == 0) {
                    Singleton.getInstance().setKontoFx(Converters.toKontoFx(e));
                    changeWindow(event, KLIENT_FXML);
                    System.out.println("Zalogowano klient");
                }
                exist = true;
                break;
            }
        }
        if ( exist == false )warningWindow("Błędna nazwa użytkownika lub hasło.");

    }


}
