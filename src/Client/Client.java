package Client;

import Client.GUI.StartWindow;
import Client.GUI.Window;

public class Client {

    Window window;
    StartWindow startWindow;
    ClientNetwork clientNetwork;
    Player player;


    public Client() {
        createWindow();
        connectToServer();
    }

    private void connectToServer() {
        while(true){
            ClientNetwork.sleep(500);
            boolean connectButtonPressed = startWindow.getReady();
            if(connectButtonPressed){
                this.player = new Player(startWindow.getSavedName());
                clientNetwork = new ClientNetwork(window, player);
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
