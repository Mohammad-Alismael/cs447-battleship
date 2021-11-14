import Panels.PanelCreator;
import ShipFactory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        IShipFactory shipFactory = new ShipFactory();
        Grid player1  = new Grid();
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.CarrierShip));//5
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.BattleShip));//4
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.DestroyerShip));//2
        player1.addShipToGameBoard(shipFactory.getShip(ShipType.SubmarineShip));//3

        System.out.print("place to shoot> ");
        String coordinates = input.next();
//        while (!coordinates.equals("quit")) {
//
//            player1.shoot(coordinates);
//            player1.getBothBoards(player1.getGameBoardWithHits());
//            System.out.print("place to shoot> ");
//            coordinates = input.next();
//        }
    }
}
