package Client.GUI;

import Server.Player;
import shared.Question;
import shared.Rond;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultsWindow extends JPanel {

    private List<Question> rounds;
    private Question question;
    private Player player1;
    private Player player2;
    private int points;

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

    public ResultsWindow(Rond rond, Question question, Player player1, Player player2){}
    public ResultsWindow(int points){

        //rounds = rond.getQuestionList();
        this.question = question;
        this.player1 = player1;
        this.player2 = player2;
        this.points = points;

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
        playerTotalField.add(player2Tot = new JLabel("player2total"));

        /*buttonsPanel.add(continueButton = SetContinueButtonText(rounds, question));*/
        buttonsPanel.add(continueButton = new myButton("Continue"));
        buttonsPanel.add(new myButton("Exit"));
    }

    public JLabel showTotalResult(JPanel panel, int points){
        JLabel resultsLabel = new JLabel("Player 1 total: "+""+points+" || ");
        return resultsLabel;
    }

    /*public JLabel SetCategoryText(Question category){
        JLabel categoryLabel = new JLabel(category.getCategory); //Vi behöver nog lägga till String Category i Question
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        categoryLabel.setForeground(Color.blue);
        return categoryLabel;
    }


    public JPanel setTotalLabel(JPanel panel, int[] points){
        for (int i = 0; i<points.length; ++i) {
            JLabel totalLabel;
            panel.add(totalLabel = new JLabel(""+points[i]));
        }
        return panel;
    }


    public JPanel BuildPlayerResultsField(JPanel resultsField, Player player){ //Lägg till string name i Player?

        JPanel playerResultsField = new JPanel();
        playerResultsField.setBackground(Color.green);
        playerResultsField.setLayout(new BoxLayout(playerResultsField, BoxLayout.Y_AXIS));
        playerResultsField.add(SetPlayerLabel(player));
        playerResultsField.add(questionResults = new JPanel());
        questionResults.setLayout(new BoxLayout(questionResults, BoxLayout.Y_AXIS));
        BuildPlayerResultsLabels(questionResults, player.getPoints); //poängArray i Player
        playerResultsField.add(SetPlayerLabel(player));

        return resultsField;
    }

    public JPanel BuildPlayerResultsLabels(JPanel playerPanel, int[] points){ //Lägg till poängarrayen i Player och skicka players istället för arrayen från server
        //sätt grundvärde i poängarrayen till -1
        for (int i = 0; i<points.length; ++i) {
            if(points[i] == -1){
                JLabel label = new JLabel("Round not played yet");
                label.setFont(new Font("Arial", Font.BOLD, 20));
            }
            else{
                JLabel label = new JLabel("Question "+(i+1) + " - " + points[i] + " points.");
                label.setFont(new Font("Arial", Font.BOLD, 20));
                playerPanel.add(label);
            }

        }
        return playerPanel;
    }

    public JLabel SetPlayerLabel(Player player){
        JLabel playerName = new JLabel(player.getName());
        playerName.setFont(new Font("Arial", Font.BOLD, 20));
        return  playerName;
    }


    public myButton SetContinueButtonText(List<Question> rounds, Question question){
        myButton button;
        if(question == rounds.get(-1)){
            button = new myButton("New game");
        }
        else{
            button = new myButton("Next Round");
        }

        return button;
    }
*/


    public static void main(String[] args) {
        int[] testArr = new int[]{1,5};
        Window test = new Window();
        test.add(new ResultsWindow(2));
        test.setVisible(true);
    }

}
