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
 * Created by Mateusz on 2017-05-26.
 */
public class Converters {

    static public KontoFx toKontoFx(Konto konto){
        KontoFx kontoFx = new KontoFx();
        kontoFx.setId(konto.getId());
        kontoFx.setLogin(konto.getLogin());
        kontoFx.setHaslo(konto.getHaslo());
        kontoFx.setAdmin(konto.getAdmin());
        kontoFx.setKlientfx(konto.getKlient());
        return kontoFx;
    }
    static public KlientFx toKlientFx(Klient klient){
        KlientFx klientFx = new KlientFx();
        klientFx.setId(klient.getId());
        klientFx.setImie(klient.getImie());
        klientFx.setNazwisko(klient.getNazwisko());
        klientFx.setEmail(klient.getEmail());
        return klientFx;
    }
    static public Klient toKlient(KlientFx klientFx){
        Klient klient = new Klient();
        klient.setId(klientFx.getId());
        klient.setImie(klientFx.getImie());
        klient.setNazwisko(klientFx.getNazwisko());
        klient.setEmail(klientFx.getEmail());
        return klient;
    }
    static public RezyserFx toRezyserFx(Rezyser rezyser){
        RezyserFx rezyserFx = new RezyserFx();
        rezyserFx.setId(rezyser.getId());
        rezyserFx.setNazwa(rezyser.getNazwa());
        return rezyserFx;
    }
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
    static public Date toDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    //static public Date toDate(LocalTime localTime) { return java.sql.Date.valueOf(localTime);}

    static public LocalDate toLocalDate(Date date){
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        return localDate;
    }
}
