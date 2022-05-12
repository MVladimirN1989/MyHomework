package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public boolean msg = false;

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public void start() {
        ServerSocket serverSocket=null;
        this.setMsg(false);

        try {
        serverSocket = new ServerSocket(8188);
            System.out.println("Server started, wait connect..." + serverSocket.getLocalSocketAddress());
            Socket socket = serverSocket.accept(); //block method
            System.out.println("Client connect");
            final DataInputStream in = new DataInputStream(socket.getInputStream());// get message
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // send message

            Thread threadMsg = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String msg = in.readUTF();
                            if ("/end".equalsIgnoreCase(msg)) {
                                break;
                            }
                            System.out.println();
                            System.out.println("MEssage from client " + msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            threadMsg.start();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter message to client: ");

            while (true) {
                if (this.isMsg()) {
                    setMsg(false);
                    System.out.println("Enter message to client: ");
                }
                String msg = scanner.nextLine();
                out.writeUTF(msg);
                System.out.println("Enter message to client: ");
                if ("/end".equalsIgnoreCase(msg)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Server stopped");

    }
        public static void main (String[]args){
            EchoServer s = new EchoServer();
            s.start();
        }
}
