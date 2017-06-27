package pl.accessories;

import pl.bazadanych.tables.*;
import pl.tablesFx.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h2>Klasa Konwerterów</h2>
 * <p>Zawiera kilka konwerterów używanych w innych klasach.</p>
 */
public class Converters {
    /**
     * Metoda konwertujaca obiekt typu DaneWypozyczeniaFx na typ DaneWypozyczenia
     * @param daneWypozyczeniaFx parametr będący obiektem typu DaneWypozyczeniaFx
     * @return obiekt typu DaneWypozyczenia
     */
    static public DaneWypozyczenia toDaneWypozyczenia(DaneWypozyczeniaFx daneWypozyczeniaFx){
        DaneWypozyczenia daneWypozyczenia = new DaneWypozyczenia();
        daneWypozyczenia.setIdKlienta(daneWypozyczeniaFx.getIdKlienta());
        daneWypozyczenia.setIdWypozyczenia(daneWypozyczeniaFx.getIdWypozyczenia());
        daneWypozyczenia.setIdEgzemplarzu(daneWypozyczeniaFx.getIdEgzemplarzu());
        return daneWypozyczenia;
    }
    /**
     * Metoda konwertujaca obiekt typu EgzemplarzFx na typ Egzemplarz
     * @param egzemplarzFx parametr będący obiektem typu EgzemplarzFx
     * @return obiekt typu Egzemplarz
     */
    static public Egzemplarz toEgzemplarz(EgzemplarzFx egzemplarzFx){
        Egzemplarz egzemplarz = new Egzemplarz();
        egzemplarz.setId(egzemplarzFx.getId());
        egzemplarz.setIdFilmu(egzemplarzFx.getIdFilmu());
        return egzemplarz;
    }
    /**
     * Metoda konwertujaca obiekt typu FilmFx na typ Film
     * @param filmFx parametr będący obiektem typu FilmFx
     * @return obiekt typu Film
     */
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
     * Metoda konwertujaca obiekt typu KontoFx na typ Konto
     * @param kontoFx parametr będący obiektem typu KontoFx
     * @return obiekt typu Konto
     */
    static public Konto toKonto(KontoFx kontoFx){
        Konto konto = new Konto();
        konto.setId(kontoFx.getId());
        konto.setLogin(kontoFx.getLogin());
        konto.setHaslo(kontoFx.getHaslo());
        konto.setAdmin(kontoFx.getAdmin());
        konto.setKlient(kontoFx.getKlientfx());
        return konto;
    }
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
     * Metoda konwertujaca obiekt typu RezerwacjaFX na typ Rezerwacja
     * @param rezerwacjaFX parametr będący obiektem typu RezerwacjaFX
     * @return obiekt typu Rezerwacja
     */
    static public Rezerwacja toRezerwacja(RezerwacjaFX rezerwacjaFX){
        Rezerwacja rezerwacja = new Rezerwacja();
        rezerwacja.setId(rezerwacjaFX.getId());
        rezerwacja.setIdFilmu(rezerwacjaFX.getFilmFx());
        rezerwacja.setIdKlienta(rezerwacjaFX.getKlientFx());
        return rezerwacja;
    }
    /**
     * Metoda konwertujaca obiekt typu RezyserFx na typ Rezyser
     * @param rezyserFx parametr będący obiektem typu RezyserFx
     * @return obiekt typu Rezyser
     */
    static public Rezyser toRezyser(RezyserFx rezyserFx){
        Rezyser rezyser = new Rezyser();
        rezyser.setId(rezyserFx.getId());
        rezyser.setNazwa(rezyserFx.getNazwa());
        return rezyser;
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
     * Metoda konwertujaca obiekt typu Wypozyczenie na typ WypozyczenieFx
     * @param wypozyczenie parametr będący obiektem typu Wypozyczenie
     * @return obiekt typu WypozyczenieFx
     */
    static public WypozyczenieFx toWypozyczenieFx(Wypozyczenie wypozyczenie) {
        WypozyczenieFx wypozyczenieFx = new WypozyczenieFx();
        wypozyczenieFx.setId(wypozyczenie.getId());
        wypozyczenieFx.setDataWypozyczenia((Date) wypozyczenie.getDataWypozyczenia());
        wypozyczenieFx.setDataOddania((Date) wypozyczenie.getDataOddania());
        return wypozyczenieFx;
    }
    /**
     * Metoda statyczna konwertujaca obiekt typu LocalDate na typ Date
     * @param localDate parametr będący obiektem typu LocalDate
     * @return obiekt typu Date
     */
    static public Date toDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }


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
