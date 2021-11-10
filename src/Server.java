import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(34000);
            Socket socket=ss.accept();//establishes connection
            // reading from the client
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String  message = (String) dis.readUTF();
            System.out.println("message = "+message);
            //create ObjectOutputStream object for sending to client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Hi Client "+message.toUpperCase());
            oos.close();
            ss.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
