package pl.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pl.bazadanych.Connections;
import pl.tablesFx.GatunekFx;
import pl.tablesFx.KontoFx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h2>Klasa głównego kontrolera</h2>
 * <p>Zawiera podstawowe metody i jest abstrakcyjna.</p>
 */
public abstract class BaseController {

    /**
     * Metoda zmieniająca okno programu.
     * @param event Event zdarzenie wywołujące metodę
     * @param path String ścieżka do pliku xml
     */
    public void changeWindow(Event event, String path){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle("Wypozyczalnia DVD");
            stage.setScene(new Scene(root, 640, 480));
            stage.setMinHeight(480);
            stage.setMaxHeight(768);
            stage.setMinWidth(640);
            stage.setMaxWidth(1024);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda otwierająca nowe okno programu.
     * @param path String ścieżka do pliku xml
     */
    public void openWindow(String path){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle("Wypozyczalnia DVD");
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda wyświetlająca Alert.
     * @param info String będący informacją do wyświetlenia.
     */
    public void warningWindow(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(info);

        alert.showAndWait();
    }


}
