package pl.accessories;


import pl.tablesFx.EgzemplarzFx;
import pl.tablesFx.FilmFx;
import pl.tablesFx.WypozyczenieFx;

/**
 * <h2>Klasa Wypozyczeń</h2>
 * <p>Przechowuje obiekty typu FilmFx, WypozeczenieFx oraz EgzemplarzFx.</p>
 */
public class MojeWypozyczenia {

    private FilmFx filmFx;
    private WypozyczenieFx wypozyczenieFx;
    private EgzemplarzFx egzemplarzFx;

    /**
     * Metoda zwracająca wartość pola egzemplarzFx
     * @return obiekt typu EgzemplarzFx
     */
    public EgzemplarzFx getEgzemplarzFx() {
        return egzemplarzFx;
    }
    /**
     * Metoda ustawiająca wartość pola egzemplarzFx
     * @param egzemplarzFx obiekt typu EgzemplarzFx
     */
    public void setEgzemplarzFx(EgzemplarzFx egzemplarzFx) {
        this.egzemplarzFx = egzemplarzFx;
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
    /**
     * Metoda zwracająca wartość pola wypozyczenieFx
     * @return obiekt typu WypozyczenieFx
     */
    public WypozyczenieFx getWypozyczenieFx() {
        return wypozyczenieFx;
    }
    /**
     * Metoda ustawiająca wartość pola wypozyczenieFx
     * @param wypozyczenieFx obiekt typu WypozyczenieFx
     */
    public void setWypozyczenieFx(WypozyczenieFx wypozyczenieFx) {
        this.wypozyczenieFx = wypozyczenieFx;
    }

    @Override
    public String toString() {
        return wypozyczenieFx.getId() + " "
                + filmFx.getNazwa() + "\n"
                + "Data wypożyczenia: " + wypozyczenieFx.getDataWypozyczenia() + " "
                + "Data oddania: " + wypozyczenieFx.getDataOddania();
    }

}
