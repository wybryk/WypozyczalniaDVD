package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Egzemplarz;
import pl.tablesFx.EgzemplarzFx;
import pl.tablesFx.FilmFx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class EgzemplarzDao {

    public void insertEgzemplarz(Egzemplarz egzemplarz ){
        Connections.initDataBase();
        Connections.insertRecord("EGZEMPLARZ", "EgzemplarzSeq.NEXTVAL, " + egzemplarz.getIdFilmu());
        Connections.closeConnection();
    }
    public void deleteEgzemplarzByIdFilmu(Egzemplarz egzemplarz){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_FILMU = " + egzemplarz.getIdFilmu());
        Connections.closeConnection();
    }
    public void deleteEgzemplarzByIdEgzemplarzu(Egzemplarz egzemplarz){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_EGZEMPLARZU = " + egzemplarz.getId());
        Connections.closeConnection();
    }
    public List selectAll(){
        Connections.initDataBase();
        List<Egzemplarz> egzemplarzList = new ArrayList<>();
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
