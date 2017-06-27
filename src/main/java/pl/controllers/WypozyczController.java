package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import pl.bazadanych.tables.Rezerwacja;
import pl.tablesFx.DaneWypozyczeniaFx;
import pl.accessories.Singleton;
import pl.tablesFx.RezerwacjaFX;
import pl.tablesFx.WypozyczenieFx;

import static pl.accessories.Converters.toDaneWypozyczenia;
import static pl.accessories.Converters.toRezerwacja;
import static pl.accessories.Converters.toWypozyczenieFx;

/**
 * <h2>Klasa kontrolera widoku wypozyczeń.</h2>
 * <p>Zawiera metody potrzebne do obsługi widoku wypozyczeń.</p>
 */
public class WypozyczController extends FilmEditController{

    private ObservableList<RezerwacjaFX> rezerwacjaFXList = FXCollections.observableArrayList();

    RezerwacjaFX rezerwacjaFX;

    /**
     * Metoda wypozycza dany film i zapisuje te informacje do bazy.
     */
    @FXML
    private void borrow(){
        DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
        boolean exist = setFreeEgzemplarz(daneWypozyczeniaFx);
        if(exist == true) {
            setKlient(daneWypozyczeniaFx);
            setWypozyczenie(daneWypozyczeniaFx);
            getRezerwacjeFromDataBase();
            if(checkKlient(daneWypozyczeniaFx) == true)  {
                wypozyczalniaClient("DaneWypozyczenia", "insert", toDaneWypozyczenia(daneWypozyczeniaFx));
                logger.logFileAndConsole("info", "Dodano wypozyczenie do BD.");
                wypozyczalniaClient("Rezerwacja", "delete", toRezerwacja(rezerwacjaFX));
                logger.logFileAndConsole("info", "Usunięto rezerwację z BD.");
                warningWindow("Wypożyczono");
            }
            else if( rezerwacjaFXList.size() < freeEgzemplarzFxList.size() ){
                wypozyczalniaClient("DaneWypozyczenia", "insert", toDaneWypozyczenia(daneWypozyczeniaFx));
                logger.logFileAndConsole("info", "Dodano wypozyczenie do BD.");
                warningWindow("Wypożyczono");
            }
            else
                warningWindow("Brak wolnych egzemplarzy");
        }
        else if (exist == false)
            warningWindow("Brak wolnych egzemplarzy");
    }
    /**
     * Metoda sprawdza czy są wolne egzemplarze danego filmu.
     * @param daneWypozyczeniaFx obiekt typu DaneWypozyczeniaFx
     * @return zmienna typu boolean
     */
    private boolean setFreeEgzemplarz(DaneWypozyczeniaFx daneWypozyczeniaFx){
        super.manageSearch();
        if(freeEgzemplarzFxList.size() != 0) {
            daneWypozyczeniaFx.setIdEgzemplarzu(super.freeEgzemplarzFxList.get(0).getId());
            return true;
        }
        else
            return false;
    }
    /**
     * Metoda ustawia klienta który wypozycza film.
     * @param daneWypozyczeniaFx obiekt typu DaneWypozyczeniaFx
     */
    private void setKlient(DaneWypozyczeniaFx daneWypozyczeniaFx){
        kontoFxObjectProperty.set(kontoListView.getSelectionModel().getSelectedItem());
        daneWypozyczeniaFx.setIdKlienta(kontoFxObjectProperty.getValue().getId());
    }
    /**
     * Metoda ustawia wypozyczenie danego filmu.
     * @param daneWypozyczeniaFx obiekt typu DaneWypozyczeniaFx
     */
    private void setWypozyczenie(DaneWypozyczeniaFx daneWypozyczeniaFx){
        wypozyczalniaClient("Wypozyczenie", "insert", null);
        wypozyczalniaClient("Wypozyczenie", "selectMaxId", null);
        WypozyczenieFx wypozyczenieFx = toWypozyczenieFx(super.wypozyczenie);
        daneWypozyczeniaFx.setIdWypozyczenia(wypozyczenieFx.getId());
    }
    /**
     * Metoda pobiera rezerwacje danego filmu z bazy danych.
     */
    private void getRezerwacjeFromDataBase(){
        wypozyczalniaClient("Rezerwacja", "selectAll", null);
        ObservableList<Rezerwacja> rezerwacjaList = FXCollections.observableArrayList(super.rezerwacjaList);
        rezerwacjaList.forEach(e -> {
            if(e.getIdFilmu() == filmFx.getId()) {
                RezerwacjaFX rezerwacjaFX = new RezerwacjaFX();
                rezerwacjaFX.setId(e.getId());
                rezerwacjaFX.setFilmFx(e.getIdFilmu());
                rezerwacjaFX.setKlientFx(e.getIdKlienta());
                rezerwacjaFXList.add(rezerwacjaFX);
            }
        });
    }
    /**
     * Metoda sprawdza czy klient rezerwował film.
     * @param daneWypozyczeniaFx obiekt typu DaneWypozyczeniaFx
     * @return zmienna typu boolean
     */
    private boolean checkKlient(DaneWypozyczeniaFx daneWypozyczeniaFx){
        boolean exist = false;
        for(RezerwacjaFX e: rezerwacjaFXList){
            if(e.getKlientFx() == daneWypozyczeniaFx.getIdKlienta()) {
                rezerwacjaFX = e;
                exist = true;
            }
        }
        return exist;
    }


    public void initialize(){
        this.filmFx = Singleton.getInstance().getFilmFx();
    }

}
