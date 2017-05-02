package pl.bazadanych.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.tablesFx.GatunekFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class GatunekDao  {

    public GatunekFx findGatunekById(int id) {

        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("GATUNEK", "ID_GATUNKU = " + id);
        GatunekFx gatunekFx = new GatunekFx();
        try{
            gatunekFx.setId(resultSet.getInt(1));
            gatunekFx.setNazwa(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return gatunekFx;
    }

    public ObservableList selectAll()  {
        ObservableList<GatunekFx> gatunekFxList = FXCollections.observableArrayList();
        Connections.initDataBase();
        ResultSet resultSet = Connections.selectRecords("GATUNEK");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2));
                GatunekFx gatunek = new GatunekFx();
                gatunek.setId(resultSet.getInt(1));
                gatunek.setNazwa(resultSet.getString(2));
                gatunekFxList.add(gatunek);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return gatunekFxList;
    }
}
