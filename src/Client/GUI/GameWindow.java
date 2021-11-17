package Client.GUI;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    public JLabel question;
    JPanel questionPanel;


    public GameWindow() {

        setBackground(Color.yellow);
        setLayout(new BorderLayout());

        questionPanel = new JPanel();
        question = new JLabel("Whats your name?");
        questionPanel.add(question);
        questionPanel.setBackground(Color.white);

        JPanel timerPanel = new JPanel();
        timerPanel.setPreferredSize(new Dimension(750, 250));
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


        this.add(questionPanel, BorderLayout.NORTH);
        this.add(timerPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void displayQuestion(String question) {
        this.question.setText(question);

    }
}
