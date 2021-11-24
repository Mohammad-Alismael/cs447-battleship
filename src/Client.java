import ShipFactory.IShipFactory;
import ShipFactory.Ship;
import ShipFactory.ShipFactory;
import ShipFactory.ShipType;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    DataOutputStream dataOutput;
    DataInputStream dataInput;
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public Client() {
        try{

            socket = new Socket("localhost",34000);
            System.out.println("connected!!..");
            dataOutput = new DataOutputStream(socket.getOutputStream());
            dataInput = new DataInputStream(socket.getInputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
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

    public char[][] getServerBoard() throws IOException, ClassNotFoundException {
        char[][] board = (char[][]) ois.readObject();
        return board;
    }

    public String getServerPlayerName() throws IOException, ClassNotFoundException {
        String message = (String) dataInput.readUTF();
        System.out.println("Server player name: " + message);
        return message;
    }

    public void sendBoard(char[][] board) throws IOException {
        oos.writeObject(board);
    }
    public char[][] getNewBoard() throws IOException, ClassNotFoundException {
        char[][] board = (char[][]) ois.readObject();
        return board;
    }
    public void closeSocket() throws IOException {
        dataOutput.flush();
        dataOutput.close();
        socket.close();
    }
    public static IShipFactory shipFactory = new ShipFactory();
    public static Grid player2 = new Grid();
    public static Scanner input = new Scanner(System.in);
    public static int loadedShips = 0;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client  = new Client();


        System.out.print("set username> ");
        String username = input.next();
//      -------------------------- for setting up the board --------------------------
        String shipStartingIndex;
        String shipType ;
        int[] xys;
        while (loadedShips <= 3) {

            shipStartingIndex = chooseIndex(input);
            shipType = chooseShip(input);
            xys = player2.getIndex(shipStartingIndex.toUpperCase());
            player2.setIndex(xys);

            switch (shipType) {
                case "Carrier":
                    addShip(shipFactory.getShip(ShipType.CarrierShip));
                    break;
                case "Destroyer":
                    addShip(shipFactory.getShip(ShipType.DestroyerShip));
                    break;
                case "Submarine":
                    addShip(shipFactory.getShip(ShipType.SubmarineShip));
                    break;
                case "Battleship":
                    addShip(shipFactory.getShip(ShipType.BattleShip));
                    break;
                default:
                    System.out.println("Enter a valid Ship Type");
                    break;
            }
            player2.getBoardWithShips();
        }
//      -------------------------- ends here --------------------------



        player2.setOpponentName(client.getServerPlayerName());
        player2.setMyName(username);
        client.sendUsername(username);

        while (!player2.checkWins()) {
            char[][] opponentBoardWithShips = client.getServerBoard();
            player2.getBothBoards(player2.getGameBoardWithHits());

            client.sendBoard(player2.getGameBoard());
            System.out.printf("waiting %s to shoot\n", player2.getOpponentName());

            player2.setGameBoard(client.getNewBoard());
            player2.getBothBoards(player2.getGameBoardWithHits());

            // shooting my first shot
            char[][] newOpponentBoardWithShips;
            do {
                String coordinates = shoot(input).toUpperCase();
                newOpponentBoardWithShips = player2.shoot(coordinates, opponentBoardWithShips);
                player2.getBothBoards(player2.getGameBoardWithHits());
            }while (newOpponentBoardWithShips.length == 0);

            client.oos.writeObject(newOpponentBoardWithShips);
            client.oos.flush();

            System.out.printf("waiting %s to shoot\n", player2.getOpponentName());
        }
        if (player2.checkWins()) {
            System.out.println("you've won the game congrats");
            client.closeSocket();
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

    public static void addShip(Ship shipType){
        int Error;
        int[] xys;
        do {
            String direction = chooseDirection(input);
            Error = player2.addShipToGameBoardChosen(shipType,direction);
            if (Error == ErrorHandling.LOCATION_ERROR) {
                System.out.println("choose a different location!");
                String shipStartingIndex = chooseIndex(input);
                xys = player2.getIndex(shipStartingIndex.toUpperCase());
                player2.setIndex(xys);
            }else if (Error == ErrorHandling.TYPE_ERROR){
                System.out.println("choose a different type");
            } else {
                System.out.printf("%s has been added to the board\n",shipType.getSymbol());
                loadedShips++;
            }
        }while (Error == ErrorHandling.LOCATION_ERROR);
    }
}
