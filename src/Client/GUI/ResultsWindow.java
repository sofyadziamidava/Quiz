package Client.GUI;

import Client.ClientProtocol;
import Client.Player;
import shared.Question;
import shared.Rond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultsWindow extends JPanel {

    private List<Question> rounds;
    private Question question;
    private Player player1;
    private Player player2;
    private int points;
    private int opponentResult;

    Player playerNew;

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

    public ResultsWindow(int points, int opponentResult, Player player){

        this.playerNew = player;

        //rounds = rond.getQuestionList();
        this.question = question;
        this.player1 = player1;
        this.player2 = player2;
        this.points = points;
        this.opponentResult = opponentResult;

        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(topText = new JLabel("Results"));
        /*SetCategoryText(question);*/
        add(categoryLabel = new JLabel("Category"));
        categoryLabel.setForeground(Color.blue);

        add(resultsPanel = new JPanel());
        add(playerTotalField = new JPanel());
        add(buttonsPanel = new JPanel());

        resultsPanel.setBackground(Color.CYAN);
        resultsPanel.setLayout(new GridLayout(0,2));
        /*resultsPanel.add(playerResultsField = BuildPlayerResultsField(playerResultsField, player1));
        *resultsPanel.add(playerResultsField = BuildPlayerResultsField(playerResultsField, player2));*/
        resultsPanel.add(player1ResultsField = new JPanel());
        resultsPanel.add(player2ResultsField = new JPanel());

        player1ResultsField.setBackground(Color.green);
        player1ResultsField.setLayout(new BoxLayout(player1ResultsField, BoxLayout.Y_AXIS));
        player1ResultsField.add(player1Label = new JLabel("Player1"));
        player1ResultsField.add(questionResults = new JPanel());
        player1ResultsField.add(showTotalResult(player1ResultsField, points));

        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        questionResults.add(player1Results = new JLabel("player1results Q1"));
        questionResults.add(new JLabel("Player1results Q2"));
        questionResults.add(new JLabel("Player1results Q3"));

        player2ResultsField.setBackground(Color.pink);
        player2ResultsField.setLayout(new BoxLayout(player2ResultsField, BoxLayout.Y_AXIS));
        player2ResultsField.add(player2Label = new JLabel("Player2"));
        player2ResultsField.add(questionResults = new JPanel());
        player2ResultsField.add(player2Tot = new JLabel("player2total"));

        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        questionResults.add(player2Results = new JLabel("player2results Q1"));
        questionResults.add(new JLabel("Player2results Q2"));
        questionResults.add(new JLabel("Player2results Q3"));

        playerTotalField.setBackground(Color.YELLOW);
        playerTotalField.add(showTotalResult(playerTotalField, points));
        playerTotalField.add(showTotalResult(playerTotalField, opponentResult));

        /*buttonsPanel.add(continueButton = SetContinueButtonText(rounds, question));*/
        buttonsPanel.add(continueButton = new myButton("Continue"));
        continueButton.addActionListener(new myContinueListener());
        buttonsPanel.add(new myButton("Exit"));
    }

    public JLabel showTotalResult(JPanel panel, int points){
        JLabel resultsLabel = new JLabel("Player 1 total: "+""+points+" || ");
        return resultsLabel;
    }

    private class myContinueListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientProtocol.waitingForNextRound = false;
        }
    }
}
