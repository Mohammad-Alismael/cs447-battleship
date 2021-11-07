import ShipFactory.Ship;
import ShipFactory.Water;

import java.util.Arrays;
import java.util.Hashtable;

// singleton class
public class Grid {

    private static Grid singleton = null;
    private char [][] gameBoard = new char[10][10];
    private Hashtable<Integer, Character> ht1 = new Hashtable<>();

    private Grid() {
        for (char[] row: gameBoard){
            Arrays.fill(row,'-');
        }

        storeChar();

    }

    private void storeChar(){
        char [] letters = {'A','B','C','D','E','F','G','H','I','J'};
        for (int i = 0; i < letters.length; i++) {
            ht1.put(i+1,letters[0]);
        }
    }

    public void getIndex(String coordinates){

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
