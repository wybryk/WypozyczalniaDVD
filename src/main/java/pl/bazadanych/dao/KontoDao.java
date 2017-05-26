package pl.bazadanych.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Klient;
import pl.bazadanych.tables.Konto;
import pl.tablesFx.KontoFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class KontoDao {

    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<Konto> kontoList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("KONTO");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) +  "  " + resultSet.getString(3)+"  "+resultSet.getInt(4) + "  "+resultSet.getInt(5) );
                Konto konto = new Konto();
                konto.setId(resultSet.getInt(1));
                konto.setLogin(resultSet.getString(2));
                konto.setHaslo(resultSet.getString(3));
                konto.setAdmin(resultSet.getInt(4));
                konto.setKlient(resultSet.getInt(5));
                kontoList.add(konto);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return kontoList;
    }

    public void insertKonto(KontoFx kontoFx, int id) {
        Connections.initDataBase();
        Connections.insertRecord("KONTO", "KontoSeq.NEXTVAL, '" + kontoFx.getLogin() + "', '" + kontoFx.getHaslo() + "', "
                + kontoFx.getAdmin()+", " + id);
        Connections.closeConnection();
    }

    public void deleteKonto(KontoFx kontoFx){
        Connections.initDataBase();
        Connections.deleteRecord("KONTO", "ID_KONTA = " + kontoFx.getId());
        Connections.closeConnection();
    }

    public void updateKonto(KontoFx kontoFx){
        Connections.initDataBase();
        Connections.updateRecord("KONTO", "LOGIN = '" + kontoFx.getLogin() + "', HASLO = '" + kontoFx.getHaslo()
                + "', CZY_ADMIN = " + kontoFx.getAdmin()," ID_KONTA = " + kontoFx.getId());
        Connections.closeConnection();
    }
}
