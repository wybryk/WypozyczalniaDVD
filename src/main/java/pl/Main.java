package pl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/GUI.fxml"));
        primaryStage.setTitle("Wypozyczalnia DVD");
        primaryStage.setScene(new Scene(root, 630, 428));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, SQLException {launch(args);}
}
