package ShipFactory;

public class ShipFactory implements IShipFactory {
    @Override
    public Ship getShip(ShipType shipType) {
        switch (shipType){
            case BattleShip:
                return new BattleShip(4, 'B');
            case CarrierShip:
                return new CarrierShip(5, 'C');
            case DestroyerShip:
                return new DestroyerShip(2,'D');
            case SubmarineShip:
                return new SubmarineShip(3,'S');
            default:
                return null;
        }
    }
}
