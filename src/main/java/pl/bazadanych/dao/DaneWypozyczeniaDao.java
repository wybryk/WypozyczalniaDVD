package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.DaneWypozyczenia;
import pl.tablesFx.DaneWypozyczeniaFx;
import pl.tablesFx.KlientFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class DaneWypozyczeniaDao{

    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<DaneWypozyczenia> daneWypozyczeniaList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("DANE_WYPOZYCZENIA");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getInt(2) +  "  " + resultSet.getInt(3));
                DaneWypozyczenia daneWypozyczenia = new DaneWypozyczenia();
                daneWypozyczenia.setIdEgzemplarzu(resultSet.getInt(1));
                daneWypozyczenia.setIdKlienta(resultSet.getInt(2));
                daneWypozyczenia.setIdWypozyczenia(resultSet.getInt(3));

                daneWypozyczeniaList.add(daneWypozyczenia);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return daneWypozyczeniaList;
    }

    public void insertDaneWypozyczenia(DaneWypozyczeniaFx daneWypozyczeniaFx){
        Connections.initDataBase();
        Connections.insertRecord("DANE_WYPOZYCZENIA", daneWypozyczeniaFx.getIdEgzemplarzu() + ", "
                + daneWypozyczeniaFx.getIdKlienta() + ", " + daneWypozyczeniaFx.getIdWypozyczenia());
        Connections.closeConnection();
    }
}
