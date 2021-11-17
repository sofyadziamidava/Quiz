package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameWindowOld extends JFrame implements ActionListener {

    JPanel root;
    JLabel label;
    JButton startButton;
    private String message;

    public GameWindowOld(){

        createGameWindow();
        gameFrameSettings();
    }

    private void createGameWindow() {

        root = new JPanel();
        label = new JLabel("Connect to server -->");
        startButton = new JButton("Start game");
        startButton.addActionListener(this);

        root.add(label);
        root.add(startButton);
    }

    private void gameFrameSettings() {
        this.setSize(new Dimension(300, 300));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(root);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            System.out.println("Server sent question in GUI");
            label.setText(message);
            //label.setText("Connected");
            //startButton.setVisible(false);
            //clientNetwork.connectToServer();
        }
    }

    public void getMessageFromServer(String message) {
        this.message = message;
    }
}
