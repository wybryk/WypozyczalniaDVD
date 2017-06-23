package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pl.bazadanych.tables.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Mateusz on 2017-04-23.
 */
public abstract class BaseController {

    protected ObservableList<Konto> baseTableObservableList = FXCollections.observableArrayList();
    protected List<DaneWypozyczenia> daneWypozyczeniaList;
    protected List<Egzemplarz> egzemplarzList;
    protected List<Film> filmList;
    protected List<Gatunek> gatunekList;
    protected List<Klient> klientList;
    protected List<Konto> kontoList;
    protected List<Rezerwacja> rezerwacjaList;
    protected List<Rezyser> rezyserList;
    protected List<Wypozyczenie> wypozyczenieList;

    protected DaneWypozyczenia daneWypozyczenia;
    protected Egzemplarz egzemplarz;
    protected Film film;
    protected Gatunek gatunek;
    protected Klient klient;
    protected Konto konto;
    protected Rezerwacja rezerwacja;
    protected Rezyser rezyser;
    protected Wypozyczenie wypozyczenie;

    protected void changeWindow(Event event, String path){
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

    protected void openWindow(String path){
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

    protected void warningWindow(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(info);

        alert.showAndWait();
    }

    protected void wypozyczalniaClient(String table, String operation, BaseTable baseTable) {
        final String serverHost = "localhost";
        Socket socketOfClient = null;
        BufferedReader is;
        BufferedWriter os;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        try {
            // Send a request to connect to the server is listening
            // on machine 'localhost' port 9999.
            socketOfClient = new Socket(serverHost, 54321);
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            outputStream = new ObjectOutputStream(socketOfClient.getOutputStream());
            inputStream = new ObjectInputStream(socketOfClient.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return;
        }
        try {
            os.write(table);
            os.newLine();
            os.flush();
            os.write(operation);
            os.newLine();
            os.flush();
            outputStream.writeObject(baseTable);

            if(operation.equals("selectAll")) {
                if(table.equals("DaneWypozyczenia"))
                    daneWypozyczeniaList = (List<DaneWypozyczenia>) inputStream.readObject();
                else if(table.equals("Egzemplarz"))
                    egzemplarzList = (List<Egzemplarz>) inputStream.readObject();
                else if(table.equals("Film"))
                    filmList = (List<Film>) inputStream.readObject();
                else if(table.equals("Gatunek"))
                    gatunekList = (List<Gatunek>) inputStream.readObject();
                else if(table.equals("Klient"))
                    klientList = (List<Klient>) inputStream.readObject();
                else if(table.equals("Konto"))
                    kontoList = (List<Konto>) inputStream.readObject();
                else if(table.equals("Rezerwacja"))
                    rezerwacjaList = (List<Rezerwacja>) inputStream.readObject();
                else if(table.equals("Rezyser"))
                    rezyserList = (List<Rezyser>) inputStream.readObject();
                else if(table.equals("Wypozyczenie"))
                    wypozyczenieList = (List<Wypozyczenie>) inputStream.readObject();
            }
            else if(operation.equals("find") || operation.equals("findById") || operation.equals("selectMaxId")) {
                if (table.equals("DaneWypozyczenia"))
                    daneWypozyczenia = (DaneWypozyczenia) inputStream.readObject();
                else if (table.equals("Egzemplarz"))
                    egzemplarz = (Egzemplarz) inputStream.readObject();
                else if (table.equals("Film"))
                    film = (Film) inputStream.readObject();
                else if (table.equals("Gatunek"))
                    gatunek = (Gatunek) inputStream.readObject();
                else if (table.equals("Klient"))
                    klient = (Klient) inputStream.readObject();
                else if (table.equals("Konto"))
                    konto = (Konto) inputStream.readObject();
                else if (table.equals("Rezerwacja"))
                    rezerwacja = (Rezerwacja) inputStream.readObject();
                else if (table.equals("Rezyser"))
                    rezyser = (Rezyser) inputStream.readObject();
                else if (table.equals("Wypozyczenie"))
                    wypozyczenie = (Wypozyczenie) inputStream.readObject();
            }
            is.close();
            os.close();
            outputStream.close();
            inputStream.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException client:  " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
