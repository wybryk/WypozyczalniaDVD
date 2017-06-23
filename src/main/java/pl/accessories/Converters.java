package pl.accessories;

import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import java.sql.Date;
import java.time.LocalDate;


/**
 * Created by Mateusz on 2017-05-26.
 */
public class Converters {


    static public DaneWypozyczenia toDaneWypozyczenia(DaneWypozyczeniaFx daneWypozyczeniaFx){
        DaneWypozyczenia daneWypozyczenia = new DaneWypozyczenia();
        daneWypozyczenia.setIdKlienta(daneWypozyczeniaFx.getIdKlienta());
        daneWypozyczenia.setIdWypozyczenia(daneWypozyczeniaFx.getIdWypozyczenia());
        daneWypozyczenia.setIdEgzemplarzu(daneWypozyczeniaFx.getIdEgzemplarzu());
        return daneWypozyczenia;
    }
    static public Egzemplarz toEgzemplarz(EgzemplarzFx egzemplarzFx){
        Egzemplarz egzemplarz = new Egzemplarz();
        egzemplarz.setId(egzemplarzFx.getId());
        egzemplarz.setIdFilmu(egzemplarzFx.getIdFilmu());
        return egzemplarz;
    }
    static public Film toFilm(FilmFx filmFx){
        Film film = new Film();
        film.setId(filmFx.getId());
        film.setNazwa(filmFx.getNazwa());
        film.setOpis(filmFx.getOpis());
        film.setIlosc(filmFx.getIlosc());
        film.setPremiera(filmFx.getPremiera());
        film.setRezyser(filmFx.getRezyserFx());
        film.setGatunek(filmFx.getGatunekFx());
        return film;
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
    static public Klient toKlient(KlientFx klientFx){
        Klient klient = new Klient();
        klient.setId(klientFx.getId());
        klient.setImie(klientFx.getImie());
        klient.setNazwisko(klientFx.getNazwisko());
        klient.setEmail(klientFx.getEmail());
        return klient;
    }
    static public KlientFx toKlientFx(Klient klient){
        KlientFx klientFx = new KlientFx();
        klientFx.setId(klient.getId());
        klientFx.setImie(klient.getImie());
        klientFx.setNazwisko(klient.getNazwisko());
        klientFx.setEmail(klient.getEmail());
        return klientFx;
    }
    static public Konto toKonto(KontoFx kontoFx){
        Konto konto = new Konto();
        konto.setId(kontoFx.getId());
        konto.setLogin(kontoFx.getLogin());
        konto.setHaslo(kontoFx.getHaslo());
        konto.setAdmin(kontoFx.getAdmin());
        konto.setKlient(kontoFx.getKlientfx());
        return konto;
    }
    static public KontoFx toKontoFx(Konto konto){
        KontoFx kontoFx = new KontoFx();
        kontoFx.setId(konto.getId());
        kontoFx.setLogin(konto.getLogin());
        kontoFx.setHaslo(konto.getHaslo());
        kontoFx.setAdmin(konto.getAdmin());
        kontoFx.setKlientfx(konto.getKlient());
        return kontoFx;
    }
    static public Rezerwacja toRezerwacja(RezerwacjaFX rezerwacjaFX){
        Rezerwacja rezerwacja = new Rezerwacja();
        rezerwacja.setId(rezerwacjaFX.getId());
        rezerwacja.setIdFilmu(rezerwacjaFX.getFilmFx());
        rezerwacja.setIdKlienta(rezerwacjaFX.getKlientFx());
        return rezerwacja;
    }
    static public Rezyser toRezyser(RezyserFx rezyserFx){
        Rezyser rezyser = new Rezyser();
        rezyser.setId(rezyserFx.getId());
        rezyser.setNazwa(rezyserFx.getNazwa());
        return rezyser;
    }
    static public RezyserFx toRezyserFx(Rezyser rezyser){
        RezyserFx rezyserFx = new RezyserFx();
        rezyserFx.setId(rezyser.getId());
        rezyserFx.setNazwa(rezyser.getNazwa());
        return rezyserFx;
    }
    static public WypozyczenieFx toWypozyczenieFx(Wypozyczenie wypozyczenie){
        WypozyczenieFx wypozyczenieFx = new WypozyczenieFx();
        wypozyczenieFx.setId(wypozyczenie.getId());
        wypozyczenieFx.setDataWypozyczenia((Date) wypozyczenie.getDataWypozyczenia());
        wypozyczenieFx.setDataOddania((Date) wypozyczenie.getDataOddania());
        return wypozyczenieFx;
    }
    static public Date toDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }


    static public LocalDate toLocalDate(Date date){
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        return localDate;
    }
}
