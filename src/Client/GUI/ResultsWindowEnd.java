package Client.GUI;

import Client.ClientProtocol;
import Client.Player;
import shared.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultsWindowEnd extends JPanel {

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
    private JPanel playerTotalField;

    private JPanel buttonsPanel;

    private JLabel categoryLabel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player1Tot;
    private JLabel player2Tot;
    private JLabel player1Results;
    private JLabel player2Results;
    private JLabel topText;

    public ResultsWindowEnd(Player player){

        int points = 0;
        this.player = player;

        this.roundPoints = points;
        this.opponentResult = opponentResult;

        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(topText = new JLabel("Results"));
        add(categoryLabel = new JLabel("Category"));
        categoryLabel.setForeground(Color.blue);
        add(resultsPanel = new JPanel());
        add(playerTotalField = new JPanel());
        add(buttonsPanel = new JPanel());

        resultsPanel.setBackground(Color.CYAN);
        resultsPanel.setLayout(new GridLayout(0,2));

        resultsPanel.add(player1ResultsField = new JPanel());
        resultsPanel.add(player2ResultsField = new JPanel());

        player1ResultsField.setBackground(Color.green);
        player1ResultsField.setLayout(new BoxLayout(player1ResultsField, BoxLayout.Y_AXIS));
        player1ResultsField.add(player1Label = new JLabel(player.getName()));
        player1ResultsField.add(questionResults = new JPanel());
        player1ResultsField.add(new JLabel(player.getName() + " total: " + points));

        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        questionResultsAdds();


        player2ResultsField.setBackground(Color.pink);
        player2ResultsField.setLayout(new BoxLayout(player2ResultsField, BoxLayout.Y_AXIS));
        player2ResultsField.add(player2Label = new JLabel("Player2"));
        player2ResultsField.add(questionResults = new JPanel());
        player2ResultsField.add(new JLabel("Opponent total: " + opponentResult));

        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        questionResults.add(player2Results = new JLabel("player2results Q1"));
        questionResults.add(new JLabel("Player2results Q2"));
        questionResults.add(new JLabel("Player2results Q3"));

        playerTotalField.setBackground(Color.YELLOW);
        playerTotalField.add(new JLabel(player.getName() + " total: " + points));
        playerTotalField.add(new JLabel("Opponent total: " + opponentResult));

        buttonsPanel.add(continueButton = new myButton("Continue"));
        continueButton.addActionListener(new myContinueListener());
        buttonsPanel.add(new myButton("Exit"));
    }

    private void questionResultsAdds() {
        int counter = 1;
        int round = 1;
        for(List<Integer> list : player.getScoreTablePlayer()){
            questionResults.add(new JLabel("Round " + round));
            for(Integer i : list){
                questionResults.add(new JLabel("player1results Q" + counter + ": " + i));
                counter++;
            }
            counter = 1;
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
        test.add(new ResultsWindowEnd(player));
        test.setVisible(true);
    }
}
