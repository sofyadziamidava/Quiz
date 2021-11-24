package Client.GUI;

import Client.GUI.Font.GUIFont;

import javax.swing.*;
import java.awt.*;

public class WaitingWindow extends JPanel {

    public WaitingWindow(){
        String labelText = "waiting for the opponent to finish before you can see the results...";
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setFont(GUIFont.customFont(20));
        label.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new GridLayout());
        this.setBackground(GUIFont.backgroundColor);
        this.add(label);
    }
}
