package pl.bazadanych;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    public static void main(String [] args) {
        Socket socketOfServer = null;
        ExecutorService executor = null;
        try (ServerSocket listener = new ServerSocket(54321);){
            executor = Executors.newFixedThreadPool(4);
            System.out.println("Server is waiting to accept user...");

            while (true) {
                socketOfServer = listener.accept();
                Runnable worker = new RequestHandler(socketOfServer);
                executor.execute(worker);
            }
        } catch (IOException e) {
            System.out.println("Błąd portu" + e);
            e.printStackTrace();
        }finally {
            if(executor != null)
                executor.shutdown();
        }
    }
}
