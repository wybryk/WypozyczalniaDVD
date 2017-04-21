package pl.bazadanych.tabels;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 2017-04-20.
 */
@DatabaseTable(tableName = "KONTO")
public class Konto implements BaseTabel{
    @DatabaseField(generatedId = true, columnName = "ID_KONTA")
    private int id;
    @DatabaseField(columnName = "LOGIN", unique = true)
    private String login;
    @DatabaseField(columnName = "HASLO")
    private String haslo;
    @DatabaseField(columnName = "ADMIN", defaultValue = "false")
    private boolean admin;
    @DatabaseField(columnName = "ID_KLIENTA", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Klient klient;

    public Konto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
}
