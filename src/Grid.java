import ShipFactory.Ship;

import java.util.Arrays;
import java.util.Hashtable;

// singleton class
public class Grid {

    private static Grid singleton = null;
    private char [][] gameBoard = new char[10][10];
    private Hashtable<Object, Integer> ht1 = new Hashtable<Object, Integer>();

    private Grid() {
        for (char[] row: gameBoard){
            Arrays.fill(row,'-');
        }

        storeChar();
        System.out.println(ht1);
    }

    private void storeChar(){
        String [] letters = {"A","B","C","D","E","F","G","H","I","J"};
        for (int i = 0; i < letters.length; i++) {
            ht1.put(letters[i],i+1);
        }
    }

    public void getIndex(String coordinates){
        String letter = coordinates.substring(0,1);
        int x = ht1.get(letter);
        int y = Integer.parseInt(coordinates.substring(1));
        System.out.println(x);
        System.out.println(y);
    }

    public void getElementIndex(String coordinates){

    }

    public boolean checkIfPossibleToAddShip(Ship ship){
        return true;
    }

    public void addShipToGameBoard(Ship ship){

    }

    public boolean checkIfItsHit(String coordinates){
        return true;
    }

    public static Grid getInstance() {

        if (singleton == null)
            singleton = new Grid();
        return singleton;
    }

    @Override
    public String toString() {

       return Arrays.deepToString(gameBoard);
    }
}
