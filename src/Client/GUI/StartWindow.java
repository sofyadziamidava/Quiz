package Client.GUI;

import Client.ClientProtocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JPanel{

    String savedName;
    JTextArea name;
    JPanel nameTextPanel;
    JLabel nameLabel;
    myButton startButton;
    boolean ready;

    public StartWindow() {
        ready = false;
        StartAction buttonAction = new StartAction();
        setBackground(Color.blue);


        nameTextPanel = new JPanel();
        nameLabel = new JLabel("Enter name");
        startButton = new myButton("Start");
        name = new JTextArea();
        name.setPreferredSize(new Dimension(100, 30));

        add(nameTextPanel);
        nameTextPanel.add(nameLabel);
        add(name);
        add(startButton);

        startButton.addActionListener(buttonAction);

    }

    public void changeFrame() {

    }

    private class StartAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //lägg till connect to server, ändra state
            savedName = name.getText();
            ClientProtocol.clientName = savedName;
            System.out.println(savedName);
            System.out.println("Ready from button: " + ready);
            nameLabel.setText("Hello " + savedName + ", Please wait for the game to start...");
            name.setVisible(false);
            startButton.setVisible(false);
            ready = true;
            System.out.println("Ready from button: " + ready);
        }

    }

    public boolean getReady() {
        return ready;
    }
}
