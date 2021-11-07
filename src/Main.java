import Panels.PanelCreator;

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
                    client.sendMessage("wow it worked");
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
