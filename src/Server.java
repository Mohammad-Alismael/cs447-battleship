
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

            // shooting my first shot

            while (!player1.checkWins()) {
                // get board from client
                char[][] opponentBoardWithShips = (char[][]) ois.readObject();
                player1.getBothBoards(player1.getGameBoardWithHits());

                String coordinates = shoot(input);
                char[][] newOpponentBoardWithShips = player1.shoot(coordinates, opponentBoardWithShips);
                player1.getBothBoards(player1.getGameBoardWithHits());

                // sending new  opponent board with ships
                oos.writeObject(newOpponentBoardWithShips);
                oos.flush();

                System.out.printf("waiting %s to shoot\n", player1.getOpponentName());

                player1.setGameBoard((char[][]) ois.readObject());
                player1.getBothBoards(player1.getGameBoardWithHits());

            }
            if (player1.checkWins()) {
                System.out.println("you've won the game congrats");
                ss.close();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static String shoot(Scanner input){
        System.out.print("shoot a place>");
        return input.next();
    }
}
