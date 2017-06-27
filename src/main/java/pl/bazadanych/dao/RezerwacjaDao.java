package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Rezerwacja;
import pl.tablesFx.FilmFx;
import pl.tablesFx.RezerwacjaFX;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Klasa łącząca z tabelą Rezerwacje w BD</h2>
 * <p>Zawiera metody do pobierania i wstawiania elementów z/do bazy.</p>
 */
public class RezerwacjaDao {
    /**
     * Metoda pobierająca rekordy z tabeli REZERWACJE
     * @return obiekt typu List
     */
    public List<Rezerwacja> selectAll(){
        Connections.initDataBase();
        List<Rezerwacja> rezerwacjaList = new ArrayList<>();
        ResultSet resultSet = Connections.selectRecords("REZERWACJE");
        try{
            while (resultSet.next()) {
                Rezerwacja rezerwacja = new Rezerwacja();
                rezerwacja.setId(resultSet.getInt(1));
                rezerwacja.setIdFilmu(resultSet.getInt(2));
                rezerwacja.setIdKlienta(resultSet.getInt(3));

                rezerwacjaList.add(rezerwacja);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connections.closeConnection();
        return rezerwacjaList;
    }
    /**
     * Metoda wstawiająca dane do tabeli REZERWACJE
     * @param rezerwacja parametr będący obiektem typu Rezerwacja
     */
    public void insertRezerwacje(Rezerwacja rezerwacja) {
        Connections.initDataBase();
        Connections.insertRecord("REZERWACJE", "RezerwacjeSeq.NEXTVAL, " + rezerwacja.getIdFilmu() + ", " +
                rezerwacja.getIdKlienta());
        Connections.closeConnection();
    }
    /**
     * Metoda usuwająca dane z tabeli REZERWACJE, używając identyfikatora rezerwacji.
     * @param rezerwacja parametr będący obiektem typu Rezerwacja
     */
    public void deleteRezerwacja(Rezerwacja rezerwacja){
        Connections.initDataBase();
        Connections.deleteRecord("REZERWACJE", "ID_REZERW = " + rezerwacja.getId());
        Connections.closeConnection();
    }
}
