package pl.controllers;

import javafx.fxml.FXML;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;
import pl.accessories.Singleton;

import static pl.accessories.Converters.toKlient;
import static pl.accessories.Converters.toKlientFx;
import static pl.accessories.Converters.toKonto;


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
            wypozyczalniaClient("Klient", "update", toKlient(klientFx));
            wypozyczalniaClient("Konto", "update", toKonto(kontoFx));
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
