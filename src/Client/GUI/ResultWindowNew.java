package Client.GUI;

import Client.ClientProtocol;
import Client.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultWindowNew extends JPanel {

    Player player;
    JPanel root;
    JPanel scorePanelPlayer;
    JPanel buttonsPanel;
    JLabel playerName;
    JLabel playerscore;
    JLabel opponentName;
    JLabel opponentScore;
    JPanel scorePanelOpponent;

    JButton continueButton;


    public ResultWindowNew(Player player) {
        this.player = player;

        root = new JPanel();
        root.setLayout(new FlowLayout());

        playerName = new JLabel(player.getName());
        playerscore = new JLabel(String.valueOf(player.getScore()));

        scorePanelPlayer = new JPanel();
        scorePanelPlayer.add(playerName);
        scorePanelPlayer.add(playerscore);

        opponentName = new JLabel("Opponent");
        opponentScore = new JLabel("0");


        scorePanelOpponent = new JPanel();
        scorePanelOpponent.add(opponentName);
        scorePanelOpponent.add(opponentScore);


        continueButton = new myButton("next round");
        continueButton.addActionListener(new ContinueListener());
        buttonsPanel = new JPanel();
        buttonsPanel.add(continueButton);

        root.add(buttonsPanel);
        root.add(scorePanelPlayer);
        root.add(scorePanelOpponent);

        this.add(root);
    }

    public static void main(String[] args) {
        Player playerTest = new Player("Kalle");
        new ResultWindowNew(playerTest);
    }

    private class ContinueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientProtocol.waitingForNextRound = false;
        }

    }
}
