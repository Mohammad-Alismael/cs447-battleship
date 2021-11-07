package ShipFactory;

public class ShipFactory implements IShipFactory {
    @Override
    public Ship getShip(ShipType shipType, int length) {
        switch (shipType){
            case BattleShip:
                return new BattleShip(length, 'B');
            case CarrierShip:
                return new CarrierShip(length, 'C');
            case DestroyerShip:
                return new DestroyerShip(length,'D');
            case SubmarineShip:
                return new SubmarineShip(length,'S');
            default:
                return null;
        }
    }
}
