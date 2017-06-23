package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Film;
import pl.tablesFx.FilmFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h2>Klasa łącząca z tabelą Film w BD</h2>
 * <p>Zawiera metody do pobierania, wyszukiwania, usuwania, edytowania i wstawiania elementów z/do bazy.</p>
 */
public class FilmDao {
    /**
     * Metoda wstawiająca dane do tabeli FILM
     * @param filmFx parametr będący obiektem typu FilmFx
     */
    public void insertFilm(FilmFx filmFx){
        Connections.initDataBase();
        Connections.insertRecord("FILM", "FilmSeq.NEXTVAL, '"+filmFx.getNazwa()+"', '"+filmFx.getOpis()+"', "+filmFx.getIlosc()+", '"
                +filmFx.getPremiera()+"', "+filmFx.getGatunekFx()+", "+filmFx.getRezyserFx());
        Connections.closeConnection();
    }
    /**
     * Metoda pobierająca rekordy z tabeli FILM
     * @return obiekt typu ObservableList
     */
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
    /**
     * Metoda wyszukująca Film po nazwie.
     * @param nazwa tytuł filmu do wyszukania
     * @return int id filmu
     */
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
    /**
     * Metoda usuwająca dane z tabeli FILM, używając pola id obiektu typu FilmFx.
     * @param filmFx parametr obiektem typu FilmFx
     */
    public void deleteFilm(FilmFx filmFx){
        Connections.initDataBase();
        Connections.deleteRecord("FILM", "ID_FILMU = " + filmFx.getId());
        Connections.closeConnection();
    }
    /**
     * Metoda aktualizująca dane w tabeli FILM.
     * @param filmFx parametr obiektem typu FilmFx
     */
    public void updateFilm(FilmFx filmFx){
        Connections.initDataBase();
        Connections.updateRecord("FILM", "NAZWA = '"+filmFx.getNazwa()+"', OPIS = '"+filmFx.getOpis()+"', ILOSC = "+
                filmFx.getIlosc()+", PREMIERA = '" +filmFx.getPremiera()+"', ID_GATUNKU = "+filmFx.getGatunekFx()+", ID_REZYSERA = "+
                filmFx.getRezyserFx(), " Id_FILMU = " + filmFx.getId());
        Connections.closeConnection();
    }

}
