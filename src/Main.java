import Panels.PanelCreator;
import ShipFactory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        IShipFactory shipFactory = new ShipFactory();
        Grid player1  = new Grid();

        player1.addShipToGameBoard(shipFactory.getShip(ShipType.CarrierShip));
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.BattleShip));
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.DestroyerShip));
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.SubmarineShip));
        player1.getBoardWithShips();
        System.out.println("----");
        System.out.println(Arrays.toString(player1.getGameBoard()[3]));

    }
}
