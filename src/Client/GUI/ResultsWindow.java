package Client.GUI;

import Client.ClientProtocol;
import Client.GUI.Font.GUIFont;
import Client.Player;
import shared.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultsWindow extends JPanel {

    public ResultsWindow(int points, Player player, boolean endOfGame){
        this(points, player.getOpponentScore(), player, endOfGame);
    }

    public ResultsWindow(int points, int opponentResult, Player player, boolean endOfGame){

        this.setBackground(GUIFont.backgroundColor);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JPanel resultsPanel = new JPanel();
        //resultsPanel.setBackground(GUIFont.backgroundColor);
        this.add(resultsPanel);

        JPanel buttonsPanel  = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(GUIFont.backgroundColor);
        this.add(buttonsPanel);

        resultsPanel.setLayout(new GridLayout(0,2));

        JPanel player1ResultsField = new JPanel();
        //player1ResultsField.setBackground(GUIFont.backgroundColor);
        resultsPanel.add(player1ResultsField);

        JPanel player2ResultsField = new JPanel();
        //player2ResultsField.setBackground(GUIFont.backgroundColor);
        resultsPanel.add(player2ResultsField);

        player1ResultsField.setBackground(GUIFont.darkerBackgroundColor);
        player1ResultsField.setLayout(new BoxLayout(player1ResultsField, BoxLayout.Y_AXIS));

        JLabel player1Label = new JLabel(player.getName());
        player1ResultsField.add(player1Label);
        player1Label.setFont(GUIFont.customFont(20));

        JPanel questionResults = new JPanel();
        questionResults.setBackground(GUIFont.backgroundColor);
        player1ResultsField.add(questionResults);

        JLabel playerRoundScore = new JLabel("Points this round: " + points);
        playerRoundScore.setFont(GUIFont.customFont(20));
        player1ResultsField.add(playerRoundScore);

        JLabel playerTotalScore = new JLabel("Total score: " + player.getScore());
        playerTotalScore.setFont(GUIFont.customFont(20));
        player1ResultsField.add(playerTotalScore);


        player2ResultsField.setBackground(GUIFont.darkerBackgroundColor);
        player2ResultsField.setLayout(new BoxLayout(player2ResultsField, BoxLayout.Y_AXIS));
        JLabel player2Label = new JLabel(ClientProtocol.opponentName);
        player2ResultsField.add(player2Label);
        player2Label.setFont(GUIFont.customFont(20));

        JPanel questionResults2 = new JPanel();
        questionResults2.setBackground(GUIFont.backgroundColor);
        player2ResultsField.add(questionResults2);
        JLabel opponentRoundScore = new JLabel("Points this round: " + opponentResult);
        opponentRoundScore.setFont(GUIFont.customFont(20));
        player2ResultsField.add(opponentRoundScore);

        JLabel opponentTotalScore = new JLabel("Total score: " + player.getOpponentScore());
        opponentTotalScore.setFont(GUIFont.customFont(20));
        player2ResultsField.add(opponentTotalScore);

        if(endOfGame){
            roundResultOfGame(player.getScoreTablePlayer(), questionResults);
            roundResultOfGame(player.getScoreTableOpponent(), questionResults2);
            player1ResultsField.remove(playerRoundScore);
            player2ResultsField.remove(opponentRoundScore);
        }

        myButton continueButton = new myButton("Next round");
        continueButton.setHorizontalAlignment(JLabel.CENTER);
        buttonsPanel.add(continueButton);
        continueButton.addActionListener(new myContinueListener());
        buttonsPanel.add(new myButton("Exit"));
    }

    private void roundResultOfGame(List<Integer> list, JPanel panel) {
        panel.setLayout(new GridLayout(list.size(), 1));
        int round = 1;
        for(Integer i : list){
            JLabel label = new JLabel("Round " + round + " points: " + i);
            label.setFont(GUIFont.customFont(16));
            panel.add(label);
            round++;
        }
    }

    private class myContinueListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientProtocol.waitingForNextRound = false;
        }
    }

    public static void main(String[] args) {
        Window window = new Window();
        Player player = new Player("Kalle");
        ClientProtocol.opponentName = "Pelle";
        ResultsWindow startWindow = new ResultsWindow(1,1, player, false);
        window.add(startWindow);
        window.setVisible(true);
    }
}
