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

        player1.addShipToGameBoard(shipFactory.getShip(ShipType.CarrierShip));//5
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.BattleShip));//4
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.DestroyerShip));//2
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.SubmarineShip));//3
//        for (int i = 0; i < 3; i++) {
//            player1.addShipToGameBoard(shipFactory.getShip(ShipType.CarrierShip));//5
//        }
        player1.getBoardWithShips();

        System.out.println("----");
        System.out.println(Arrays.toString(player1.getGameBoard()[3]));

    }
}
