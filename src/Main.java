import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("simple gui app");
        frame.pack();
        frame.setSize(new Dimension(600,600));
        frame.setLocationRelativeTo(null); // for centering the frame
        frame.setVisible(true);
    }
}
