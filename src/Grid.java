
import ShipFactory.*;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

// singleton class
public class Grid {

    private char [][] gameBoard = new char[10][10];
    private char [][] gameBoardWithHits = new char[10][10];
    private Hashtable<Object, Integer> ht1 = new Hashtable<Object, Integer>();
    private int[] generatedIndex;
    private int points = 0;
    private String opponentName = "Opponent";
    private String myName = "You";
    public Grid() {
        System.out.println("loading board...");
        for (char[] row: gameBoard){
            Arrays.fill(row,'-');
        }

        for (char[] row: gameBoardWithHits){
            Arrays.fill(row,'-');
        }

        storeChar();
        IShipFactory shipFactory = new ShipFactory();
        addShipToGameBoard(shipFactory.getShip(ShipType.CarrierShip));
        addShipToGameBoard(shipFactory.getShip(ShipType.BattleShip));
        addShipToGameBoard(shipFactory.getShip(ShipType.DestroyerShip));
        addShipToGameBoard(shipFactory.getShip(ShipType.SubmarineShip));
        System.out.println("board finished!");
    }

    public void incrementPoint(){
        if (points < 14) points++;
        else throw new IllegalArgumentException("you can increment more than 14");
    }

    public boolean checkWins(){
        return points >= 14;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public String getOpponentName() {
        return this.opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public char[][] getGameBoardWithHits() {
        return gameBoardWithHits;
    }

    private void storeChar(){
        String [] letters = {"A","B","C","D","E","F","G","H","I","J"};
        for (int i = 0; i < letters.length; i++) {
            ht1.put(letters[i],i);
        }
    }

    public int[] getIndex(String coordinates){
        String letter = coordinates.substring(0,1);
        int x = ht1.get(letter);
        int y = Integer.parseInt(coordinates.substring(1));
        return new int[]{x, y-1};
    }

    public boolean isItWater(String coordinates){
        int[] xys = getIndex(coordinates);
        int x = xys[0];
        int y = xys[1];
        return gameBoard[x][y] == '-';
    }

    public void shoot(String coordinates){
        int[] xys = getIndex(coordinates);
        int x = xys[0];
        int y = xys[1];
        if (gameBoard[y][x] != '-'){
            incrementPoint();
            gameBoardWithHits[y][x] = 'H';
            gameBoard[y][x] = 'H';
            System.out.println("you hit!!");
        }else {
            gameBoardWithHits[y][x] = 'X';
            gameBoard[y][x] = 'X';
            System.out.println("you missed!!");
        }
    }

    public char getElementIndex(String coordinates){
        int[] xys = getIndex(coordinates);
        int x = xys[0];
        int y = xys[1];
        return gameBoard[x][y];
    }

    public void changeElementIndex(int x,int y,char symbol){
        gameBoard[x][y] = symbol;
    }

    public int[] generateRandomIndex(){
        Random rng = new Random();
        int min = 0;
        int max = 9;
        int x;
        int y;
        x = min + rng.nextInt(max-min +1);
        y = min + rng.nextInt(max-min +1);
//        System.out.println("generated set "+Arrays.toString(new int[]{x,y}));
        if (gameBoard[y][x] != '-'){
           return generateRandomIndex();
        }else {
            return new int[]{x, y};
        }
    }

    public void setRandomIndex(int x, int y){
        generatedIndex = new int[]{x-1,y-1};
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
        char[]slicedRow = SliceBoardGameForRows(generatedIndex[1]);
        int position = generatedIndex[0] - shipLength;
        if (position >= 0 && position <= 9){
            for (int i = position+1; i <= generatedIndex[0] ; i++) {
                if (slicedRow[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    private void addShipFromLeftToPoint(int shipLength,int[] generatedIndex,Ship ship){
        int position = generatedIndex[0] - shipLength;//
        for (int i = position+1; i <= generatedIndex[0] ; i++) {
                      //  x
            gameBoard[generatedIndex[1]][i] = ship.getSymbol();
        }
    }

    private Boolean checkIfTheShipCanFitFromRight(int shipLength, int[] generatedIndex){
        char[]slicedRow = SliceBoardGameForRows(generatedIndex[1]);
        int position = generatedIndex[0] + shipLength;
        if (position >= 0 && position <= 9){
            for (int i = generatedIndex[0]; i < position-1; i++) {
                if (slicedRow[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }



    private void addShipFromRightToPoint(int shipLength,int[] generatedIndex,Ship ship){
        int position = generatedIndex[0] + shipLength;
        for (int i = generatedIndex[0]; i < position; i++) {
            gameBoard[generatedIndex[1]][i] = ship.getSymbol();
        }
    }

    private Boolean checkIfTheShipCanFitFromTop(int shipLength, int[] generatedIndex){
        char[]tmp = SliceBoardGameForColumns(generatedIndex[0]);
        int position = generatedIndex[1] - shipLength;
        if (position >= 0 && position <= 9){
            for (int i = position+1; i <= generatedIndex[1]; i++) {
                if (tmp[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    private void addShipFromTopToPoint(int shipLength,int[] generatedIndex,Ship ship){
        int position = generatedIndex[1] - shipLength;
        for (int i = position+1; i <= generatedIndex[1] ; i++) {
            gameBoard[i][generatedIndex[0]] = ship.getSymbol();
        }
    }

    private Boolean checkIfTheShipCanFitFromBottom(int shipLength, int[] generatedIndex){
        char[]tmp = SliceBoardGameForColumns(generatedIndex[0]);
        int position = generatedIndex[1] + shipLength;
        if (position >= 0 && position <= 9){
            for (int i = generatedIndex[1]; i < position-1; i++) {
                if (tmp[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    private void addShipFromBottomToPoint(int shipLength,int[] generatedIndex,Ship ship){
        int position = generatedIndex[1] + shipLength;
        for (int i = generatedIndex[1] ; i < position-1 ; i++) {
            gameBoard[i][generatedIndex[0]] = ship.getSymbol();
        }
    }


    public void addShipToGameBoard(Ship ship){
            generatedIndex = generateRandomIndex();
            int shipLength = ship.getLength();
//            System.out.println("left " + checkIfTheShipCanFitFromLeft(shipLength, generatedIndex));
//            System.out.println("right " + checkIfTheShipCanFitFromRight(shipLength, generatedIndex));
//            System.out.println("top " + checkIfTheShipCanFitFromTop(shipLength, generatedIndex));
//            System.out.println("bottom " + checkIfTheShipCanFitFromBottom(shipLength, generatedIndex));

            if (checkIfTheShipCanFitFromLeft(shipLength, generatedIndex)) {
                addShipFromLeftToPoint(shipLength, generatedIndex, ship);
            } else if (checkIfTheShipCanFitFromRight(shipLength, generatedIndex)) {
                addShipFromRightToPoint(shipLength, generatedIndex, ship);
            } else if (checkIfTheShipCanFitFromTop(shipLength, generatedIndex)) {
                addShipFromTopToPoint(shipLength, generatedIndex, ship);
            } else if (checkIfTheShipCanFitFromBottom(shipLength, generatedIndex)) {
                addShipFromBottomToPoint(shipLength, generatedIndex, ship);
            } else {
                System.out.println("all false");
                addShipToGameBoard(ship);
            }
        }


    public void getBoardWithHits(){
        System.out.print("   A  B  C  D  E  F  G  H  I  J");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d  %s\n",i+1,Arrays.toString(gameBoardWithHits[i]));
        }
    }
    public void getBoardWithShips(){
        System.out.print("   A  B  C  D  E  F  G  H  I  J");
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d %s\n",i+1,Arrays.toString(gameBoard[i]));
        }
        System.out.printf("%d%s\n",10,Arrays.toString(gameBoard[9]));
    }

    public void getBothBoards(char [][] board){
        System.out.printf("              %s                                    %s              \n",myName,opponentName);
        System.out.print("   A  B  C  D  E  F  G  H  I  J           A  B  C  D  E  F  G  H  I  J");
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d %s       %d %s\n",i+1,Arrays.toString(gameBoard[i]),i+1,Arrays.toString(board[i]));
        }
        System.out.printf("%d%s       %d%s\n",10,Arrays.toString(gameBoard[9]),10,Arrays.toString(board[9]));
    }


}
