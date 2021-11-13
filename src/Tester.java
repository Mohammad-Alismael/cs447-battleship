import java.util.Arrays;
import java.util.Random;

public class Tester {
    public static final char [][] gameBoard = new char[10][10];
    public static void main(String[] args) {

        char[]tmp = new char[] {'-','-','-','-','-','-','c','c','-','-'};

        boolean res = false;
        int counter = 0;
        while (true){
            int[] generatedRandomIndex = generateRandomIndex();
            res = checkIfTheShipCanFitFromLeft(generatedRandomIndex,tmp,4);
            if (res && generatedRandomIndex[0] != 5 && generatedRandomIndex[0] != 4){
                addShipFromLeftToPoint(4,generatedRandomIndex,tmp);
                break;
            }
            counter++;
//            System.out.println(counter);
        }

//        addShipFromLeftToPoint(4,new int[]{6,0},tmp);
        System.out.println(Arrays.toString(tmp));
    }

    public static boolean checksIfSymbolIncludes(char[] tmp,char search){
        for (int i = 0; i < tmp.length ; i++) {
            if (tmp[i] == search) return true;
        }
        return false;
    }

    public static boolean V2(char[] tmp,char search){
        for (int i = 0; i < tmp.length ; i++) {
            if (tmp[i] != search) return false;
        }
        return true;
    }

    public static boolean checkIfTheShipCanFitFromLeft(int[]generatedIndex, char[] tmp,int shipLength){
        int position = generatedIndex[0] - shipLength;
        if (position >= 0 && position <= 9){
            for (int i = position+1; i <= generatedIndex[0] ; i++) {
                if (tmp[i] != '-') return false;
            }
            return true;
        }else {
            return false;
        }
    }

    private static void addShipFromLeftToPoint(int shipLength, int[] generatedIndex, char[] tmp){
        int position = generatedIndex[0] - shipLength;
        for (int i = position+1; i <= generatedIndex[0] ; i++) {
            tmp[i] = 'a';
        }
    }

    public static int[] generateRandomIndex(){
        Random rng = new Random();
        int min = 0;
        int max = 9;
        int x;
        int y;
        x = min + rng.nextInt(max-min +1);
        y = min + rng.nextInt(max-min +1);
        System.out.println("generated set "+Arrays.toString(new int[]{x,y}));
        return new int[]{x, y};
    }

    public static void getBoardWithShips(){
        System.out.print("   0  1  2  3  4  5  6  7  8  9 ");
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d %s\n",i,Arrays.toString(gameBoard[i]));
        }
    }

}
