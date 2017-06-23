package pl.controllers;

import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;

/**
 * <h2>Klasa kontrolera widoku wyświatlania danych klienta.</h2>
 * <p>Zawiera metody potrzebne do obsługi widoku wyświatlania danych klienta.</p>
 */
public class KlientEditKlientController extends KlientEditController{

    /**
     * Metoda przeciążająca, pobiera dane klienta z formularza.
     * @param klientFx obiekt typu KlientFx
     * @param kontoFx obiekt typu KontoFx
     * @return String hasło klienta
     */
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
