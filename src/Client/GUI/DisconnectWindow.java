package Client.GUI;

import Client.Client;
import Client.ClientProtocol;
import Client.ClientNetwork;
import Client.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisconnectWindow extends JPanel implements ActionListener{

   private myButton newGameButton;
   private JLabel label;

   public DisconnectWindow() {
       setLayout(new GridLayout(2,0));
       label = new JLabel("your opponent disconnected", SwingConstants.CENTER);
       label.setFont(new Font("Arial",Font.BOLD, 20));
       label.setHorizontalAlignment(JLabel.CENTER);

       JPanel buttonPanel = new JPanel();

       newGameButton = new myButton("New Game");
       newGameButton.addActionListener(this);

       add(label); buttonPanel.add(newGameButton); add(buttonPanel);
   }


    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText("Waiting for next opponent...");
        newGameButton.setEnabled(false);
        }

}
