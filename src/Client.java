import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    DataOutputStream dataOutput;
    DataInputStream dataInput;
    Socket socket;
    ObjectInputStream ois;
    public Client() {
        try{

            socket = new Socket("localhost",34000);
            System.out.println("connected!!..");
            dataOutput = new DataOutputStream(socket.getOutputStream());
            dataInput = new DataInputStream(socket.getInputStream());

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void sendMessage(String msg) throws IOException {
        dataOutput.writeUTF(msg);
    }

    public void sendUsername(String msg) throws IOException {
        dataOutput.writeUTF(msg);
    }

    public void readFromServer() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message from server: " + message);
    }

    public void readFromServerv2() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) dataInput.readUTF();
        System.out.println("Message from server: " + message);
    }
    public void closeSocket() throws IOException {
        dataOutput.flush();
        dataOutput.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client  = new Client();
        Grid player2 = new Grid();
        Scanner input = new Scanner(System.in);

        System.out.print("set username> ");
        String username = input.next();

        client.sendUsername(username);
        client.sendUsername(username.substring(4));
        client.readFromServerv2();
        client.closeSocket();
    }
}
