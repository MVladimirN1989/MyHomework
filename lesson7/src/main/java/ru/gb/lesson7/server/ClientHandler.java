package ru.gb.lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Socket socket;
    private final ChatServer server;
    private String nick;
    private final DataInputStream in;
    private final DataOutputStream out;
    private AuthService authService;

    public ClientHandler(Socket socket, ChatServer server, AuthService authService) {
        try {
            this.socket = socket;
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            this.authService = authService;

            new Thread(() -> {
                try {
                    authenticate();
                    readMessage();
                } finally {
                    closeConnection();
                }
            }).start();

        } catch (IOException e) {
            throw new RuntimeException("Mistake join to client", e);
        }
    }

    private void readMessage() {
        try {
            while (true) {
                String msg = in.readUTF();
                if ("/end".equals(msg)){
                    break;
                }
                System.out.println("Get message: " + msg);
            if (nick!=null && !msg.startsWith("/")){
                msg=this.nick+": "+ msg;
            }
                server.broadcat(msg);
            }
    } catch(IOException e) {
        e.printStackTrace();
    }
}

    private void authenticate() {
    while (true){
        try {
            final String msg=in.readUTF(); // /auth login1 pass1 -> get nick
            if (msg.startsWith("/auth")){
                final String[] s=msg.split(" "); // s[0]="/auth" s[1]=login1 s[2]=pass1
                final String login=s[1];
                final String password=s[2];
                final String nick=authService.getNickByLoginAndPassword(login,password);
                if (nick != null) {
                    if (server.isNickBusy(nick)){
                        sendMessage("User is busy");
                        continue;
                    }
                    sendMessage("/authok "+nick);
                    this.nick=nick;
                    server.broadcat("User "+nick+" join chat");
                    server.subscribe(this);
                    break;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    }

    private void closeConnection() {
sendMessage("/end");
    try {
        if (in!=null){
            in.close();
         }
    }catch (IOException e){
       throw new RuntimeException("Mistake disconnect",e);
    }
        try {
            if (out!=null){
                out.close();
            }
        }catch (IOException e){
            throw new RuntimeException("Mistake disconnect",e);
        }
        try {
            if (socket!=null){
                server.unsubscribe(this);
                socket.close();
            }
        }catch (IOException e){
            throw new RuntimeException("Mistake disconnect",e);
        }
    }

    void sendMessage(String message) {
        try {
            System.out.println("Send message: "+message);
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
