package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.DaneWypozyczenia;
import pl.tablesFx.DaneWypozyczeniaFx;
import pl.tablesFx.KlientFx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class DaneWypozyczeniaDao{

    public List selectAll(){
        Connections.initDataBase();
        List<DaneWypozyczenia> daneWypozyczeniaList = new ArrayList<>();
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

    public void insertDaneWypozyczenia(DaneWypozyczenia daneWypozyczenia){
        Connections.initDataBase();
        Connections.insertRecord("DANE_WYPOZYCZENIA", daneWypozyczenia.getIdEgzemplarzu() + ", "
                + daneWypozyczenia.getIdKlienta() + ", " + daneWypozyczenia.getIdWypozyczenia());
        Connections.closeConnection();
    }
}
