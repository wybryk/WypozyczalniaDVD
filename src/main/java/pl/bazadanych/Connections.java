package pl.bazadanych;

import java.sql.*;
import java.sql.Connection;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Connections {

    static Connection conn;
    static Statement stmt;

    public static void initDataBase(){
        forName();
        getConnection();
    }

    public  static void forName(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    public  static void closeConnection(){
        try {
            conn.close();
            System.out.println("Disconnected database successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
