package Client.GUI.Font;

import java.awt.*;

public class GUIFont {

    public static final Color backgroundColor = new Color(205,230,255);
    public static final Color darkerBackgroundColor = new Color(153,204,255);
    public static final Color buttonColor = new Color(200,225,255);

    public static Font customFont(int size){
        return new java.awt.Font("Arial", java.awt.Font.PLAIN, size);
    }
}
