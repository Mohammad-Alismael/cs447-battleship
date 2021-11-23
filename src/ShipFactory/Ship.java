package ShipFactory;

public class Ship {
    private int length;
    private char symbol;

    public Ship(int length, char symbol) {
        this.length = length;
        this.symbol = symbol;
    }
    public char getSymbol() {
        return symbol;
    }
    public int getLength() {
        return length;
    }
}
