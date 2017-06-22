package tests;

import pl.bazadanych.tables.Film;
import pl.bazadanych.tables.Klient;
import pl.bazadanych.tables.Konto;
import pl.bazadanych.tables.Rezyser;
import pl.tablesFx.FilmFx;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;
import pl.tablesFx.RezyserFx;

import static org.junit.Assert.*;

/**
 * Created by Damian on 2017-06-22.
 */
public class ConvertersTest {
    private Konto konto;
    private Film film;
    private Klient klient;
    private KlientFx klientFx;
    private Rezyser rezyser;

    @org.junit.Before
    public void setUp() throws Exception {
        konto = new Konto();
        klient = new Klient();
        klientFx = new KlientFx();
        rezyser = new Rezyser();
        film = new Film();
    }

    @org.junit.Test
    public void toKontoFx() throws Exception {
        assertTrue(pl.accessories.Converters.toKontoFx(this.konto) instanceof  KontoFx);
    }

    @org.junit.Test
    public void toKlientFx() throws Exception {
        assertTrue(pl.accessories.Converters.toKlientFx(this.klient) instanceof KlientFx);
    }

    @org.junit.Test
    public void toKlient() throws Exception {
        assertTrue(pl.accessories.Converters.toKlient(this.klientFx) instanceof Klient);
    }

    @org.junit.Test
    public void toRezyserFx() throws Exception {
        assertTrue(pl.accessories.Converters.toRezyserFx(this.rezyser) instanceof RezyserFx);
    }

    @org.junit.Test
    public void toFilmFx() throws Exception {
        assertTrue(pl.accessories.Converters.toFilmFx(this.film) instanceof FilmFx);
    }

}