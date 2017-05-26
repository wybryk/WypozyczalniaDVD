package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Rezyser;
import pl.tablesFx.RezyserFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class RezyserDao{

    public Rezyser findRezyserById(int id) {

        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("REZYSER", "ID_REZYSERA = " + id);
        Rezyser rezyser = new Rezyser();
        try{
            while (resultSet.next()) {
                rezyser.setId(resultSet.getInt(1));
                rezyser.setNazwa(resultSet.getString(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return rezyser;
    }

    public ObservableList selectAll(){
        ObservableList<Rezyser> rezyserList= FXCollections.observableArrayList();
        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("REZYSER");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2));
                Rezyser rezyser = new Rezyser();
                rezyser.setId(resultSet.getInt(1));
                rezyser.setNazwa(resultSet.getString(2));
                rezyserList.add(rezyser);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return rezyserList;
    }

    public int findRezyser(String rezyserNazwa){
        ObservableList<Rezyser> rezyserList = selectAll();
        boolean exist = false;
        int id = 0;

        for(Rezyser e : rezyserList){
            if(rezyserNazwa.equals(e.getNazwa())){
                exist = true;
                id = e.getId();
                break;
            }
            else{
                continue;
            }
        }
        if( exist == false) {
            id = insertRezyser(rezyserNazwa);
        }
        return id;
    }

    public int insertRezyser(String rezyserNazwa) {
        Connections.initDataBase();
        Rezyser rezyser = new Rezyser();
        rezyser.setNazwa(rezyserNazwa);
        Connections.insertRecord("REZYSER", "RezyserSeq.NEXTVAL, '"+rezyser.getNazwa()+"'");
        Connections.closeConnection();
        return 0;
    }
}
