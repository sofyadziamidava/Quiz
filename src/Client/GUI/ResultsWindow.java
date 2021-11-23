package Client.GUI;

import Client.ClientProtocol;
import shared.Player;
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

    public ResultsWindow(Rond rond){

        rounds = rond.getQuestionList();
        this.player1 = ClientProtocol.clientPlayer;
        this.player2 = ClientProtocol.opponentPlayer;

        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(topText = new JLabel("Results"));
        SetCategoryText(rond);
        add(categoryLabel = new JLabel("Category"));
        categoryLabel.setForeground(Color.blue);

        add(resultsPanel = new JPanel());
        add(playerTotalField = new JPanel());
        add(buttonsPanel = new JPanel());

        resultsPanel.add(playerResultsField = BuildPlayerResultsField(playerResultsField, player1));
        resultsPanel.add(playerResultsField = BuildPlayerResultsField(playerResultsField, player2));

        playerTotalField.setBackground(Color.YELLOW);
        playerTotalField.add(setRoundTotalLabel(player1));
        playerTotalField.add(setRoundTotalLabel(player2));

        buttonsPanel.add(continueButton = SetContinueButtonText());
        continueButton.addActionListener(new myContinueListener());
        buttonsPanel.add(new myButton("Exit"));

    }
    public ResultsWindow(int points, int opponentResult){


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
        player1ResultsField.add(showTotalResult(points));

        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        questionResults.add(player1Results = new JLabel("player1results Q1"));
        questionResults.add(new JLabel("Player1results Q2"));
        questionResults.add(new JLabel("Player1results Q3"));

        player2ResultsField.setBackground(Color.pink);
        player2ResultsField.setLayout(new BoxLayout(player2ResultsField, BoxLayout.Y_AXIS));
        player2ResultsField.add(player2Label = new JLabel("Player2"));
        player2ResultsField.add(questionResults = new JPanel());
        player2ResultsField.add(showTotalResult(opponentResult));

        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        questionResults.add(player2Results = new JLabel("player2results Q1"));
        questionResults.add(new JLabel("Player2results Q2"));
        questionResults.add(new JLabel("Player2results Q3"));

        playerTotalField.setBackground(Color.YELLOW);
        playerTotalField.add(setRoundTotalLabel(ClientProtocol.clientPlayer));
        playerTotalField.add(setRoundTotalLabel(ClientProtocol.opponentPlayer));
        //playerTotalField.add(showTotalResult(ClientProtocol.clientScoreTotal));
        //playerTotalField.add(showTotalResult(ClientProtocol.opponentScoreTotal));

        buttonsPanel.add(continueButton = SetContinueButtonText());
        //buttonsPanel.add(continueButton = new myButton("Continue"));
        continueButton.addActionListener(new myContinueListener());
        buttonsPanel.add(new myButton("Exit"));
    }

    public String checkWinner(){
        if
    }

    public JLabel showTotalResult(int points){
        JLabel resultsLabel = new JLabel("Player 1 total: "+""+points+" || ");
        return resultsLabel;
    }

    private class myContinueListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientProtocol.waitingForNextRound = false;
        }
    }


    public JLabel SetCategoryText(Rond category){
        JLabel categoryLabel = new JLabel(category.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        categoryLabel.setForeground(Color.blue);
        return categoryLabel;
    }





    public JPanel BuildPlayerResultsField(JPanel resultsField, Player player){

        JPanel playerResultsField = new JPanel();
        playerResultsField.setBackground(Color.green);
        playerResultsField.setLayout(new BoxLayout(playerResultsField, BoxLayout.Y_AXIS));
        playerResultsField.add(SetPlayerLabel(player));
        playerResultsField.add(questionResults = new JPanel());
        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        BuildPlayerResultsLabels(questionResults, player);

        return resultsField;
    }

    public void BuildPlayerResultsLabels(JPanel playerPanel, Player player){

        for (int i = 0; i<rounds.size(); ++i) {
            if(player.getCurrentRoundList() == null){
                JLabel label = new JLabel("Round not played yet");
                label.setFont(new Font("Arial", Font.BOLD, 20));
            }
            else{
                JLabel label = new JLabel("Question "+(i+1) + " - " + player.getPointForSpecificQuestion(ClientProtocol.currentRound, i) + " points.");
                label.setFont(new Font("Arial", Font.BOLD, 20));
                playerPanel.add(label);
            }
        }
    }

    public JLabel SetPlayerLabel(Player player){
        JLabel playerName;
        if(player == null){
            playerName = new JLabel("Player null");
            playerName.setFont(new Font("Arial", Font.BOLD, 20));
        }
        else {
            playerName = new JLabel(player.getName());
            playerName.setFont(new Font("Arial", Font.BOLD, 20));
        }
        return  playerName;
    }

    public JLabel setRoundTotalLabel(Player player){
        JLabel totalLabel = new JLabel("Current total: "+""+player.getTotalForAllRounds());
        return totalLabel;
    }


    public myButton SetContinueButtonText(){
        myButton button;
        if(ClientProtocol.currentRound == ClientProtocol.totalRounds){
            button = new myButton("New game");
            //lägg till action för nytt spel
        }
        else{
            button = new myButton("Next Round");
            button.addActionListener(new myContinueListener());
        }

        return button;
    }



    /*public static void main(String[] args) {
        int[] testArr = new int[]{1,5};
        Window test = new Window();
        test.add(new ResultsWindow();
        test.setVisible(true);
    }
*/
}
