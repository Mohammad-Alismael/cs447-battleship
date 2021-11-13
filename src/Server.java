
import ShipFactory.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try{
            Grid player1  = new Grid();
            ServerSocket ss = new ServerSocket(34000);
            Scanner input = new Scanner(System.in);

            System.out.print("set username> ");
            String username = input.next();
            player1.setMyName(username);
            System.out.println("waiting for a client...");


            Socket socket=ss.accept();//establishes connection
            // reading from the client
            ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());

            //create ObjectOutputStream object for sending to client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject(player1.getMyName());
            oos.flush();
            String  opponentName = (String) dis.readObject();
            player1.setOpponentName(opponentName);
            System.out.printf("%s has been connected!\n",player1.getOpponentName());
            oos.writeObject(player1.getGameBoardWithHits());
            oos.flush();

            char[][] opponentBoard = (char[][]) dis.readObject();
            player1.getBothBoards(opponentBoard);

            oos.close();
            ss.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
