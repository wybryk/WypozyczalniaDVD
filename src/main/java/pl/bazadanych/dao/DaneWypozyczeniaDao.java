package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
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
        ObservableList<DaneWypozyczeniaFx> daneWypozyczeniaFxList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("DANE_WYPOZYCZENIA");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getInt(2) +  "  " + resultSet.getInt(3));
                DaneWypozyczeniaFx daneWypozyczeniaFx = new DaneWypozyczeniaFx();
                daneWypozyczeniaFx.setIdEgzemplarzu(resultSet.getInt(1));
                daneWypozyczeniaFx.setIdKlienta(resultSet.getInt(2));
                daneWypozyczeniaFx.setIdWypozyczenia(resultSet.getInt(3));

                daneWypozyczeniaFxList.add(daneWypozyczeniaFx);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return daneWypozyczeniaFxList;
    }
}
