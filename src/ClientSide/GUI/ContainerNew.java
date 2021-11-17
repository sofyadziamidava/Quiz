package ClientSide.GUI;

import javax.swing.*;

public class ContainerNew extends JFrame {

    public ContainerNew(){

        setSize(750,450);
        setTitle("QuizKampen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public GameWindow getGameWindow(){
        return new GameWindow();
    }

    public StartWindowNew getStartWindow(){
        return new StartWindowNew();
    }

    public static void main(String[] args) {
        new ContainerNew();
    }

}
