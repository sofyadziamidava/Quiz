package Client.GUI;

import Server.Game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameWindow extends JPanel {
    public JLabel question;
    JPanel questionPanel;
    myButton timer;
    myButton[]buttonsArray;


    public GameWindow() {

        timer = new myButton("0");

        setBackground(Color.yellow);
        setLayout(new BorderLayout());

        questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 80));

        question = new JLabel("Whats your name?");
        question.setFont(new Font("Arial",Font.PLAIN,24));
        question.add(timer);

        questionPanel.add(question);
        questionPanel.setBackground(Color.white);

        JPanel timerPanel = new JPanel();
        timerPanel.setBackground(Color.red);



        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 2));
        buttonsPanel.setBackground(Color.GREEN);
        myButton b1 = new myButton("Button1");
        myButton b2 = new myButton("Button2");
        myButton b3 = new myButton("Button3");
        myButton b4 = new myButton("Button4");
        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.add(b3);
        buttonsPanel.add(b4);

        buttonsArray = new myButton[]{b1, b2, b3 ,b4};



        this.add(questionPanel, BorderLayout.NORTH);
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



    public static void main(String[] args) {
        Window testWindow = new Window();
        testWindow.add(testWindow.getGameWindow());
        testWindow.setVisible(true);
    }
}
