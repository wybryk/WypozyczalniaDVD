package pl.bazadanych.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Gatunek;
import pl.tablesFx.GatunekFx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Klasa łącząca z tabelą Gatunek w BD</h2>
 * <p>Zawiera metody do pobierania i szukanai elementów z/w bazy.</p>
 */
public class GatunekDao  {

    public Gatunek findGatunekById(Gatunek gatunek) {

        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("GATUNEK", "ID_GATUNKU = " + gatunek.getId());
        try{
            while (resultSet.next()) {
                gatunek.setId(resultSet.getInt(1));
                gatunek.setNazwa(resultSet.getString(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return gatunek;
    }
    /**
     * Metoda pobierająca rekordy z tabeli GATUNEK
     * @return obiekt typu List
     */
    public List<Gatunek> selectAll()  {
        List<Gatunek> gatunekList = new ArrayList<>();
        Connections.initDataBase();
        ResultSet resultSet = Connections.selectRecords("GATUNEK");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2));
                Gatunek gatunek = new Gatunek();
                gatunek.setId(resultSet.getInt(1));
                gatunek.setNazwa(resultSet.getString(2));
                gatunekList.add(gatunek);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return gatunekList;
    }
}
