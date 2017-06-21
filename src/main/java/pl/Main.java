package pl;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pl.accessories.LoggerClass;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    private static final String LOG_FXML = "/logowanie.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(LOG_FXML));
        primaryStage.setTitle("Wypozyczalnia DVD");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.setMinHeight(480);
        primaryStage.setMaxHeight(768);
        primaryStage.setMinWidth(640);
        primaryStage.setMaxWidth(1024);
        primaryStage.show();
        LoggerClass logger = new LoggerClass();
        logger.logFileAndConsole("info", "START APLIKACJI");
    }

    public static void main(String[] args) throws IOException, SQLException {launch(args);}
}
