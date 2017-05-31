package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Rezerwacja;
import pl.tablesFx.FilmFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Damian on 2017-05-25.
 */
public class RezerwacjaDao {
    public void insertRezerwacje(FilmFx filmFx, int klientID) {
        Connections.initDataBase();
        Connections.insertRecord("REZERWACJE", "RezerwacjeSeq.NEXTVAL," + filmFx.getId() + "," + klientID + "");
        Connections.closeConnection();
    }
}
