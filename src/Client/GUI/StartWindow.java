package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JPanel {

    String savedName;
    JTextArea name;
    JPanel nameTextPanel;
    JLabel nameLabel;

    public StartWindow(){

        StartAction buttonAction = new StartAction();
        setBackground(Color.CYAN);


        nameTextPanel = new JPanel();
        nameLabel = new JLabel("Enter name");
        myButton startButton = new myButton("Start");
        name = new JTextArea();
        name.setPreferredSize(new Dimension(100, 30));

        add(nameTextPanel);
        nameTextPanel.add(nameLabel);
        add(name);
        add(startButton);

        startButton.addActionListener(buttonAction);

    }
    private class StartAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            savedName = name.getText();
            System.out.println(savedName);
        }

    }
}
