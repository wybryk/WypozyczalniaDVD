package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Film;
import pl.tablesFx.FilmFx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List selectAllFilm(){
        List<Film> filmList = new ArrayList<>();
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

    public Film findFilm(Film film){
        List<Film> filmList = selectAllFilm();
        String nazwa = film.getNazwa();
        int id = 0;
        for(Film e: filmList){
            if( nazwa.equals(e.getNazwa())) {
                id = e.getId();
                break;
            }
        }
        film.setId(id);
        return film;
    }

    public void deleteFilm(Film film){
        Connections.initDataBase();
        System.out.println(film.getId());
        Connections.deleteRecord("FILM", "ID_FILMU = " + film.getId());
        System.out.println(film.getId());
        Connections.closeConnection();
    }

    public void updateFilm(Film film){
        Connections.initDataBase();
        Connections.updateRecord("FILM", "NAZWA = '"+film.getNazwa()+"', OPIS = '"+film.getOpis()+"', ILOSC = "+
                film.getIlosc()+", PREMIERA = '" +film.getPremiera()+"', ID_GATUNKU = "+film.getGatunek()+", ID_REZYSERA = "+
                film.getRezyser(), " Id_FILMU = " + film.getId());
        Connections.closeConnection();
    }

}
