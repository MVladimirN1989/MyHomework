package ru.gb.lesson7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private ClientController controller;

    public ChatClient(ClientController controller) {
        this.controller = controller;
    }
    public void openConnection() throws IOException {
        socket=new Socket("localHost",8189);
        in=new DataInputStream(socket.getInputStream());
        out=new DataOutputStream(socket.getOutputStream());
        new Thread(() ->{
            try {
                waitAuth();
                readMessage();
            }finally {
                closeConnection();
            }
        }).start();
    }

    private void closeConnection() {
    }

    private void readMessage() {
while (true){
    try {
        final String msg=in.readUTF();
        if ("/end".equals(msg)){
            controller.toggleBoxesVisibility(false);
            break;
        }
        controller.addMessage(msg);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }

    private void waitAuth() {
        while (true){
            try {
                final String msg=in.readUTF(); // /auth nick
                if (msg.startsWith("/authok")){
                    final String[] split=msg.split(" ");
                    final String nick=split[1];
                    controller.toggleBoxesVisibility(true);
                    controller.addMessage("Successful auth nick "+nick);
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
