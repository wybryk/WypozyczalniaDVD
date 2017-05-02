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

    public RezyserFx findRezyserById(int id) {

        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("REZYSER", "ID_REZYSER = " + id);
        RezyserFx rezyserFx = new RezyserFx();
        try{
            rezyserFx.setId(resultSet.getInt(1));
            rezyserFx.setNazwa(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return rezyserFx;
    }

    public ObservableList selectAll(){
        ObservableList<RezyserFx> rezyserFxList= FXCollections.observableArrayList();
        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("REZYSER");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2));
                RezyserFx rezyserFx = new RezyserFx();
                rezyserFx.setId(resultSet.getInt(1));
                rezyserFx.setNazwa(resultSet.getString(2));
                rezyserFxList.add(rezyserFx);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return rezyserFxList;
    }

    public int findRezyser(String rezyserNazwa){
        ObservableList<RezyserFx> rezyserFxList = selectAll();
        boolean exist = false;
        int id = 0;

        for(RezyserFx e : rezyserFxList){
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
