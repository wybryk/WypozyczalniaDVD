package tests;

import org.junit.Before;
import org.junit.Test;
import pl.accessories.Singleton;
import pl.tablesFx.FilmFx;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;

import static org.junit.Assert.*;

/**
 * Created by Damian on 2017-06-22.
 */
public class SingletonTest {
    pl.accessories.Singleton singleton;

    @Before
    public void setUp() throws Exception {
        singleton = new Singleton();
        singleton.setFilmFx(new FilmFx());
        singleton.setKlientFx(new KlientFx());
        singleton.setKontoFx(new KontoFx());
    }

    @Test
    public void getKontoFx() throws Exception {
        assertTrue(singleton.getKontoFx() instanceof KontoFx);
    }

    @Test
    public void getKlientFx() throws Exception {
        assertTrue(singleton.getKlientFx() instanceof KlientFx);
    }

    @Test
    public void getFilmFx() throws Exception {
        assertTrue(singleton.getFilmFx() instanceof FilmFx);
    }

}