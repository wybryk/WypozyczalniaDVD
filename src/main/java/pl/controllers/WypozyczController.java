package pl.controllers;

import javafx.fxml.FXML;
import pl.bazadanych.dao.DaneWypozyczeniaDao;
import pl.bazadanych.dao.WypozyczenieDao;
import pl.bazadanych.tables.DaneWypozyczenia;
import pl.tablesFx.DaneWypozyczeniaFx;
import pl.tablesFx.FilmFx;
import pl.accessories.Singleton;

/**
 * <h2>Klasa kontrolera widoku wypozyczeń.</h2>
 * <p>Zawiera metody potrzebne do obsługi widoku wypozyczeń.</p>
 */
public class WypozyczController extends FilmEditController{

    /**
     * Metoda wypozycza dany film i zapisuje te informacje do bazy.
     */
    @FXML
    private void borrow(){
        DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
        manageSearch();
        setFreeEgzemplarz(daneWypozyczeniaFx);
        setKlient(daneWypozyczeniaFx);
        setWypozyczenie(daneWypozyczeniaFx);

        DaneWypozyczeniaDao daneWypozyczeniaDao = new DaneWypozyczeniaDao();

        daneWypozyczeniaDao.insertDaneWypozyczenia(daneWypozyczeniaFx);
        logger.logFileAndConsole("info", "Dodano wypozyczenie do BD.");
    }
    /**
     * Metoda sprawdza czy są wolne egzemplarze danego filmu.
     * @param daneWypozyczeniaFx obiekt typu DaneWypozyczeniaFx
     */
    private void setFreeEgzemplarz(DaneWypozyczeniaFx daneWypozyczeniaFx){
        if(freeEgzemplarzFxList.size() != 0)
            daneWypozyczeniaFx.setIdEgzemplarzu(freeEgzemplarzFxList.get(0).getId());
        else
            warningWindow("Brak wolnych egzemplarzy");
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
        WypozyczenieDao wypozyczenieDao = new WypozyczenieDao();
        wypozyczenieDao.insertWypozyczenie();
        daneWypozyczeniaFx.setIdWypozyczenia(wypozyczenieDao.selectMaxId());
    }
    public void initialize(){
        this.filmFx = Singleton.getInstance().getFilmFx();
    }

}
