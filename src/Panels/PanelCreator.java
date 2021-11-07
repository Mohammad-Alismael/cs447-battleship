package Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PanelCreator {
    private String type;
    private JPanel createdPanel = new JPanel();

    public PanelCreator(String type) {
        this.type = type;
        createdPanel.setSize(new Dimension(400,400));
        createdPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JPanel getCreatedPanel() {
        return createdPanel;
    }

    public void setCreatedPanel(JPanel createdPanel) {
        this.createdPanel = createdPanel;
    }

    public JPanel CreatePanel(){
        if (this.type.equals("myPanel")){
            createdPanel.setBackground(new Color(210,226,241));
        }else if (this.type.equals("OpponentsPanel")){
            createdPanel.setBackground(new Color(211,223,226));
        }else{}
        return createdPanel;
    }
}
