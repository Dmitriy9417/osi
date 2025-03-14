package ru.netology.client;

import ru.netology.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(Socket clientSocket = new Socket("127.0.0.1", Server.PORT);
            PrintWriter writer= new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            writer.println("Drum-n-bass");
            System.out.println("Server respont:" +reader.readLine());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
