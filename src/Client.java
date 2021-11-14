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
        player2.setOpponentName(client.getServerPlayerName());
        player2.setMyName(username);
        client.sendUsername(username);

        char[][] opponentBoard = client.getServerBoard();
        player2.getBothBoards(opponentBoard);

        client.sendBoard(player2.getGameBoard());

        client.closeSocket();
    }
}
