package pl.bazadanych.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Wypozyczenie;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class WypozyczenieDao {
    /**
     * Metoda wstawiająca dane do tabeli WYPOZYCZENIE.
     */
    public void insertWypozyczenie() {
        Connections.initDataBase();
        Connections.insertRecord("WYPOZYCZENIE", "WypozyczenieSeq.NEXTVAL, SYSDATE, SYSDATE+7");
        Connections.closeConnection();
    }
    /**
     * Metoda pobierająca rekordy z tabeli WYPOZYCZENIE.
     * @return obiekt typu ObservableList
     */
    public ObservableList selectAll(){
        ObservableList<Wypozyczenie> wypozyczeniesList = FXCollections.observableArrayList();
        Connections.initDataBase();
        ResultSet resultSet = Connections.selectRecords("WYPOZYCZENIE");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getDate(2) +  "  " + resultSet.getDate(3));
                Wypozyczenie wypozyczenie = new Wypozyczenie();
                wypozyczenie.setId(resultSet.getInt(1));
                wypozyczenie.setDataWypozyczenia(resultSet.getDate(2));
                wypozyczenie.setDataOddania(resultSet.getDate(3));

                wypozyczeniesList.add(wypozyczenie);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return wypozyczeniesList;
    }
    /**
     * Metoda znajdująca ostatni wstawiony rekord w tabeli WYPOZYCZENIE.
     * @return id rekordu
     */
    public int selectMaxId(){
        int id = 0;
        Connections.initDataBase();
        ResultSet resultSet = Connections.selectMaxId("WYPOZYCZENIE", "ID_WYPOZYCZENIA");
        try{
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                id = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return id;
    }
}
