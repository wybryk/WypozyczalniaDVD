package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pl.accessories.MojeWypozyczenia;
import pl.accessories.Singleton;
import pl.bazadanych.dao.*;
import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import java.sql.Date;
import java.util.List;

/**
 * <h2>Klasa kontrolera widoku wypożyczeń klienta.</h2>
 * <p>Zawiera metody potrzebne do obsługi widoku wypożyczeń klienta.</p>
 */
public class MojeWypozyczeniaController {
    @FXML
    private ListView<FilmFx>  reservationListView;
    @FXML
    private ListView<MojeWypozyczenia>  borrowsListView;


    ObservableList<DaneWypozyczeniaFx> daneWypozyczeniaFxList = FXCollections.observableArrayList();

    ObservableList<EgzemplarzFx> egzemplarzFxList = FXCollections.observableArrayList();

    ObservableList<FilmFx> filmFxList = FXCollections.observableArrayList();
    ObservableList<FilmFx> resFilmFxList = FXCollections.observableArrayList();

    ObservableList<WypozyczenieFx> wypozyczenieFxList = FXCollections.observableArrayList();

    ObservableList<MojeWypozyczenia> mojeWypozyczeniaList = FXCollections.observableArrayList();

    ObservableList<RezerwacjaFX> rezerwacjaFxList = FXCollections.observableArrayList();

    private KontoFx kontoFx;
    /**
     * Metoda pobiera wypozyczenia klienta z bazy danych.
     */
    private void getDaneWypozyczeniaFromDataBase(){
        DaneWypozyczeniaDao daneWypozyczeniaDao = new DaneWypozyczeniaDao();
        List<DaneWypozyczenia> daneWypozyczeniaList = daneWypozyczeniaDao.selectAll();
        daneWypozyczeniaList.forEach(e->{
            if(e.getIdKlienta()==kontoFx.getKlientfx()){
                DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
                daneWypozyczeniaFx.setIdEgzemplarzu(e.getIdEgzemplarzu());
                daneWypozyczeniaFx.setIdKlienta(e.getIdKlienta());
                daneWypozyczeniaFx.setIdWypozyczenia(e.getIdWypozyczenia());

                daneWypozyczeniaFxList.add(daneWypozyczeniaFx);
            }
        });
    }
    /**
     * Metoda pobiera dane o egzemplarzu filmu z bazy danych.
     */
    private void getEgzemplarzFromDataBase(){
        EgzemplarzDao egzemplarzDao = new EgzemplarzDao();
        List<Egzemplarz> egzemplarzList = egzemplarzDao.selectAll();
        egzemplarzList.forEach(e->{
            daneWypozyczeniaFxList.forEach(i-> {
                if (e.getId() == i.getIdEgzemplarzu()) {
                    EgzemplarzFx egzemplarzFx = new EgzemplarzFx();
                    egzemplarzFx.setId(e.getId());
                    egzemplarzFx.setIdFilmu(e.getIdFilmu());

                    this.egzemplarzFxList.add(egzemplarzFx);
                }
            });
        });
    }
    /**
     * Metoda pobiera dane filmu z bazy danych.
     */
    private void getFilmFromDataBase() {
        FilmDao filmDao = new FilmDao();
        List<Film> filmList = filmDao.selectAllFilm();

        filmList.forEach(e -> {
            egzemplarzFxList.forEach(i->{
                if (e.getId() == i.getIdFilmu())
                {
                    FilmFx filmFx = new FilmFx();

                    filmFx.setId(e.getId());
                    filmFx.setNazwa(e.getNazwa());
                    filmFx.setIlosc(e.getIlosc());
                    filmFx.setOpis(e.getOpis());
                    filmFx.setPremiera(e.getPremiera());
                    filmFx.setGatunekFx(e.getGatunek());
                    filmFx.setRezyserFx(e.getRezyser());

                    filmFxList.add(filmFx);
                }
             });
        });
    }
    /**
     * Metoda pobiera listę wypozyczeń z bazy danych.
     */
    private void getWypozyczenieFromDataBase(){
        WypozyczenieDao wypozyczenieDao = new WypozyczenieDao();
        List<Wypozyczenie> wypozyczenieList = wypozyczenieDao.selectAll();

        wypozyczenieList.forEach(e->{
            daneWypozyczeniaFxList.forEach(i->{
                if(e.getId() == i.getIdWypozyczenia()) {
                    WypozyczenieFx wypozyczenieFx = new WypozyczenieFx();
                    wypozyczenieFx.setId(e.getId());
                    wypozyczenieFx.setDataWypozyczenia((Date) e.getDataWypozyczenia());
                    wypozyczenieFx.setDataOddania((Date) e.getDataOddania());
                    this.wypozyczenieFxList.add(wypozyczenieFx);
                }
            });
        });
    }
    /**
     * Metoda tworzy listę wypożyczeń klienta.
     */
    private void setMojeWypozyczenia(){

            egzemplarzFxList.forEach(i->{
                wypozyczenieFxList.forEach(j->{
                    filmFxList.forEach(k->{
                        daneWypozyczeniaFxList.forEach(l -> {
                            if (l.getIdWypozyczenia() == j.getId() && l.getIdEgzemplarzu() == i.getId() && i.getIdFilmu() == k.getId()) {
                                MojeWypozyczenia mojeWypozyczenia = new MojeWypozyczenia();
                                mojeWypozyczenia.setEgzemplarzFx(i);
                                mojeWypozyczenia.setFilmFx(k);
                                mojeWypozyczenia.setWypozyczenieFx(j);
                                mojeWypozyczeniaList.add(mojeWypozyczenia);
                            }
                        });
                    });
                });
            });
        borrowsListView.setItems(mojeWypozyczeniaList);
    }
    /**
     * Metoda pobiera rezerwacje klienta z bazy danych.
     */
    private void getReservationsFromDatabase() {
        RezerwacjaDao rezerwacjaDao = new RezerwacjaDao();
        List<Rezerwacja> rezerwacjaList = rezerwacjaDao.selectAll();
        rezerwacjaList.forEach(e -> {
            if (e.getIdKlienta() == kontoFx.getKlientfx()) {
                RezerwacjaFX rezerwacjaFX = new RezerwacjaFX();
                rezerwacjaFX.setId(e.getId());
                rezerwacjaFX.setFilmFx(e.getIdFilmu());
                rezerwacjaFX.setKlientFx(e.getIdKlienta());

                rezerwacjaFxList.add(rezerwacjaFX);
            }
        });
    }
    /**
     * Metoda tworzy listę zarezerwowanych filmów.
     */
    private void getResFilms() {
        if (rezerwacjaFxList.size() != 0) {
            FilmDao filmDao = new FilmDao();
            List<Film> filmList = filmDao.selectAllFilm();
            filmList.forEach(e -> {
                rezerwacjaFxList.forEach(ev -> {
                    if (e.getId() == ev.getFilmFx()) {
                        FilmFx filmFx = new FilmFx();
                        filmFx.setId(e.getId());
                        filmFx.setNazwa(e.getNazwa());
                        filmFx.setRezyserFx(e.getRezyser());
                        filmFx.setGatunekFx(e.getGatunek());
                        filmFx.setOpis(e.getOpis());
                        filmFx.setIlosc(e.getIlosc());
                        filmFx.setPremiera(e.getPremiera());

                        resFilmFxList.add(filmFx);
                    }
                });
            });

            reservationListView.setItems(resFilmFxList);
        }
    }

    public void initialize(){
        this.kontoFx = Singleton.getInstance().getKontoFx();
        getDaneWypozyczeniaFromDataBase();
        getEgzemplarzFromDataBase();
        getFilmFromDataBase();
        getWypozyczenieFromDataBase();
        setMojeWypozyczenia();
        getReservationsFromDatabase();
        getResFilms();
    }
}
