package ru.gb.lesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
private final List<ClientHandler> clients;
    AuthService authService;
    public ChatServer() {
        clients = new ArrayList<>();
        authService = new InMemoryAuthService();
        authService.start();
    }

    public void run(){
    try (ServerSocket serverSocket=new ServerSocket(8189)) {
    while (true){
        System.out.println("wait connect client...");
        final Socket socket=serverSocket.accept();
        System.out.println("Client connected");
        new ClientHandler(socket,this,authService);
    }
} catch (IOException e) {
    throw new RuntimeException("Mistake server's",e);
}
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getNick().equals(nick)){
                return true;
            }
        }

        return false;
    }

    public void broadcat(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
    clients.remove(client);
    }
}
