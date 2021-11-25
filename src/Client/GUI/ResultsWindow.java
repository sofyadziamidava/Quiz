package Client.GUI;

import Client.ClientProtocol;
import Client.GUI.Font.GUIFont;
import Client.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.BoxLayout.Y_AXIS;

public class ResultsWindow extends JPanel {

    private final int points;
    private final int opponentResult;
    private final Player player;
    private JPanel questionResults;
    private JPanel questionResults2;
    private JPanel player1ResultsField;
    private JPanel resultsPanel;
    private JLabel playerRoundScore;
    private JPanel player2ResultsField;
    private JLabel opponentRoundScore;
    private JPanel buttonsPanel;

    public ResultsWindow(int points, Player player, boolean endOfGame){
        this(points, player.getOpponentScore(), player, endOfGame);
    }

    public ResultsWindow(int points, int opponentResult, Player player, boolean endOfGame){
        this.points = points;
        this.opponentResult = opponentResult;
        this.player = player;

        createPlayerOnePanels();
        createPlayerTwoPanels();
        createBottomPanel(endOfGame);

        if(endOfGame){
            roundResultOfGame(player.getScoreTablePlayer(), questionResults);
            roundResultOfGame(player.getScoreTableOpponent(), questionResults2);
            player1ResultsField.remove(playerRoundScore);
            player2ResultsField.remove(opponentRoundScore);
            checkWinner();
        }

        this.setBackground(GUIFont.backgroundColor);
        this.setLayout(new BoxLayout(this, Y_AXIS));
        this.add(resultsPanel);
        this.add(buttonsPanel);
    }

    private void createPlayerOnePanels() {
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(0,2));

        player1ResultsField = new JPanel();
        player1ResultsField.setBackground(GUIFont.darkerBackgroundColor);
        player1ResultsField.setLayout(new BoxLayout(player1ResultsField, Y_AXIS));
        JLabel player1Label = new JLabel(player.getName());
        player1Label.setFont(GUIFont.customFont(20));
        player1ResultsField.add(player1Label);
        resultsPanel.add(player1ResultsField);
        questionResults = new JPanel();
        questionResults.setBackground(GUIFont.backgroundColor);
        player1ResultsField.add(questionResults);
        playerRoundScore = new JLabel("Points this round: " + points);
        playerRoundScore.setFont(GUIFont.customFont(20));
        player1ResultsField.add(playerRoundScore);
        JLabel playerTotalScore = new JLabel("Total score: " + player.getScore());
        playerTotalScore.setFont(GUIFont.customFont(20));
        player1ResultsField.add(playerTotalScore);
    }

    private void createPlayerTwoPanels() {
        player2ResultsField = new JPanel();
        player2ResultsField.setBackground(GUIFont.darkerBackgroundColor);
        player2ResultsField.setLayout(new BoxLayout(player2ResultsField, Y_AXIS));
        JLabel player2Label = new JLabel(ClientProtocol.opponentName);
        player2Label.setFont(GUIFont.customFont(20));
        player2ResultsField.add(player2Label);
        resultsPanel.add(player2ResultsField);
        questionResults2 = new JPanel();
        questionResults2.setBackground(GUIFont.backgroundColor);
        player2ResultsField.add(questionResults2);
        opponentRoundScore = new JLabel("Points this round: " + opponentResult);
        opponentRoundScore.setFont(GUIFont.customFont(20));
        player2ResultsField.add(opponentRoundScore);
        JLabel opponentTotalScore = new JLabel("Total score: " + player.getOpponentScore());
        opponentTotalScore.setFont(GUIFont.customFont(20));
        player2ResultsField.add(opponentTotalScore);
    }

    private void createBottomPanel(boolean endOfGame) {
        buttonsPanel  = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setBackground(GUIFont.backgroundColor);
        Button continueButton = new Button("Next round");
        buttonsPanel.add(continueButton);
        continueButton.addActionListener(new myContinueListener());
        Button exitButton = new Button("Exit");
        buttonsPanel.add(exitButton);
        exitButton.addActionListener(new myExitButtonListener());
        if(endOfGame){
            buttonsPanel.remove(continueButton);
        }
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

    private void checkWinner(){
        if(player.getScore() > player.getOpponentScore()){
            JOptionPane.showMessageDialog(null, "YOU WON");
        }
        else if(player.getScore() == player.getOpponentScore()){
            JOptionPane.showMessageDialog(null, "IT'S A TIE");
        }
        else{
            JOptionPane.showMessageDialog(null, "YOU LOST");
        }
    }

    private class myContinueListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientProtocol.waitingForNextRound = false;
        }
    }

    private class myExitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
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
