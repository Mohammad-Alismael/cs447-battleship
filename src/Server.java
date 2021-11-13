
import ShipFactory.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(34000);
            System.out.println("waiting for a client...");
            Socket socket=ss.accept();//establishes connection
            System.out.println("connected!!..");
            // reading from the client
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String  opponentName = (String) dis.readUTF();
            System.out.printf("%s has been connected",opponentName);
            //create ObjectOutputStream object for sending to client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //write object to Socket
//            oos.writeObject("Hi Client "+opponentName.toUpperCase());
//            oos.close();
            dos.writeUTF("hi "+ opponentName);
            dos.flush();
            ss.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
