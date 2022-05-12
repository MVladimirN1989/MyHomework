package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean msg=false;
    public boolean isMsg(){
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public static void main(String[] args) {
        final EchoClient client = new EchoClient();
        client.start();
    }
    private void start() {
        setMsg(false);
        try {
            openConnection();
            final Scanner scanner=new Scanner(System.in);
            System.out.println("Message to server: ");
            while (true) {
                if (isMsg()){
                    setMsg(false);
                    System.out.println("Message to server: ");
                }
                final String msg = scanner.nextLine();
            out.writeUTF(msg);
                System.out.println("Message to server: "+msg);
            if ("/end".equalsIgnoreCase(msg)){
                break;
            }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void openConnection() throws IOException {
        socket=new Socket("127.0.0.1",8188);
        in=new DataInputStream(socket.getInputStream());
        out=new DataOutputStream(socket.getOutputStream());
        final Thread thread=new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    while (true) {
                    final String message = in.readUTF();
                        System.out.println("Message from server "+message);
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
thread.start();
    }
}
