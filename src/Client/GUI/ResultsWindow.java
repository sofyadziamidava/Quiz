package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultsWindow extends JPanel {

    private myButton continueButton;

    private JPanel resultsPanel;
    private JPanel player1ResultsPanel;
    private JPanel player2ResultsPanel;
    private JPanel buttonsPanel;

    private JLabel player1;
    private JLabel player2;
    private JLabel player1Tot;
    private JLabel player2Tot;
    private JLabel player1Results;
    private JLabel player2Results;
    private JLabel topText;

    public ResultsWindow(){

        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(topText = new JLabel("Results")); //Results+kategori+round*
        add(resultsPanel = new JPanel());
        add(buttonsPanel = new JPanel());

        resultsPanel.setLayout(new GridLayout(0,2));
        resultsPanel.add(player1ResultsPanel = new JPanel());
        player1ResultsPanel.add(player1Results = new JLabel("1"));
        player1ResultsPanel.add(player1Tot = new JLabel("11"));
        resultsPanel.add(player2ResultsPanel = new JPanel());
        player2ResultsPanel.add(player2Results = new JLabel("2"));
        player2ResultsPanel.add(player2Tot = new JLabel("22"));

        buttonsPanel.add(continueButton = new myButton("Continue"));
        buttonsPanel.add(new myButton("Exit"));
    }

    public void SetCategoryText(){

    }

    public String SetButton1Text(String text){


        return text;
    }

    public JLabel BuildPlayerResultsLabel(){
        JLabel results = new JLabel();

        return results;
    }



    public static void main(String[] args) {
        Window test = new Window();
        test.add(new ResultsWindow());
        test.setVisible(true);
    }

}
