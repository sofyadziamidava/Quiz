package Client.GUI;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {

        this.setSize(750, 450);
        this.setTitle("QuizKampen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public GameWindow getGameWindow() {
        return new GameWindow();
    }

    public static void main(String[] args) {
        new Window();
    }

}
