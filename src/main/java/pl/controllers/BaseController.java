package pl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mateusz on 2017-04-23.
 */
public abstract class BaseController {

    public void changeWindow(ActionEvent event, String path){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle("Wypozyczalnia DVD");
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
