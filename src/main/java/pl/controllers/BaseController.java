package pl.controllers;


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
 * <h2>Klasa głównego kontrolera</h2>
 * <p>Zawiera podstawowe metody i jest abstrakcyjna.</p>
 */
public abstract class BaseController {

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
    /**
     * Metoda wysyłająca oraz odbierająca socket po  stronie klienta.
     * @param table String będący informacją o wybranej tabeli.
     * @param operation String będący informacją o wybranej operacji.
     * @param baseTable Obiekt typu BaseTable.
     */
    protected void wypozyczalniaClient(String table, String operation, BaseTable baseTable) {
        final String serverHost = "localhost";
        Socket socketOfClient = null;
        BufferedReader is;
        BufferedWriter os;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        try {
            socketOfClient = new Socket(serverHost, 54321);
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
            outputStream.writeObject(table);
            outputStream.flush();
            outputStream.writeObject(operation);
            outputStream.flush();
            outputStream.writeObject(baseTable);
            outputStream.flush();

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

