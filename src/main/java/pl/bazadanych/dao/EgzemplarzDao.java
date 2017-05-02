package pl.bazadanych.dao;

import pl.bazadanych.Connections;
import pl.bazadanych.tables.Egzemplarz;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class EgzemplarzDao {

    public void insertEgzemplarz(int n, int id ){
        Egzemplarz egzemplarz = new Egzemplarz();

        egzemplarz.setIdFilmu(id);
        Connections.initDataBase();
        for(int i = 0; i < n; i++)
            Connections.insertRecord("EGZEMPLARZ", "EgzemplarzSeq.NEXTVAL, " + egzemplarz.getIdFilmu());
        Connections.closeConnection();
    }
}
