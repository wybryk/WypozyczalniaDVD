package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Film;
import pl.tablesFx.FilmFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class FilmDao {

    public void insertFilm(Film film){
        Connections.initDataBase();
        Connections.insertRecord("FILM", "FilmSeq.NEXTVAL, '"+film.getNazwa()+"', '"+film.getOpis()+"', "+film.getIlosc()+", '"
                +film.getPremiera()+"', "+film.getGatunek()+", "+film.getRezyser());
        Connections.closeConnection();
    }

    public ObservableList selectAllFilm(){
        ObservableList<FilmFx> filmFxList = FXCollections.observableArrayList();
        Connections.initDataBase();
        ResultSet resultSet = Connections.selectRecords("FILM");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) +  "  " + resultSet.getString(3));
                FilmFx filmFx = new FilmFx();
                filmFx.setId(resultSet.getInt(1));
                filmFx.setNazwa(resultSet.getString(2));
                filmFx.setOpis(resultSet.getString(3));
                filmFx.setIlosc(resultSet.getInt(4));
                filmFx.setPremiera(resultSet.getDate(5));
                filmFx.setGatunekFx(resultSet.getInt(6));
                filmFx.setRezyserFx(resultSet.getInt(7));
                filmFxList.add(filmFx);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return filmFxList;
    }

    public int findFilm(String nazwa){
        ObservableList<FilmFx> filmFxList = selectAllFilm();
        int id = 0;
        for(FilmFx e: filmFxList){
            if( nazwa.equals(e.getNazwa())) {
                id = e.getId();
                break;
            }
        }
        return id;
    }

}
