package pl.accessories;

import javafx.scene.control.DatePicker;
import pl.bazadanych.tables.Film;
import pl.bazadanych.tables.Klient;
import pl.bazadanych.tables.Konto;
import pl.bazadanych.tables.Rezyser;
import pl.tablesFx.FilmFx;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;
import pl.tablesFx.RezyserFx;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h2>Klasa Konwerterów</h2>
 * <p>Zawiera kilka konwerterów używanych w innych klasach.</p>
 */
public class Converters {
    /**
     * Metoda konwertujaca obiekt typu Konto na typ KontoFx
     * @param konto parametr będący obiektem typu Konto
     * @return obiekt typu KontoFx
     */
    static public KontoFx toKontoFx(Konto konto){
        KontoFx kontoFx = new KontoFx();
        kontoFx.setId(konto.getId());
        kontoFx.setLogin(konto.getLogin());
        kontoFx.setHaslo(konto.getHaslo());
        kontoFx.setAdmin(konto.getAdmin());
        kontoFx.setKlientfx(konto.getKlient());
        return kontoFx;
    }
    /**
     * Metoda konwertujaca obiekt typu Klient na typ KlientFx
     * @param klient parametr będący obiektem typu Klient
     * @return obiekt typu KlientFx
     */
    static public KlientFx toKlientFx(Klient klient){
        KlientFx klientFx = new KlientFx();
        klientFx.setId(klient.getId());
        klientFx.setImie(klient.getImie());
        klientFx.setNazwisko(klient.getNazwisko());
        klientFx.setEmail(klient.getEmail());
        return klientFx;
    }
    /**
     * Metoda konwertujaca obiekt typu KlientFx na typ Klient
     * @param klientFx parametr będący obiektem typu KlientFx
     * @return obiekt typu Klient
     */
    static public Klient toKlient(KlientFx klientFx){
        Klient klient = new Klient();
        klient.setId(klientFx.getId());
        klient.setImie(klientFx.getImie());
        klient.setNazwisko(klientFx.getNazwisko());
        klient.setEmail(klientFx.getEmail());
        return klient;
    }
    /**
     * Metoda konwertujaca obiekt typu Rezyser na typ RezyserFx
     * @param rezyser parametr będący obiektem typu Rezyser
     * @return obiekt typu RezyserFx
     */
    static public RezyserFx toRezyserFx(Rezyser rezyser){
        RezyserFx rezyserFx = new RezyserFx();
        rezyserFx.setId(rezyser.getId());
        rezyserFx.setNazwa(rezyser.getNazwa());
        return rezyserFx;
    }
    /**
     * Metoda konwertujaca obiekt typu Film na typ FilmFx
     * @param film parametr będący obiektem typu Film
     * @return obiekt typu FilmFx
     */
    static public FilmFx toFilmFx(Film film){
        FilmFx filmFx = new FilmFx();
        filmFx.setId(film.getId());
        filmFx.setNazwa(film.getNazwa());
        filmFx.setOpis(film.getOpis());
        filmFx.setIlosc(film.getIlosc());
        filmFx.setPremiera(film.getPremiera());
        filmFx.setRezyserFx(film.getRezyser());
        filmFx.setGatunekFx(film.getGatunek());
        return filmFx;
    }
    /**
     * Metoda statyczna konwertujaca obiekt typu LocalDate na typ Date
     * @param localDate parametr będący obiektem typu LocalDate
     * @return obiekt typu Date
     */
    static public Date toDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    //static public Date toDate(LocalTime localTime) { return java.sql.Date.valueOf(localTime);}
    /**
     * Metoda statyczna konwertujaca obiekt typu Date na typ LocalDate
     * @param date parametr będący obiektem typu Date
     * @return obiekt typu LocalDate
     */
    static public LocalDate toLocalDate(Date date){
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        return localDate;
    }
}
