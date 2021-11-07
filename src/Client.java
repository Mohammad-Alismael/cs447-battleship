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

   public void closeSocket() throws IOException {
       dataOutput.flush();
       dataOutput.close();
       socket.close();
   }
}
