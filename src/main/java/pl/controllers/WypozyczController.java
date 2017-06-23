package pl.controllers;

import javafx.fxml.FXML;
import pl.tablesFx.DaneWypozyczeniaFx;
import pl.accessories.Singleton;
import pl.tablesFx.WypozyczenieFx;

import static pl.accessories.Converters.toDaneWypozyczenia;
import static pl.accessories.Converters.toWypozyczenieFx;

/**
 * Created by Mateusz on 2017-05-24.
 */
public class WypozyczController extends FilmEditController{

    @FXML
    private void borrow(){
        DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
        boolean exist = setFreeEgzemplarz(daneWypozyczeniaFx);
        if(exist == true) {
            setKlient(daneWypozyczeniaFx);
            setWypozyczenie(daneWypozyczeniaFx);

            wypozyczalniaClient("DaneWypozyczenia", "insert", toDaneWypozyczenia(daneWypozyczeniaFx));
            System.out.println(daneWypozyczeniaFx);

            warningWindow("Wypożyczono.");
        }
        else if (exist == false)
            warningWindow("Brak wolnych egzemplarzy");
    }
    private boolean setFreeEgzemplarz(DaneWypozyczeniaFx daneWypozyczeniaFx){
        super.manageSearch();
        if(freeEgzemplarzFxList.size() != 0) {
            daneWypozyczeniaFx.setIdEgzemplarzu(super.freeEgzemplarzFxList.get(0).getId());
            return true;
        }
        else
            return false;
    }
    private void setKlient(DaneWypozyczeniaFx daneWypozyczeniaFx){
        kontoFxObjectProperty.set(kontoListView.getSelectionModel().getSelectedItem());
        daneWypozyczeniaFx.setIdKlienta(kontoFxObjectProperty.getValue().getId());
    }
    private void setWypozyczenie(DaneWypozyczeniaFx daneWypozyczeniaFx){
        wypozyczalniaClient("Wypozyczenie", "insert", null);
        wypozyczalniaClient("Wypozyczenie", "selectMaxId", null);
        WypozyczenieFx wypozyczenieFx = toWypozyczenieFx(super.wypozyczenie);
        daneWypozyczeniaFx.setIdWypozyczenia(wypozyczenieFx.getId());
    }
    public void initialize(){
        this.filmFx = Singleton.getInstance().getFilmFx();
    }

}
