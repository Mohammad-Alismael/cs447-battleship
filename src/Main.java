import Panels.PanelCreator;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PanelCreator myPanel = new PanelCreator("MyPanel");
        JLabel k = new JLabel("hello world");
        myPanel.CreatePanel().add(k);
        frame.add(myPanel.CreatePanel());
        frame.add(myPanel.CreatePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("simple gui app");
        frame.pack();
        frame.setSize(new Dimension(1000,600));
        frame.setLocationRelativeTo(null); // for centering the frame
        frame.setVisible(true);
    }
}
