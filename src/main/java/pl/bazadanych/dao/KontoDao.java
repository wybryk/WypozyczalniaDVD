package pl.bazadanych.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Klient;
import pl.bazadanych.tables.Konto;
import pl.tablesFx.KontoFx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class KontoDao {

    public List selectAll(){
        Connections.initDataBase();
        List<Konto> kontoList = new ArrayList<>();
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

    public void insertKonto(Konto konto) {
        Connections.initDataBase();
        Connections.insertRecord("KONTO", "KontoSeq.NEXTVAL, '" + konto.getLogin() + "', '" + konto.getHaslo() + "', "
                + konto.getAdmin()+", " + konto.getKlient());
        Connections.closeConnection();
    }

    public void deleteKonto(Konto konto){
        Connections.initDataBase();
        Connections.deleteRecord("KONTO", "ID_KONTA = " + konto.getId());
        Connections.closeConnection();
    }

    public void updateKonto(Konto konto){
        Connections.initDataBase();
        Connections.updateRecord("KONTO", "LOGIN = '" + konto.getLogin() + "', HASLO = '" + konto.getHaslo()
                + "', CZY_ADMIN = " + konto.getAdmin()," ID_KONTA = " + konto.getId());
        Connections.closeConnection();
    }
}
