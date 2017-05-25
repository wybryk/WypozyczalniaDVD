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
        Egzemplarz egzemplarz = new Egzemplarz();

        egzemplarz.setIdFilmu(id);
        Connections.initDataBase();
        for(int i = 0; i < n; i++)
            Connections.insertRecord("EGZEMPLARZ", "EgzemplarzSeq.NEXTVAL, " + egzemplarz.getIdFilmu());
        Connections.closeConnection();
    }
    public void deleteEgzemplarz(FilmFx filmFx){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_FILMU = " + filmFx.getId());
        Connections.closeConnection();
    }
    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<EgzemplarzFx> egzemplarzFxList = FXCollections.observableArrayList();
        ResultSet resultSet = Connections.selectRecords("EGZEMPLARZ");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getInt(2) +  "  " + resultSet.getInt(3));
                EgzemplarzFx egzemplarzFx = new EgzemplarzFx();
                egzemplarzFx.setId(resultSet.getInt(1));
                egzemplarzFx.setIdFilmu(resultSet.getInt(2));

                egzemplarzFxList.add(egzemplarzFx);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return egzemplarzFxList;
    }

}
