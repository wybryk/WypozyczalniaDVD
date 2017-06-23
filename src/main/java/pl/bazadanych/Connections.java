package pl.bazadanych;

import java.sql.*;
import java.sql.Connection;

/**
 * <h2>Klasa do nazwiązywania połączeń z BD</h2>
 * <p>Zawiera metody wykorzystyjące kwerendy SQL do komunikacji z bazą danych.</p>
 */
public class Connections {

    static Connection conn;
    static Statement stmt;
    /**
     * Metoda inicjuje połączenie z bazą danych.
     */
    public static void initDataBase(){
        forName();
        getConnection();
    }
    /**
     * Metoda wykorzystywana przez metodę initDataBase do nawiązywania połączenia z BD.
     */
    public  static void forName(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda wykorzystywana przez metodę initDataBase do nawiązywania połączenia z BD.
     */
    public  static void getConnection(){
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String USER = "hr";
        String PASS = "hr";
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connecting to a selected database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * Metoda wykorzystywana do zamknięcia połączenia z bazą danych.
     */
    public  static void closeConnection(){
        try {
            conn.close();
            System.out.println("Disconnected database successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda pobierająca wszystkie rekordy z tabeli podanej przez argument.
     * @param table String nazwa tabeli
     * @return obiekt typu ResultSet bądź null
     */
    public static ResultSet selectRecords( String table ) {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM " + table;
            System.out.println("Selecting values...");
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Metoda pobierająca rekordy z tabeli podanej przez argument, spełniające warunek podany przez argument.
     * @param table String nazwa tabeli
     * @param query String warunek dla danych
     * @return obiekt typu ResultSet bądź null
     */
    public static ResultSet selectRecords( String table, String query) {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM " + table + " WHERE " + query;
            System.out.println("Selecting values...");
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Metoda pobierająca maksymalny rekord z tabeli podanej przez argument, spełniający warunek podany przez argument.
     * @param table String nazwa tabeli
     * @param query String warunek dla danych
     * @return obiekt typu ResultSet bądź null
     */
    public static ResultSet selectMaxId( String table, String query) {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT Max(" + query + ") FROM " + table;
            System.out.println("Selecting values...");
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Metoda dodająca rekord do danej tabeli bazy danych.
     * @param table String nazwa tabeli
     * @param values String wartości do rekordu
     */
    public static void insertRecord( String table, String values){
        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO " + table + " VALUES (" + values +")";
            stmt.executeUpdate(sql);
            System.out.println("Insert values...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda usuwająca rekord z danej tabeli bazy danych.
     * @param table String nazwa tabeli
     * @param values String wartości do rekordu
     */
    public static void deleteRecord(String table, String values){
        try {
            stmt = conn.createStatement();
            String sql = "DELETE FROM " + table + " WHERE " + values ;
            stmt.executeUpdate(sql);
            System.out.println("Delete values...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda aktualizująca rekord w danej tabeli bazy danych.
     * @param table String nazwa tabeli
     * @param values String wartości do rekordu
     * @param warunek String warunek wstawienia
     */
    public static void updateRecord(String table, String values, String warunek){
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE " + table + " SET " + values + " WHERE " + warunek;
            stmt.executeUpdate(sql);
            System.out.println("Update values...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
