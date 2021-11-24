package Client.GUI;

import Client.GUI.Font.GUIFont;

import javax.swing.*;

public class Button extends JButton {

    public Button(String text) {
        super(text);
        this.setFocusable(false);
        this.setBackground(GUIFont.buttonColor);
    }
}

