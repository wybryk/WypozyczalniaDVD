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
 * <h2>Klasa łącząca z tabelą Film w BD</h2>
 * <p>Zawiera metody do pobierania, wyszukiwania, usuwania, edytowania i wstawiania elementów z/do bazy.</p>
 */
public class FilmDao {
    /**
     * Metoda wstawiająca dane do tabeli FILM
     * @param film parametr będący obiektem typu Film
     */
    public void insertFilm(Film film){
        Connections.initDataBase();
        Connections.insertRecord("FILM", "FilmSeq.NEXTVAL, '"+film.getNazwa()+"', '"+film.getOpis()+"', "+film.getIlosc()+", '"
                +film.getPremiera()+"', "+film.getGatunek()+", "+film.getRezyser());
        Connections.closeConnection();
    }
    /**
     * Metoda pobierająca rekordy z tabeli FILM
     * @return obiekt typu List
     */
    public List<Film> selectAllFilm(){
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
    /**
     * Metoda wyszukująca Film po nazwie.
     * @param film parametr będący obiektem typu Film
     * @return film parametr będący obiektem typu Film
     */
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
    /**
     * Metoda usuwająca dane z tabeli FILM, używając pola id obiektu typu Film.
     * @param film parametr obiektem typu Film
     */
    public void deleteFilm(Film film){
        Connections.initDataBase();
        Connections.deleteRecord("FILM", "ID_FILMU = " + film.getId());
        Connections.closeConnection();
    }
    /**
     * Metoda aktualizująca dane w tabeli FILM.
     * @param film parametr obiektem typu Film
     */
    public void updateFilm(Film film){
        Connections.initDataBase();
        Connections.updateRecord("FILM", "NAZWA = '"+film.getNazwa()+"', OPIS = '"+film.getOpis()+"', ILOSC = "+
                film.getIlosc()+", PREMIERA = '" +film.getPremiera()+"', ID_GATUNKU = "+film.getGatunek()+", ID_REZYSERA = "+
                film.getRezyser(), " Id_FILMU = " + film.getId());
        Connections.closeConnection();
    }

}
