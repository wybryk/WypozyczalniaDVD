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

    public void insertFilm(FilmFx filmFx){
        Connections.initDataBase();
        Connections.insertRecord("FILM", "FilmSeq.NEXTVAL, '"+filmFx.getNazwa()+"', '"+filmFx.getOpis()+"', "+filmFx.getIlosc()+", '"
                +filmFx.getPremiera()+"', "+filmFx.getGatunekFx()+", "+filmFx.getRezyserFx());
        Connections.closeConnection();
    }

    public ObservableList selectAllFilm(){
        ObservableList<Film> filmList = FXCollections.observableArrayList();
        Connections.initDataBase();
        ResultSet resultSet = Connections.selectRecords("FILM");
        try{
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) +  "  " + resultSet.getString(3));
                Film film = new Film();
                film.setId(resultSet.getInt(1));
                film.setNazwa(resultSet.getString(2));
                film.setOpis(resultSet.getString(3));
                film.setIlosc(resultSet.getInt(4));
                film.setPremiera(resultSet.getDate(5));
                film.setGatunek(resultSet.getInt(6));
                film.setRezyser(resultSet.getInt(7));
                filmList.add(film);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return filmList;
    }

    public int findFilm(String nazwa){
        ObservableList<Film> filmList = selectAllFilm();
        int id = 0;
        for(Film e: filmList){
            if( nazwa.equals(e.getNazwa())) {
                id = e.getId();
                break;
            }
        }
        return id;
    }

    public void deleteFilm(FilmFx filmFx){
        Connections.initDataBase();
        Connections.deleteRecord("FILM", "ID_FILMU = " + filmFx.getId());
        Connections.closeConnection();
    }

    public void updateFilm(FilmFx filmFx){
        Connections.initDataBase();
        Connections.updateRecord("FILM", "NAZWA = '"+filmFx.getNazwa()+"', OPIS = '"+filmFx.getOpis()+"', ILOSC = "+
                filmFx.getIlosc()+", PREMIERA = '" +filmFx.getPremiera()+"', ID_GATUNKU = "+filmFx.getGatunekFx()+", ID_REZYSERA = "+
                filmFx.getRezyserFx(), " Id_FILMU = " + filmFx.getId());
        Connections.closeConnection();
    }

}
