package Client.GUI;

import javax.swing.*;
import java.awt.*;

public class WaitingWindow extends JPanel {
    public WaitingWindow(){
        setLayout(new GridLayout());
        setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel("*waiting for opponents results*", SwingConstants.CENTER);
       // label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);

        add(label);
    }
}

