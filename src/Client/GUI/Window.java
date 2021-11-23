package Client.GUI;

import Client.Player;

import javax.swing.*;

public class Window extends JFrame {

    Player player;

    public Window(Player player) {
        this.player = player;

        setSize(750, 450);
        setTitle("QuizKampen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public GameWindow getGameWindow() {
        return new GameWindow(player);
    }

    public StartWindow getStartWindow() {
        return new StartWindow();
    }


}
