package Client;

import Client.GUI.GameWindow;
import Client.GUI.Window;
import Server.Rond;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;

public class ClientNetwork {

    Window window;

    public ClientNetwork(Window window) {
        this.window = window;
        connectToServer();
    }

    public void connectToServer() {
        int serverPort = 42424;
        String serverHost = "127.0.0.1";

        try (Socket clientSocket = new Socket(serverHost, serverPort);
             ObjectOutputStream dataToServer = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream dataFromServer = new ObjectInputStream(clientSocket.getInputStream());
        ) {
            Object obj;
            while ((obj = dataFromServer.readObject()) != null) {
                if (obj instanceof Rond) {

                    sleep();
                    window.dispose();

                    Window gameWindow = new Window();
                    GameWindow gw = gameWindow.getGameWindow();
                    gameWindow.add(gw);
                    gameWindow.setVisible(true);

                    sleep();
                    gw.question.setText("How old are you?");
                } else if (obj instanceof Integer) {
                } else if (obj instanceof Array) {
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

