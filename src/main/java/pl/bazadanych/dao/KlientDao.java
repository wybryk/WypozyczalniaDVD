package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Klient;
import pl.tablesFx.KlientFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class KlientDao  {

    public KlientFx findKlientById(int id) {

        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("KLIENT", "ID_KLIENTA = " + id);
        KlientFx klientFx = new KlientFx();
        try{
            klientFx.setId(resultSet.getInt(1));
            klientFx.setImie(resultSet.getString(2));
            klientFx.setNazwisko(resultSet.getString(3));
            klientFx.setEmail(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return klientFx;
    }

    public void insertKlient(Klient klient) {
            Connections.initDataBase();
            Connections.insertRecord("KLIENT", "KlientSeq.NEXTVAL, '" + klient.getImie() + "', '" + klient.getNazwisko() + "', '"
                    + klient.getEmail() + "'");
            Connections.closeConnection();
    }

    public void deleteKlient(Klient klient) {
        Connections.initDataBase();
        Connections.deleteRecord("KLIENT", "ID_KLIENTA = " + klient.getId());
        Connections.closeConnection();
    }

    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<KlientFx> klientFxList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("KLIENT");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) +  "  " + resultSet.getString(3));
                KlientFx klientFx = new KlientFx();
                klientFx.setId(resultSet.getInt(1));
                klientFx.setImie(resultSet.getString(2));
                klientFx.setNazwisko(resultSet.getString(3));
                klientFx.setEmail(resultSet.getString(4));

                klientFxList.add(klientFx);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return klientFxList;
    }

    public int findKlient(Klient klient){
        ObservableList<KlientFx> klientFxList = selectAll();
        int id = 0;
        for(KlientFx e: klientFxList){
            System.out.println(e);
            if( klient.getImie().equals(e.getImie()) && klient.getNazwisko().equals(e.getNazwisko()) && klient.getEmail().equals(e.getEmail())) {
                id = e.getId();
                break;
            }
        }
        return id;
    }

}
