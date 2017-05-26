package pl.bazadanych.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Gatunek;
import pl.tablesFx.GatunekFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class GatunekDao  {

    public Gatunek findGatunekById(int id) {

        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("GATUNEK", "ID_GATUNKU = " + id);
        Gatunek gatunek = new Gatunek();
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

    public ObservableList selectAll()  {
        ObservableList<Gatunek> gatunekList = FXCollections.observableArrayList();
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
