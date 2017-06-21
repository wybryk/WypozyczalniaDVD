package pl.accessories;

import org.apache.log4j.*;

import java.io.IOException;
import java.util.SplittableRandom;
import java.util.logging.Logger;

/**
 * Created by Damian on 2017-06-20.
 */
public class LoggerClass {
    private org.apache.log4j.Logger logger;
    private org.apache.log4j.Logger fileLogger;
    private Layout layout;
    private Appender appender;
    private Appender fileAppender;

    public LoggerClass() {
        layout = new PatternLayout("[%p] %c - %m - Data wpisu: %d %n");
        this.logger = org.apache.log4j.Logger.getRootLogger();
    }
    public void logFileAndConsole(String mode, String msg) {
        logToConsole(mode, msg);
        logToFile(mode, msg);
    }
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
    public void logToConsole(String mode, String msg) {
        appender = new ConsoleAppender(layout);
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure(appender);
        log(mode, msg);
    }
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
