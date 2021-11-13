import java.io.*;
import java.net.*;

public class Client {

    DataOutputStream dataOutput;
    Socket socket;

    public Client() {
        try{

            socket = new Socket("localhost",34000);
            dataOutput = new DataOutputStream(socket.getOutputStream());

        }catch(Exception e){
            System.out.println(e);
        }
    }

   public void sendMessage(String msg) throws IOException {
       dataOutput.writeUTF(msg);
   }

   public void readFromServer() throws IOException, ClassNotFoundException {
       var ois = new ObjectInputStream(socket.getInputStream());
       String message = (String) ois.readObject();
       System.out.println("Message from server: " + message);
   }
   public void closeSocket() throws IOException {
       dataOutput.flush();
       dataOutput.close();
       socket.close();
   }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client player2  = new Client();
        player2.sendMessage("ahmed");
        player2.readFromServer();
        player2.closeSocket();
    }
}
