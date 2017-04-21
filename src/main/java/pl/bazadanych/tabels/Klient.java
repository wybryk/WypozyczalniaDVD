package pl.bazadanych.tabels;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 2017-04-19.
 */
@DatabaseTable(tableName = "KLIENT")
public class Klient implements BaseTabel{

    @DatabaseField(generatedId = true, columnName = "ID_KLIENTA")
    private int id;
    @DatabaseField(columnName = "IMIE")
    private String imie;
    @DatabaseField(columnName = "NAZWISKO")
    private String nazwisko;
    @DatabaseField(columnName = "EMAIL")
    private String email;
    @ForeignCollectionField
    private ForeignCollection<Konto> konto;
    @ForeignCollectionField
    private ForeignCollection<DaneWypozyczenia> daneWypozyczenia;

    public Klient(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForeignCollection<Konto> getKonto() {
        return konto;
    }

    public void setKonto(ForeignCollection<Konto> konto) {
        this.konto = konto;
    }

    public ForeignCollection<DaneWypozyczenia> getDaneWypozyczenia() {
        return daneWypozyczenia;
    }

    public void setDaneWypozyczenia(ForeignCollection<DaneWypozyczenia> daneWypozyczenia) {
        this.daneWypozyczenia = daneWypozyczenia;
    }
}
