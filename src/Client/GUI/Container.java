package Client.GUI;

import javax.swing.*;
import java.awt.*;

public class Container extends JFrame {

    String question;
    Window startWindow;
    Window gameWindow;
    myButton startButton = new myButton("Start");
    myButton questionButton;
    myButton[][] answerButtons = new myButton[2][2];
    String[] answers = new String[4];

    public Container(){

        setSize(750,750);
        setTitle("QuizKampen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        add(startWindow = new Window());
        startWindow.setSize(750,750);
        startWindow.setBackground(Color.BLACK);
        startWindow.add(startButton);
        startButton.addActionListener(e -> System.out.println("hej"));
        //startButton.addActionListener(new listener());

        add(gameWindow = new Window());
        gameWindow.setSize(750, 750);
        gameWindow.setBackground(Color.CYAN);
        for (int i = 0; i<2; ++i) {
            for(int j = 0; i<2; ++j){
                answerButtons[i][j] = new myButton(answers[i]);
            }

        }
    }
    /*
    //istället för lambda
    private class listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("hej");
        }
    }*/
}
