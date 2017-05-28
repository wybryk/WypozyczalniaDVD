package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Egzemplarz;
import pl.tablesFx.EgzemplarzFx;
import pl.tablesFx.FilmFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class EgzemplarzDao {

    public void insertEgzemplarz(int n, int id ){
        Connections.initDataBase();
        for(int i = 0; i < n; i++)
            Connections.insertRecord("EGZEMPLARZ", "EgzemplarzSeq.NEXTVAL, " + id);
        Connections.closeConnection();
    }
    public void deleteEgzemplarzByIdFilmu(FilmFx filmFx){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_FILMU = " + filmFx.getId());
        Connections.closeConnection();
    }
    public void deleteEgzemplarzByIdEgzemplarzu(int id){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_EGZEMPLARZU = " + id);
        Connections.closeConnection();
    }
    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<Egzemplarz> egzemplarzList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("EGZEMPLARZ");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getInt(2) +  "  " + resultSet.getInt(3));
                Egzemplarz egzemplarz = new Egzemplarz();
                egzemplarz.setId(resultSet.getInt(1));
                egzemplarz.setIdFilmu(resultSet.getInt(2));

                egzemplarzList.add(egzemplarz);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return egzemplarzList;
    }

}
