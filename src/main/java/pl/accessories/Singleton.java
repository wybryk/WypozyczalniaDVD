package pl.accessories;

import pl.tablesFx.FilmFx;
import pl.tablesFx.KlientFx;
import pl.tablesFx.KontoFx;

/**
 * <h2>Klasa Singleton</h2>
 * <p>Przechowuje obiekty typu FilmFx, KlientFx oraz KontoFx.</p>
 * <p>Wzorzec projektowy Singleton umożliwia dostęp do tych samych wartości w całym programie.</p>
 */
public class Singleton {

    private static Singleton instance = new Singleton();
    /**
     * Metoda zwracająca instancje Singleton
     * @return obiekt typu Singleton
     */
    public static Singleton getInstance() {
        return instance;
    }

    private KontoFx kontoFx;
    private KlientFx klientFx;
    private FilmFx filmFx;

    /**
     * Metoda zwracająca wartość pola kontoFx
     * @return obiekt typu KontoFx
     */
    public KontoFx getKontoFx() {
        return kontoFx;
    }
    /**
     * Metoda ustawiająca wartość pola kontoFx
     * @param kontoFx obiekt typu KontoFx
     */
    public void setKontoFx(KontoFx kontoFx) {
        this.kontoFx = kontoFx;
    }
    /**
     * Metoda zwracająca wartość pola klientFx
     * @return obiekt typu KlientFx
     */
    public KlientFx getKlientFx() {
        return klientFx;
    }
    /**
     * Metoda ustawiająca wartość pola klientFx
     * @param klientFx obiekt typu KlientFx
     */
    public void setKlientFx(KlientFx klientFx) {
        this.klientFx = klientFx;
    }
    /**
     * Metoda zwracająca wartość pola filmFx
     * @return obiekt typu FilmFx
     */
    public FilmFx getFilmFx() {
        return filmFx;
    }
    /**
     * Metoda ustawiająca wartość pola filmFx
     * @param filmFx obiekt typu FilmFx
     */
    public void setFilmFx(FilmFx filmFx) {
        this.filmFx = filmFx;
    }
}
