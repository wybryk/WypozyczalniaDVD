package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * <h2>Klasa przechowująca dane egzemplarza filmu.</h2>
 * <p>Zawiera pola z tabeli EGZEMPLARZ.</p>
 */
public class EgzemplarzFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty idFilmu = new SimpleIntegerProperty();
    /**
     * Metoda zwracająca wartość pola idd
     * @return int id egzemplarza
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * Metoda ustawiająca wartość pola id
     * @param id int identyfikator egzemplarza
     */
    public void setId(int id) {
        this.id.set(id);
    }
    /**
     * Metoda zwracająca wartość pola idFilmu
     * @return int id filmu
     */
    public int getIdFilmu() {
        return idFilmu.get();
    }

    public IntegerProperty idFilmuProperty() {
        return idFilmu;
    }
    /**
     * Metoda ustawiająca wartość pola idFilmu
     * @param idFilmu int identyfikator filmu
     */
    public void setIdFilmu(int idFilmu) {
        this.idFilmu.set(idFilmu);
    }
}
