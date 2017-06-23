package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Rezyser;
import pl.tablesFx.RezyserFx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class RezyserDao{

    public Rezyser findRezyserById(Rezyser rezyser) {

        Connections.initDataBase();

        ResultSet resultSet = Connections.selectRecords("REZYSER", "ID_REZYSERA = " + rezyser.getId());
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

    public List selectAll(){
        List<Rezyser> rezyserList= new ArrayList<>();
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

    public Rezyser findRezyser(Rezyser rezyser){
        List<Rezyser> rezyserList = selectAll();
        String rezyserNazwa = rezyser.getNazwa();
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
        rezyser.setId(id);
        return rezyser;
    }

    public int insertRezyser(String rezyserNazwa) {
        Connections.initDataBase();
        Connections.insertRecord("REZYSER", "RezyserSeq.NEXTVAL, '"+rezyserNazwa+"'");
        Connections.closeConnection();
        return 0;
    }
}
