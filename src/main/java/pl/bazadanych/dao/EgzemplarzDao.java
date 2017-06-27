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
 * <h2>Klasa łącząca z tabelą Egzemplarz w BD</h2>
 * <p>Zawiera metody do pobierania, usuwania i wstawiania elementów z/do bazy.</p>
 */
public class EgzemplarzDao {
    /**
     * Metoda wstawiająca dane do tabeli EGZEMPLARZ
     * @param egzemplarz parametr będący obiektem typu Egzemplarz
     */
    public void insertEgzemplarz(Egzemplarz egzemplarz ){
        Connections.initDataBase();
        Connections.insertRecord("EGZEMPLARZ", "EgzemplarzSeq.NEXTVAL, " + egzemplarz.getIdFilmu());
        Connections.closeConnection();
    }
    /**
     * Metoda usuwająca dane z tabeli EGZEMPLARZ
     * @param egzemplarz parametr obiektem typu Egzemplarz
     */
    public void deleteEgzemplarzByIdFilmu(Egzemplarz egzemplarz){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_FILMU = " + egzemplarz.getIdFilmu());
        Connections.closeConnection();
    }
    /**
     * Metoda usuwająca dane z tabeli EGZEMPLARZ, używając id Egzemplarza
     * @param egzemplarz parametr obiektem typu Egzemplarz
     */
    public void deleteEgzemplarzByIdEgzemplarzu(Egzemplarz egzemplarz){
        Connections.initDataBase();
        Connections.deleteRecord("EGZEMPLARZ", "ID_EGZEMPLARZU = " + egzemplarz.getId());
        Connections.closeConnection();
    }
    /**
     * Metoda pobierająca rekordy z tabeli EGZEMPLARZ
     * @return obiekt typu List
     */
    public List<Egzemplarz> selectAll(){
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
