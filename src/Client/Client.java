package Client;

import Client.GUI.StartWindow;
import Client.GUI.Window;

public class Client {

    private Window window;
    private StartWindow startWindow;

    public Client() {
        createWindow();
        connectToServer();
    }

    private void connectToServer() {
        while(true){
            ClientNetwork.sleep(500);
            boolean connectButtonPressed = startWindow.getReady();
            if(connectButtonPressed){
                new ClientNetwork(window, new Player(startWindow.getSavedName()));
            }
        }
    }

    private void createWindow() {
        window = new Window();
        startWindow = new StartWindow();
        window.add(startWindow);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        new Client();
    }
}
