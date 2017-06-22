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
 * <h2>Klasa łącząca z tabelą Egzemplarz w BD</h2>
 * <p>Zawiera metody do pobierania, usuwania i wstawiania elementów z/do bazy.</p>
 */
public class EgzemplarzDao {
    /**
     * Metoda wstawiająca dane do tabeli EGZEMPLARZ
     * @param n parametr będący wartością typu int
     * @param id parametr będący identyfikatorem rekordu
     */
    public void insertEgzemplarz(int n, int id ){
        Connections.initDataBase();
        for(int i = 0; i < n; i++)
            Connections.insertRecord("EGZEMPLARZ", "EgzemplarzSeq.NEXTVAL, " + id);
        Connections.closeConnection();
    }
    /**
     * Metoda usuwająca dane z tabeli EGZEMPLARZ, używając pola id obiektu typu FilmFx.
     * @param filmFx parametr obiektem typu FilmFx
     */
    public void deleteEgzemplarzByIdFilmu(FilmFx filmFx){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_FILMU = " + filmFx.getId());
        Connections.closeConnection();
    }
    /**
     * Metoda usuwająca dane z tabeli EGZEMPLARZ, używając id Egzemplarza
     * @param id identyfikator egzemplarza
     */
    public void deleteEgzemplarzByIdEgzemplarzu(int id){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_EGZEMPLARZU = " + id);
        Connections.closeConnection();
    }
    /**
     * Metoda pobierająca rekordy z tabeli EGZEMPLARZ
     * @return obiekt typu ObservableList
     */
    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<Egzemplarz> egzemplarzList = FXCollections.observableArrayList();
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
