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

    public void insertKlient(KlientFx klientFx) {
            Connections.initDataBase();
            Connections.insertRecord("KLIENT", "KlientSeq.NEXTVAL, '" + klientFx.getImie() + "', '" +
                    klientFx.getNazwisko() + "', '" + klientFx.getEmail() + "'");
            Connections.closeConnection();
    }

    public void deleteKlient(KlientFx klientFx) {
        Connections.initDataBase();
        Connections.deleteRecord("KLIENT", "ID_KLIENTA = " + klientFx.getId());
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

    public int findKlient(KlientFx klientFx){
        ObservableList<KlientFx> klientFxList = selectAll();
        int id = 0;
        for(KlientFx e: klientFxList){
            System.out.println(e);
            if( klientFx.getImie().equals(e.getImie()) && klientFx.getNazwisko().equals(e.getNazwisko()) &&
                    klientFx.getEmail().equals(e.getEmail())) {
                id = e.getId();
                break;
            }
        }
        return id;
    }

    public void updateKlient(KlientFx klientFx){
        Connections.initDataBase();
        Connections.updateRecord("KLIENT", "IMIE = '" + klientFx.getImie() + "', NAZWISKO = '" + klientFx.getNazwisko()
                + "', EMAIL = '" + klientFx.getEmail() + "'"," ID_KLIENTA = " + klientFx.getId());
        Connections.closeConnection();
    }

}
