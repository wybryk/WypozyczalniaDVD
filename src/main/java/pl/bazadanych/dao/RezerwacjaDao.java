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

    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<Rezerwacja> rezerwacjaList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("REZERWACJE");
        try{
            while (resultSet.next()) {
                Rezerwacja rezerwacja = new Rezerwacja();
                rezerwacja.setId(resultSet.getInt(1));
                rezerwacja.setIdFilmu(resultSet.getInt(2));
                rezerwacja.setIdKlienta(resultSet.getInt(3));

                rezerwacjaList.add(rezerwacja);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return rezerwacjaList;
    }

    public void insertRezerwacje(FilmFx filmFx, int klientID) {
        Connections.initDataBase();
        Connections.insertRecord("REZERWACJE", "RezerwacjeSeq.NEXTVAL," + filmFx.getId() + "," + klientID + "");
        Connections.closeConnection();
    }
}
