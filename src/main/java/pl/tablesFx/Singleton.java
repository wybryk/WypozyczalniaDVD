package pl.tablesFx;

/**
 * Created by Mateusz on 2017-05-24.
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    private KontoFx kontoFx;
    private  KlientFx klientFx;
    private FilmFx filmFx;

    public KontoFx getKontoFx() {
        return kontoFx;
    }
    public void setKontoFx(KontoFx kontoFx) {
        this.kontoFx = kontoFx;
    }
    public KlientFx getKlientFx() {
        return klientFx;
    }
    public void setKlientFx(KlientFx klientFx) {
        this.klientFx = klientFx;
    }
    public FilmFx getFilmFx() {
        return filmFx;
    }
    public void setFilmFx(FilmFx filmFx) {
        this.filmFx = filmFx;
    }
}
