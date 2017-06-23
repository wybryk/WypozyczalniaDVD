package pl.controllers;

/**
 * <h2>Klasa kontrolera widoku logowania.</h2>
 * <p>Zawiera metody potrzebne do obsługi widoku logowania.</p>
 */

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import pl.accessories.Converters;
import pl.accessories.LoggerClass;
import pl.bazadanych.dao.KontoDao;
import pl.bazadanych.tables.Konto;
import pl.accessories.Singleton;

import java.util.List;





public class LogController extends BaseController{

    private static final String KLIENT_FXML = "/klient.fxml";
    private static final String ADMIN_FXML = "/admin.fxml";

    private LoggerClass logger = new LoggerClass();

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
    /**
     * Metoda loguje do odpowiedniego okna w zależności od wprowadzonych danych logowania.
     * @param event obiekt typu Event
     */
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
                    logger.logFileAndConsole("debug", "Zalogowano Admin");
                }
                else if (e.getAdmin() == 0) {
                    Singleton.getInstance().setKontoFx(Converters.toKontoFx(e));
                    changeWindow(event, KLIENT_FXML);
                    logger.logFileAndConsole("debug", "Zalogowano Klienta");
                }
                exist = true;
                break;
            }
        }
        if ( exist == false ) {
            logger.logFileAndConsole("info", "Nieudane logowanie.");
            warningWindow("Błędna nazwa użytkownika lub hasło.");
        }

    }


}
