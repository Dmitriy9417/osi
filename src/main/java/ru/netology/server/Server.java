package ru.netology.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8081;
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while (true) {
                try(
                Socket clientSocket = serverSocket.accept(); // ждем подключения
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ){
                    System.out.println("New connection accepted");

                    out.println("Write your name");
                    final String name = in.readLine();

                    String isChild;
                    while (true) {
                        out.println("Are you child? (yes/no)");
                        isChild = in.readLine();

                        if ("yes".equalsIgnoreCase(isChild) || "no".equalsIgnoreCase(isChild)) {
                            break;
                        } else {
                            out.println(String.format("%s! You must enter yes or no!", name));
                        }
                    }


                    if ("yes".equalsIgnoreCase(isChild)) {
                        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    } else {
                        out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    }

                    out.println("What is your favorite color?");
                    final String favoriteColor = in.readLine();

                    out.println(String.format("Nice choice, %s! %s is a great color!\nThank you for chatting with me. Goodbye!", name, favoriteColor));
                }
            }

        }
    }
}
