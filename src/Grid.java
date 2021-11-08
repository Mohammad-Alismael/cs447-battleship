import ShipFactory.Ship;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

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

    public int[] getIndex(String coordinates){
        String letter = coordinates.substring(0,1);
        int x = ht1.get(letter);
        int y = Integer.parseInt(coordinates.substring(1));
        return new int[]{x-1, y-1};
    }

    public boolean isItWater(String coordinates){
        int[] xys = getIndex(coordinates);
        int x = xys[0];
        int y = xys[1];
        return gameBoard[x][y] == '-';
    }

    public char getElementIndex(String coordinates){
        int[] xys = getIndex(coordinates);
        int x = xys[0];
        int y = xys[1];
        return gameBoard[x][y];
    }

    private int[] generateRandomIndex(){
        Random rng = new Random();
        int x = rng.nextInt(10+1);
        int y = rng.nextInt(10+1);
        return new int[]{x -1 ,y-1};
    }

    private char[] SliceBoardGameForRows(int generatedX){
        return gameBoard[generatedX];
    }
    private char[] SliceBoardGameForColumns(int generatedY){
        char[] column = new char[10]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
            column[i] = gameBoard[i][generatedY];
        }
        return column;
    }

    private Boolean checkIfTheShipCanFitFromLeft(int shipLength, int[] generatedIndex){
        char[]tmp = SliceBoardGameForRows(generatedIndex[0]);
        int position = generatedIndex[0] - shipLength;
        if (position > 0){
            for (int i = position; i < shipLength; i++) {
                if (tmp[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    private Boolean checkIfTheShipCanFitFromRight(int shipLength, int[] generatedIndex){
        char[]tmp = SliceBoardGameForRows(generatedIndex[0]);
        int position = generatedIndex[0] + shipLength;
        if (position > 0){
            for (int i = generatedIndex[0]; i < position; i++) {
                if (tmp[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    private Boolean checkIfTheShipCanFitFromTop(int shipLength, int[] generatedIndex){
        char[]tmp = SliceBoardGameForRows(generatedIndex[0]);
        int position = generatedIndex[1] - shipLength;
        if (position > 0){
            for (int i = position; i < generatedIndex[1]; i++) {
                if (tmp[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    private Boolean checkIfTheShipCanFitFromBottom(int shipLength, int[] generatedIndex){
        char[]tmp = SliceBoardGameForRows(generatedIndex[0]);
        int position = generatedIndex[1] + shipLength;
        if (position > 0){
            for (int i = generatedIndex[1]; i < position; i++) {
                if (tmp[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean checkIfTheShipCanFitFromAllDirections(Ship ship){
        int shipLength = ship.getLength();
        int[] generatedIndex = generateRandomIndex();
        boolean left = checkIfTheShipCanFitFromLeft(shipLength,generatedIndex);
        boolean right = checkIfTheShipCanFitFromRight(shipLength,generatedIndex);
        boolean top = checkIfTheShipCanFitFromTop(shipLength,generatedIndex);
        boolean bottom = checkIfTheShipCanFitFromBottom(shipLength,generatedIndex);

//        check for all directions
        return left && right && top && bottom;
    }

    public void addShipToGameBoard(Ship ship){
        int[] generatedIndex = generateRandomIndex();
        int shipLength = ship.getLength();
        if (checkIfTheShipCanFitFromLeft(shipLength,generatedIndex)){

        }else if (checkIfTheShipCanFitFromRight(shipLength,generatedIndex)){

        }else if(checkIfTheShipCanFitFromTop(shipLength,generatedIndex)){

        }else if(checkIfTheShipCanFitFromBottom(shipLength,generatedIndex)){

        }
    }

    public void addShip(){

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
