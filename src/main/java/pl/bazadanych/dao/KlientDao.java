package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.accessories.Converters;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Klient;
import pl.tablesFx.KlientFx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class KlientDao  {

    public Klient findKlientById(Klient klient) {
        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("KLIENT", "ID_KLIENTA = " + klient.getId());
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

    public void insertKlient(Klient klient) {
            Connections.initDataBase();
            Connections.insertRecord("KLIENT", "KlientSeq.NEXTVAL, '" + klient.getImie() + "', '" +
                    klient.getNazwisko() + "', '" + klient.getEmail() + "'");
            Connections.closeConnection();
    }

    public void deleteKlient(Klient klient) {
        Connections.initDataBase();
        Connections.deleteRecord("KLIENT", "ID_KLIENTA = " + klient.getId());
        Connections.closeConnection();
    }

    public List selectAll(){
        Connections.initDataBase();
        List<Klient> klientList = new ArrayList<>();
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

    public Klient findKlient(Klient klient){
        List<Klient> klientList = selectAll();
        int id = 0;
        for(Klient e: klientList){
            //System.out.println(e);
            if( klient.getImie().equals(e.getImie()) && klient.getNazwisko().equals(e.getNazwisko()) &&
                    klient.getEmail().equals(e.getEmail())) {
                id = e.getId();
                break;
            }
        }
        klient.setId(id);
        return klient;
    }

    public void updateKlient(Klient klient){
        Connections.initDataBase();
        Connections.updateRecord("KLIENT", "IMIE = '" + klient.getImie() + "', NAZWISKO = '" + klient.getNazwisko()
                + "', EMAIL = '" + klient.getEmail() + "'"," ID_KLIENTA = " + klient.getId());
        Connections.closeConnection();
    }

}
