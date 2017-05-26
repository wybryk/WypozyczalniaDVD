package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.accessories.Converters;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Klient;
import pl.tablesFx.KlientFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class KlientDao  {

    public Klient findKlientById(int id) {
        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("KLIENT", "ID_KLIENTA = " + id);
        Klient klient = new Klient();
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) +  "  " + resultSet.getString(3));
                klient.setId(resultSet.getInt(1));
                klient.setImie(resultSet.getString(2));
                klient.setNazwisko(resultSet.getString(3));
                klient.setEmail(resultSet.getString(4));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return klient;
    }

    public void insertKlient(KlientFx klientFx) {
            Connections.initDataBase();
            Connections.insertRecord("KLIENT", "KlientSeq.NEXTVAL, '" + klientFx.getImie() + "', '" +
                    klientFx.getNazwisko() + "', '" + klientFx.getEmail() + "'");
            Connections.closeConnection();
    }

    public void deleteKlient(int id) {
        Connections.initDataBase();
        Connections.deleteRecord("KLIENT", "ID_KLIENTA = " + id);
        Connections.closeConnection();
    }

    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<Klient> klientList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("KLIENT");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) +  "  " + resultSet.getString(3));
                Klient klient = new Klient();
                klient.setId(resultSet.getInt(1));
                klient.setImie(resultSet.getString(2));
                klient.setNazwisko(resultSet.getString(3));
                klient.setEmail(resultSet.getString(4));

                klientList.add(klient);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return klientList;
    }

    public int findKlient(KlientFx klientFx){
        ObservableList<Klient> klientList = selectAll();
        int id = 0;
        for(Klient e: klientList){
            //System.out.println(e);
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
