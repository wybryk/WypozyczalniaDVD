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
import javafx.stage.Stage;
import pl.bazadanych.Connections;
import pl.tablesFx.GatunekFx;
import pl.tablesFx.KontoFx;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mateusz on 2017-04-23.
 */
public abstract class BaseController {


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


}
