package pl.tablesFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Mateusz on 2017-05-25.
 */
public class EgzemplarzFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty idFilmu = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getIdFilmu() {
        return idFilmu.get();
    }

    public IntegerProperty idFilmuProperty() {
        return idFilmu;
    }

    public void setIdFilmu(int idFilmu) {
        this.idFilmu.set(idFilmu);
    }

    @Override
    public String toString() {
        return "EgzemplarzFx{" +
                "id=" + id +
                ", idFilmu=" + idFilmu +
                '}';
    }
}
