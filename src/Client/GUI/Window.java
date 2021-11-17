package Client.GUI;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {

        setSize(750, 450);
        setTitle("QuizKampen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public GameWindow getGameWindow() {
        return new GameWindow();
    }

    public StartWindow getStartWindow() {
        return new StartWindow();
    }

    public static void main(String[] args) {
        new Window();
    }

}
