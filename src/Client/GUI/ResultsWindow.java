package Client.GUI;

import Client.ClientProtocol;
import Client.Player;
import shared.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultsWindow extends JPanel {

    Player player;

    private List<Question> rounds;
    private Question question;


    private int roundPoints;
    private int opponentResult;

    private myButton continueButton;

    private JPanel resultsPanel;
    private JPanel playerResultsField;

    private JPanel player1ResultsField;
    private JPanel player2ResultsField;
    private JPanel questionResults;
    private JPanel questionResults2;

    private JPanel buttonsPanel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player1Tot;
    private JLabel player2Tot;
    private JLabel player1Results;
    private JLabel player2Results;
    private JLabel topText;

    private boolean endOfGame;

    public ResultsWindow(int points, Player player, boolean endOfGame){
        this(points, player.getOpponentScore(), player, endOfGame);
    }

    public ResultsWindow(int points, int opponentResult, Player player, boolean endOfGame){

        this.endOfGame = endOfGame;
        this.player = player;

        this.roundPoints = points;
        this.opponentResult = opponentResult;

        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(topText = new JLabel("Results"));
        add(resultsPanel = new JPanel());
        add(buttonsPanel = new JPanel());

        resultsPanel.setBackground(Color.CYAN);
        resultsPanel.setLayout(new GridLayout(0,2));

        resultsPanel.add(player1ResultsField = new JPanel());
        resultsPanel.add(player2ResultsField = new JPanel());

        player1ResultsField.setBackground(Color.green);
        player1ResultsField.setLayout(new BoxLayout(player1ResultsField, BoxLayout.Y_AXIS));
        player1ResultsField.add(player1Label = new JLabel(player.getName()));
        player1Label.setFont(new Font("Arial", Font.BOLD, 20));
        player1ResultsField.add(questionResults = new JPanel());
        JLabel playerRoundScore = new JLabel("Points this round: " + points);
        playerRoundScore.setFont(new Font("Arial", Font.BOLD, 20));
        player1ResultsField.add(playerRoundScore);
        JLabel playerTotalScore = new JLabel("Total score: " + player.getScore());
        playerTotalScore.setFont(new Font("Arial", Font.BOLD, 20));
        player1ResultsField.add(playerTotalScore);


        player2ResultsField.setBackground(Color.pink);
        player2ResultsField.setLayout(new BoxLayout(player2ResultsField, BoxLayout.Y_AXIS));
        player2ResultsField.add(player2Label = new JLabel("Opponent"));
        player2Label.setFont(new Font("Arial", Font.BOLD, 20));
        player2ResultsField.add(questionResults2 = new JPanel());
        JLabel opponentRoundScore = new JLabel("Points this round: " + opponentResult);
        opponentRoundScore.setFont(new Font("Arial", Font.BOLD, 20));

        player2ResultsField.add(opponentRoundScore);
        JLabel opponentTotalScore = new JLabel("Total score: " + player.getOpponentScore());
        opponentTotalScore.setFont(new Font("Arial", Font.BOLD, 20));
        player2ResultsField.add(opponentTotalScore);

        if(endOfGame){
            roundResultOfGame(player.getScoreTablePlayer(), questionResults);
            roundResultOfGame(player.getScoreTableOpponent(), questionResults2);
            player1ResultsField.remove(playerRoundScore);
            player2ResultsField.remove(opponentRoundScore);
        }

        buttonsPanel.add(continueButton = new myButton("Next round"));
        continueButton.addActionListener(new myContinueListener());
        buttonsPanel.add(new myButton("Exit"));
    }

    private void roundResultOfGame(List<Integer> list, JPanel panel) {
        panel.setLayout(new GridLayout(list.size(), 1));
        int round = 1;
        for(Integer i : list){
            JLabel label = new JLabel("Round " + round + " points: " + i);
            label.setFont(new Font("Arial", Font.BOLD, 16));
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
        int[] testArr = new int[]{1,5};
        Player player = new Player("Kalle");
        Window test = new Window();
        test.add(new ResultsWindow(2, 1, player, true));
        test.setVisible(true);
    }
}
