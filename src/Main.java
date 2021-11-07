import Panels.PanelCreator;
import ShipFactory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton btn = new JButton("send message");
        panel.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                try {
                    IShipFactory shipFactory = new ShipFactory();
                    Ship p = shipFactory.getShip(ShipType.CarrierShip);
                    client.sendMessage(String.valueOf(p.getSymbol()));
                    client.closeSocket();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("damn!");
                }
            }
        });
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("you");
        frame.pack();
        frame.setSize(new Dimension(400,400));
        frame.setLocationRelativeTo(null); // for centering the frame
        frame.setVisible(true);

    }
}
