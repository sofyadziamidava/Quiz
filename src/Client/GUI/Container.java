package Client.GUI;

import javax.swing.*;
import java.awt.*;

public class Container extends JFrame {

    String question;
    StartWindow startWindow;
    GameWindow gameWindow;
    myButton startButton = new myButton("Start");
    myButton questionButton;
    myButton[][] answerButtons = new myButton[2][2];
    String[] answers = new String[4];

    public Container(){

        setSize(750,750);
        setTitle("QuizKampen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameWindow = new GameWindow();
        this.add(gameWindow);

        //startWindow = new StartWindow();
        //add(startWindow);


        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
