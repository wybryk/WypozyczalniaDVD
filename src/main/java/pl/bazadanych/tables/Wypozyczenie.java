package pl.bazadanych.tables;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Mateusz on 2017-04-20.
 */
@DatabaseTable(tableName = "WYPOZYCZENIE")
public class Wypozyczenie implements BaseTable {
    @DatabaseField(generatedId = true, columnName = "ID_WYPOZYCZENIA")
    private int id;
    @DatabaseField(columnName = "DATA_WYPOZYCZENIA")
    private Date dataWypozyczenia;
    @DatabaseField(columnName = "DATA_ODDANIA")
    private Date dataOddania;
    @ForeignCollectionField
    private ForeignCollection<DaneWypozyczenia> daneWypozyczenia;

    public Wypozyczenie() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Date getDataOddania() {
        return dataOddania;
    }

    public void setDataOddania(Date dataOddania) {
        this.dataOddania = dataOddania;
    }

    public ForeignCollection<DaneWypozyczenia> getDaneWypozyczenia() {
        return daneWypozyczenia;
    }

    public void setDaneWypozyczenia(ForeignCollection<DaneWypozyczenia> daneWypozyczenia) {
        this.daneWypozyczenia = daneWypozyczenia;
    }
}
