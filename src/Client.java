import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    DataOutputStream dataOutput;
    public Client() throws IOException {
        try{
            socket = new Socket("localhost",34000);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            dataOutput  = new DataOutputStream(socket.getOutputStream());

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void sendUsername(String username) throws IOException {
        dataOutput.writeUTF(username);
    }

   public char[][] getOpponentBoard() throws IOException, ClassNotFoundException {
       return (char[][]) ois.readObject();
   }
    public void sendMyBoard(char[][] board) throws IOException {
        oos.writeObject(board);
        oos.flush();
    }
   public String getOpponentName() throws IOException, ClassNotFoundException {
       return (String) ois.readObject();
   }
   public void closeSocket() throws IOException {
       ois.close();
       socket.close();
   }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client  = new Client();
        Grid player2 = new Grid();
        Scanner input = new Scanner(System.in);

//        player2.setOpponentName(client.getOpponentName());
        System.out.println(player2.getOpponentName());
        System.out.print("set username> ");
        String username = input.next();
        client.sendUsername(username);// Send your name to the opponent
        player2.setMyName(username); // My name in my class

//        char[][] opponentBoard = client.getOpponentBoard();
//        player2.getBothBoards(opponentBoard);
//        client.sendMyBoard(player2.getGameBoardWithHits());
        client.closeSocket();
    }


}
