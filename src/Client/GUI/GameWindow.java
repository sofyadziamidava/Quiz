package Client.GUI;

import Client.ClientProtocol;
import Client.Clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameWindow extends JPanel implements ActionListener {

    public JLabel question;
    JLabel timerText;
    JLabel timerLabel;
    JPanel questionPanel;
    JPanel buttonsPanel;
    myButton[]buttonsArray;
    Clock timer;
    private String correctAnswer;
    private boolean buttonPressed = false;
    myButton[] buttonsInGame;
    ClientProtocol clientProtocol;


    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public GameWindow(ClientProtocol clientProtocol) {
        this.clientProtocol = clientProtocol;

        buttonsArray = new myButton[4];
        timerLabel = new JLabel();
        timer = new Clock(timerLabel);


        setBackground(new Color(205,230,255));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 80));

        question = new JLabel("Whats your name?");
        question.setFont(new Font("Arial",Font.PLAIN,24));


        questionPanel.add(question);
        questionPanel.setBackground(Color.white);


        JPanel timerPanel = new JPanel();
        timerPanel.setBackground(new Color(205,230,255));
        timerText = new JLabel("Time left: ");
        timerText.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setFont(new Font("Arila", Font.BOLD, 20));
        timerPanel.add(timerText);
        timerPanel.add(timerLabel);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 2));
        buttonsPanel.setBackground(new Color(205,230,255));
        this.buttonsInGame = createButtons(buttonsArray, buttonsPanel);

        timer.start();


        this.add(questionPanel, BorderLayout.NORTH);
        add(timerPanel);
        this.add(buttonsPanel, BorderLayout.CENTER);
    }

    public void displayQuestion(String question) {
        this.question.setText(question);
    }



    public void displayButtons(List<String> list){
        for (int i = 0; i < buttonsArray.length; i++) {
            buttonsArray[i].setText(list.get(i));
        }
    }

    public myButton[] createButtons(myButton[] buttonsArray, JPanel buttonsPanel){
        myButton[] b = buttonsArray;
        //lägga till ActionListener, intern klass
        for (int i = 0; i<4; ++i){
            myButton button = new myButton("button"+i);
            button.addActionListener(this);
            buttonsPanel.add(button);
            b[i] = button;
        }
        return b;
    }


    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        myButton temp = (myButton) e.getSource();
        if(temp.getText().equals(correctAnswer)){
            temp.setBackground(Color.green);
            clientProtocol.increasePoint();
        } else{
            temp.setBackground(Color.red);
        }
        setButtonsEnabled();
        buttonPressed = true;

    }

    private void setButtonsEnabled() {
        //set all buttons to -> setEnable(false);
        for(myButton button : buttonsInGame){
            button.setEnabled(false);
        }
    }
}
