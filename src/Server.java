
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
        try {
            Scanner input = new Scanner(System.in);
            ServerSocket ss = new ServerSocket(34000);
            Grid player1 = new Grid();
            System.out.print("set username> ");
            String username = input.next();
            player1.setMyName(username);

            //Choosing Ship Places
            for (int i=0;i < 4; i++) {
                String shipStartingIndex = chooseIndex(input);
                String direction = chooseDirection(input);
                String shipType = chooseShip(input);
                IShipFactory shipFactory = new ShipFactory();
                switch (shipType) {
                    case "Carrier":
                        player1.choosingShipPlaces(player1.getIndex(shipStartingIndex), direction, shipFactory.getShip(ShipType.CarrierShip));
                        break;
                    case "Destroyer":
                        player1.choosingShipPlaces(player1.getIndex(shipStartingIndex), direction, shipFactory.getShip(ShipType.DestroyerShip));
                        break;
                    case "Submarine":
                        player1.choosingShipPlaces(player1.getIndex(shipStartingIndex), direction, shipFactory.getShip(ShipType.SubmarineShip));
                        break;
                    case "Battleship":
                        player1.choosingShipPlaces(player1.getIndex(shipStartingIndex), direction, shipFactory.getShip(ShipType.BattleShip));
                        break;
                    default:
                        System.out.println("Enter a valid Ship Type");
                        break;
                }
                player1.getBothBoards(player1.getGameBoard());
            }

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

            while (!player1.checkWins()) {
                //send board to client
                oos.writeObject(player1.getGameBoard());
                oos.flush();

                // get board from client
                char[][] opponentBoardWithShips = (char[][]) ois.readObject();
                player1.getBothBoards(player1.getGameBoardWithHits());

                // shooting my first shot
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
            }else{
                System.out.println("You have: "+ player1.getPoints() +" Points");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static String shoot(Scanner input){
        System.out.print("shoot a place>");
        return input.next();
    }
    public static String chooseIndex(Scanner input){
        System.out.print("Choose Index>");
        return input.next();
    }
    public static String chooseDirection(Scanner input){
        System.out.print("Choose the direction of the Ship>");
        return input.next();
    }
    public static String chooseShip(Scanner input){
        System.out.print("Type of the Ship>");
        return input.next();
    }

}
