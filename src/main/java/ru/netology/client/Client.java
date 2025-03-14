package ru.netology.client;

import ru.netology.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket clientSocket = new Socket("127.0.0.1", Server.PORT);
            PrintWriter writer= new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);) {
            while (true) {

                String serverMessage = reader.readLine();
                if (serverMessage == null) {

                    System.out.println("Server has closed the connection.");
                    break;
                }
                System.out.println(serverMessage);


                if (serverMessage.startsWith("Write your name") ||
                        serverMessage.startsWith("Are you child") ||
                        serverMessage.startsWith("What is your favorite color")) {
                    String userInput = scanner.nextLine();
                    writer.println(userInput);
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
