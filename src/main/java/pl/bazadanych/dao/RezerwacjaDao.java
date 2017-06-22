package pl.bazadanych.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bazadanych.Connections;
import pl.bazadanych.tables.Rezerwacja;
import pl.tablesFx.FilmFx;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h2>Klasa łącząca z tabelą Rezerwacje w BD</h2>
 * <p>Zawiera metody do pobierania i wstawiania elementów z/do bazy.</p>
 */
public class RezerwacjaDao {
    /**
     * Metoda pobierająca rekordy z tabeli REZERWACJE
     * @return obiekt typu ObservableList
     */
    public ObservableList selectAll(){
        Connections.initDataBase();
        ObservableList<Rezerwacja> rezerwacjaList = FXCollections.observableArrayList();
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
     * @param filmFx parametr będący obiektem typu FilmFx
     * @param klientID parametr będący identyfikatorem klienta który złożył rezerwację
     */
    public void insertRezerwacje(FilmFx filmFx, int klientID) {
        Connections.initDataBase();
        Connections.insertRecord("REZERWACJE", "RezerwacjeSeq.NEXTVAL," + filmFx.getId() + "," + klientID + "");
        Connections.closeConnection();
    }
}
