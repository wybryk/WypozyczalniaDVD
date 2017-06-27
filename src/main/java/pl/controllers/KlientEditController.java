package pl.controllers;

import javafx.fxml.FXML;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;
import pl.accessories.Singleton;

import static pl.accessories.Converters.toKlient;
import static pl.accessories.Converters.toKlientFx;
import static pl.accessories.Converters.toKonto;


/**
 * <h2>Klasa kontrolera widoku edycji danych klienta.</h2>
 * <p>Zawiera metody potrzebne do obsługi widoku edycji danych klienta.</p>
 */
public class KlientEditController extends AdminController{


    protected KlientFx klientFx;
    protected KontoFx kontoFx;
    /**
     * Metoda aktualizuje dane klienta w bazie danych.
     */
    @FXML
    protected void updateKlientInDataBase(){

        KlientFx klientFx = new KlientFx();
        KontoFx kontoFx = new KontoFx();
        String haslo2 = getKlientValues(klientFx, kontoFx);
        kontoFx.setId(this.kontoFx.getId());
        klientFx.setId(this.klientFx.getId());

        if(haslo2.equals(kontoFx.getHaslo()) == true ) {
            wypozyczalniaClient("Klient", "update", toKlient(klientFx));
            wypozyczalniaClient("Konto", "update", toKonto(kontoFx));
            logger.logFileAndConsole("info", "Zaktualizowano dane klienta w BD.");
        }
        else {
            logger.logFileAndConsole("debug", "Klient nie mogl zaktualizowac danych w BD.");
        }
    }
    /**
     * Metoda wypełnia pola danymi klienta w widoku edycji.
     */
    protected void setTextFields(){
        imieTextField.setText(this.klientFx.getImie());
        nazwiskoTextField.setText(this.klientFx.getNazwisko());
        emailTextField.setText(this.klientFx.getEmail());
        loginTextField.setText(this.kontoFx.getLogin());
        hasloPasswordField.setText(this.kontoFx.getHaslo());
        haslo2PasswordField.setText(this.kontoFx.getHaslo());
    }
    /**
     * Metoda pobiera dane klienta z bazy.
     */
    protected void getKlientFromDataBase(){
        KlientFx klientFx = new KlientFx();
        klientFx.setId(this.kontoFx.getKlientfx());
        wypozyczalniaClient("Klient", "findById", toKlient(klientFx));
        this.klientFx = toKlientFx(super.klient);
    }

    @FXML
    public void initialize()  {
        this.kontoFx = Singleton.getInstance().getKontoFx();
        getKlientFromDataBase();
        setTextFields();
    }

}
