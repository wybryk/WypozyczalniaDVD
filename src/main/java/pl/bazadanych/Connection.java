package pl.bazadanych;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.bazadanych.tables.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-20.
 */
public class Connection {

    private static String JDBC_DRIVER_SQLITE = "jdbc:sqlite:databaseTest.db";
    private static ConnectionSource connectionSource;
    public static final Logger LOGGER = LoggerFactory.getLogger(Connection.class);

    public static void initDataBase() {
        connect();
        //dropTable();
        createTable();
        disconnect();
    }
    private static void connect() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null)
            connect();
        return connectionSource;
    }

    public static void disconnect() {
        if (connectionSource != null)
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
    }

    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Gatunek.class);
            TableUtils.createTableIfNotExists(connectionSource, Rezyser.class);
            TableUtils.createTableIfNotExists(connectionSource, Film.class);
            TableUtils.createTableIfNotExists(connectionSource, Egzemplarz.class);
            TableUtils.createTableIfNotExists(connectionSource, DaneWypozyczenia.class);
            TableUtils.createTableIfNotExists(connectionSource, Wypozyczenie.class);
            TableUtils.createTableIfNotExists(connectionSource, Klient.class);
            TableUtils.createTableIfNotExists(connectionSource, Konto.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Gatunek.class, true);
            TableUtils.dropTable(connectionSource, Rezyser.class, true);
            TableUtils.dropTable(connectionSource, Film.class, true);
            TableUtils.dropTable(connectionSource, Egzemplarz.class, true);
            TableUtils.dropTable(connectionSource, DaneWypozyczenia.class, true);
            TableUtils.dropTable(connectionSource, Wypozyczenie.class, true);
            TableUtils.dropTable(connectionSource, Klient.class, true);
            TableUtils.dropTable(connectionSource, Konto.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
