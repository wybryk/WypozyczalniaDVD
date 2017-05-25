package pl.bazadanych.dao;


import pl.bazadanych.Connections;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class WypozyczenieDao {

    public void insertWypozyczenie() {
        Connections.initDataBase();
        Connections.insertRecord("WYPOZYCZENIE", "WypozyczenieSeq.NEXTVAL, SYSDATE, SYSDATE+7");
        Connections.closeConnection();
    }
}
