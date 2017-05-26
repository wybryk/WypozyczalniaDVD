package pl.tablesFx;



/**
 * Created by Mateusz on 2017-05-26.
 */
public class MojeWypozyczenia {

    private FilmFx filmFx;
    private WypozyczenieFx wypozyczenieFx;
    private EgzemplarzFx egzemplarzFx;


    public EgzemplarzFx getEgzemplarzFx() {
        return egzemplarzFx;
    }

    public void setEgzemplarzFx(EgzemplarzFx egzemplarzFx) {
        this.egzemplarzFx = egzemplarzFx;
    }

    public FilmFx getFilmFx() {
        return filmFx;
    }

    public void setFilmFx(FilmFx filmFx) {
        this.filmFx = filmFx;
    }

    public WypozyczenieFx getWypozyczenieFx() {
        return wypozyczenieFx;
    }

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
