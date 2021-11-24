package Client.GUI;

import Client.ClientProtocol;
import Client.GUI.Font.GUIFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JPanel{

    private boolean ready;
    private JTextArea name;
    private JPanel botton;
    private JPanel titlePanel;
    private JPanel loginPanel;
    private String savedName;
    private JLabel nameLabel;
    private myButton startButton;

    public StartWindow() {
        ready = false;
        createTitlePanel();
        createLoginPanel();
        createBottomPanel();
        setMainPanelSettings();
    }

    private void createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout());
        JLabel title = new JLabel("QuizCamp");
        title.setFont(GUIFont.customFont(40));
        titlePanel.setBackground(GUIFont.backgroundColor);
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        loginPanel.setBackground(GUIFont.backgroundColor);
        nameLabel = new JLabel("Enter name:");
        nameLabel.setFont(GUIFont.customFont(20));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        name = new JTextArea();
        name.setFont(GUIFont.customFont(20));
        name.setPreferredSize(new Dimension(100, 30));
        loginPanel.add(nameLabel);
        loginPanel.add(name);
    }

    private void createBottomPanel() {
        StartAction buttonAction = new StartAction();
        botton = new JPanel();
        botton.setBackground(GUIFont.backgroundColor);
        startButton = new myButton("Find opponent");
        startButton.setBackground(Color.white);
        startButton.setPreferredSize(new Dimension(120, 40));
        botton.add(startButton);
        startButton.addActionListener(buttonAction);
    }

    private void setMainPanelSettings() {
        this.setBackground(new Color(205,230,255));
        this.setLayout(new GridLayout(3,1));
        this.add(titlePanel);
        this.add(loginPanel);
        this.add(botton);
    }

    public String getSavedName() {
        return savedName;
    }

    public boolean getReady() {
        return ready;
    }

    private class StartAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            savedName = name.getText();
            ClientProtocol.clientName = savedName;
            System.out.println(savedName);
            nameLabel.setText("Hello " + savedName + ", Please wait for the game to start...");
            name.setVisible(false);
            startButton.setVisible(false);
            ready = true;
        }
    }
}
