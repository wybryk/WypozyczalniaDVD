package pl.bazadanych;

import javafx.collections.ObservableList;
import pl.bazadanych.dao.*;
import pl.bazadanych.tables.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mateusz on 2017-06-21.
 */
public class RequestHandler implements Runnable {
    private Socket socketOfServer;
    ServerSocket listener = null;

    public RequestHandler(Socket socketOfServer){
        this.socketOfServer = socketOfServer;
    }

    @Override
    public void run() {
        String operation;
        String table;
        BaseTable baseTable;
        BufferedReader is;
        BufferedWriter os;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        try {
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            inputStream = new ObjectInputStream(socketOfServer.getInputStream());
            outputStream = new ObjectOutputStream(socketOfServer.getOutputStream());

            // Read data to the server (sent from client).
            table = is.readLine();
            operation = is.readLine();
            baseTable = (BaseTable) inputStream.readObject();
            System.out.println("Table: " +  table);
            System.out.println("Operation: " + operation);
            System.out.println("Object: " + baseTable);

            synchronized (this) {
                if (table.equals("DaneWypozyczenia")) {
                    DaneWypozyczeniaDao daneWypozyczeniaDao = new DaneWypozyczeniaDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(daneWypozyczeniaDao.selectAll());
                    else if (operation.equals("insert"))
                        daneWypozyczeniaDao.insertDaneWypozyczenia((DaneWypozyczenia) baseTable);
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Egzemplarz")) {
                    EgzemplarzDao egzemplarzDao = new EgzemplarzDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(egzemplarzDao.selectAll());
                    else if (operation.equals("insert"))
                        egzemplarzDao.insertEgzemplarz((Egzemplarz) baseTable);
                    else if (operation.equals("deleteByIdFilmu"))
                        egzemplarzDao.deleteEgzemplarzByIdFilmu((Egzemplarz) baseTable);
                    else if (operation.equals("deleteByIdEgzemplarzu"))
                        egzemplarzDao.deleteEgzemplarzByIdEgzemplarzu((Egzemplarz) baseTable);
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Film")) {
                    FilmDao filmDao = new FilmDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(filmDao.selectAllFilm());
                    else if (operation.equals("insert"))
                        filmDao.insertFilm((Film) baseTable);
                    else if (operation.equals("delete"))
                        filmDao.deleteFilm((Film) baseTable);
                    else if (operation.equals("find"))
                        outputStream.writeObject(filmDao.findFilm((Film) baseTable));
                    else if (operation.equals("update"))
                        filmDao.updateFilm((Film) baseTable);
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Gatunek")) {
                    GatunekDao gatunekDao = new GatunekDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(gatunekDao.selectAll());
                    else if (operation.equals("find"))
                        outputStream.writeObject(gatunekDao.findGatunekById((Gatunek) baseTable));
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Klient")) {
                    KlientDao klientDao = new KlientDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(klientDao.selectAll());
                    else if (operation.equals("insert"))
                        klientDao.insertKlient((Klient) baseTable);
                    else if (operation.equals("findById"))
                        outputStream.writeObject(klientDao.findKlientById((Klient) baseTable));
                    else if (operation.equals("find"))
                        outputStream.writeObject(klientDao.findKlient((Klient) baseTable));
                    else if (operation.equals("delete"))
                        klientDao.deleteKlient((Klient) baseTable);
                    else if (operation.equals("update"))
                        klientDao.updateKlient((Klient) baseTable);
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Konto")) {
                    KontoDao kontoDao = new KontoDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(kontoDao.selectAll());
                    else if (operation.equals("insert"))
                        kontoDao.insertKonto((Konto) baseTable);
                    else if (operation.equals("delete"))
                        kontoDao.deleteKonto((Konto) baseTable);
                    else if (operation.equals("update"))
                        kontoDao.updateKonto((Konto) baseTable);
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Rezerwacja")) {
                    RezerwacjaDao rezerwacjaDao = new RezerwacjaDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(rezerwacjaDao.selectAll());
                    else if (operation.equals("insert"))
                        rezerwacjaDao.insertRezerwacje((Rezerwacja) baseTable);
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Rezyser")) {
                    RezyserDao rezyserDao = new RezyserDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(rezyserDao.selectAll());
                    else if (operation.equals("find"))
                        outputStream.writeObject(rezyserDao.findRezyser((Rezyser) baseTable));
                    else if (operation.equals("findById"))
                        outputStream.writeObject(rezyserDao.findRezyserById((Rezyser) baseTable));
                    else
                        System.out.println("Brak operacji");
                } else if (table.equals("Wypozyczenie")) {
                    WypozyczenieDao wypozyczenieDao = new WypozyczenieDao();
                    if (operation.equals("selectAll"))
                        outputStream.writeObject(wypozyczenieDao.selectAll());
                    else if (operation.equals("insert"))
                        wypozyczenieDao.insertWypozyczenie();
                    else if (operation.equals("selectMaxId"))
                        outputStream.writeObject(wypozyczenieDao.selectMaxId());
                    else
                        System.out.println("Brak operacji");
                } else
                    System.out.println("Brak tabeli");
            }
            is.close();
            os.close();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            System.out.println("IOException server:  " + e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                socketOfServer.close();
                socketOfServer = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sever stopped!");
    }
}
