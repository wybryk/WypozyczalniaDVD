package tests;

import org.junit.Before;
import org.junit.Test;
import pl.accessories.MojeWypozyczenia;
import pl.tablesFx.*;

import static org.junit.Assert.*;

/**
 * Created by Damian on 2017-06-22.
 */
public class MojeWypozyczeniaTest {
    private pl.accessories.MojeWypozyczenia mojeWypozyczenia;

    @Before
    public void setUp() throws Exception {
        mojeWypozyczenia = new MojeWypozyczenia();
        mojeWypozyczenia.setEgzemplarzFx(new EgzemplarzFx());
        mojeWypozyczenia.setFilmFx(new FilmFx());
        mojeWypozyczenia.setWypozyczenieFx(new WypozyczenieFx());
    }

    @Test
    public void getEgzemplarzFx() throws Exception {
        assertTrue(mojeWypozyczenia.getEgzemplarzFx() instanceof EgzemplarzFx);
    }

    @Test
    public void getFilmFx() throws Exception {
        assertTrue(mojeWypozyczenia.getFilmFx() instanceof FilmFx);
    }

    @Test
    public void getWypozyczenieFx() throws Exception {
        assertTrue(mojeWypozyczenia.getWypozyczenieFx() instanceof WypozyczenieFx);
    }

}