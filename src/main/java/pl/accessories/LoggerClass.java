package pl.accessories;

import org.apache.log4j.*;

import java.io.IOException;
import java.util.SplittableRandom;
import java.util.logging.Logger;

/**
 * <h2>Klasa do tworzenia logów</h2>
 * <p>Korzysta z log4j do logowania eventów.</p>
 */
public class LoggerClass {
    private org.apache.log4j.Logger logger;
    private org.apache.log4j.Logger fileLogger;
    private Layout layout;
    private Appender appender;
    private Appender fileAppender;
    /**
     * Konstruktor, tworzy nowy Layout dla logów i obiekt typu Logger.
     */
    public LoggerClass() {
        layout = new PatternLayout("[%p] %c - %m - Data wpisu: %d %n");
        this.logger = org.apache.log4j.Logger.getRootLogger();
    }
    /**
     * Metoda wypisująca logi zarówno do pliku jak i na konsolę.
     * @param mode parametr będący typem logu który zapisujemy
     * @param msg parametr będący treścią zapisywanego logu
     */
    public void logFileAndConsole(String mode, String msg) {
        logToConsole(mode, msg);
        logToFile(mode, msg);
    }
    /**
     * Metoda wypisująca logi do pliku.
     * @param mode parametr będący typem logu który zapisujemy
     * @param msg parametr będący treścią zapisywanego logu
     */
    public void logToFile(String mode, String msg) {
        try {
            fileAppender = new FileAppender(layout, "D:/Code/WypozyczalniaDVD/src/app-logs.txt");
        }
        catch (IOException e) {
            System.out.println("-- File error: --" + e + "--");
        }
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(fileAppender);
        log(mode, msg);
    }
    /**
     * Metoda wypisująca logi na konsolę.
     * @param mode parametr będący typem logu który zapisujemy
     * @param msg parametr będący treścią zapisywanego logu
     */
    public void logToConsole(String mode, String msg) {
        appender = new ConsoleAppender(layout);
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(appender);
        log(mode, msg);
    }
    /**
     * Metoda wypisująca logi.
     * @param mode parametr będący typem logu który zapisujemy
     * @param msg parametr będący treścią zapisywanego logu
     */
    private void log(String mode, String msg) {
        switch ( mode ) {
            case "error":
                logger.setLevel(Level.ERROR);
                logger.error(msg);
                break;
            case "trace":
                logger.setLevel(Level.TRACE);
                logger.trace(msg);
                break;
            case "info":
                logger.setLevel(Level.INFO);
                logger.info(msg);
                break;
            default:
                logger.setLevel(Level.DEBUG);
                logger.debug(msg);
                break;
        }
    }
}
