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
 * <h2>Klasa łącząca z tabelą Rezyser w BD</h2>
 * <p>Zawiera metody do pobierania, szukania i wstawiania elementów z/do bazy.</p>
 */
public class RezyserDao{

    /**
     * Metoda wyszukująca Reżysera po id.
     * @param rezyser parametr będący obiektem typu Rezyser
     * @return obiekt typu Rezyser
     */
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
    /**
     * Metoda pobierająca rekordy z tabeli REZYSER
     * @return obiekt typu ObservableList
     */
    public List<Rezyser> selectAll(){
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
    /**
     * Metoda wyszukująca Reżysera po nazwie.
     * @param rezyser parametr będący obiektem typu Rezyser
     * @return obiekt typu Rezyser
     */
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
    /**
     * Metoda wstawiająca dane do tabeli REZYSER
     * @param rezyserNazwa parametr będący obiektem typu String - Nazwa rezysera
     * @return wartość typu int
     */
    private int insertRezyser(String rezyserNazwa) {
        Connections.initDataBase();
        Connections.insertRecord("REZYSER", "RezyserSeq.NEXTVAL, '"+rezyserNazwa+"'");
        Connections.closeConnection();
        return 0;
    }
}
