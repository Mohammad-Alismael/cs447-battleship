
import ShipFactory.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    DataInputStream dis;
    ObjectOutputStream oos;
    DataOutputStream dos;

    public Server() {
    }

    public static void main(String[] args) {
        try{
            Scanner input = new Scanner(System.in);
            ServerSocket ss = new ServerSocket(34000);
            Grid player1 = new Grid();
            System.out.print("set username> ");
            String username = input.next();
            player1.setMyName(username);

            System.out.println("waiting for a client...");
            Socket socket=ss.accept();//establishes connection
            // reading from the client
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // send my name
            dos.writeUTF(player1.getMyName());
            dos.flush();

            // get opponent name
            String  opponentName = (String) dis.readUTF();
            player1.setOpponentName(opponentName);
            System.out.printf("%s has been connected\n",opponentName);
            //create ObjectOutputStream object for sending to client

            //send board to client
            oos.writeObject(player1.getGameBoard());
            oos.flush();

            // get board from client
            char[][] opponentBoard = (char[][]) ois.readObject();
            player1.getBothBoards(opponentBoard);

            // shooting my first shot
            String coordinates = shoot(input);


            ss.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static String shoot(Scanner input){
        System.out.print("shoot a place>");
        return input.next();
    }
}
