package Client;

import Client.GUI.GameWindow;
import Client.GUI.Window;

import java.io.*;
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

        try(Socket clientSocket = new Socket(serverHost, serverPort);
            ObjectInputStream dataFromServer = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream dataToServer = new ObjectOutputStream(clientSocket.getOutputStream());)
        {

            ClientProtocol clientProtocol = new ClientProtocol();

            Rond ronds[] = new Rond[2];
            Object o;
            while((o = dataFromServer.readObject()) != null){
                ronds[0] = (Rond)o;
                int poäng = 0;
                clientProtocol.inputHandler(rond[0]);
                dataToServer.writeObject((Integer) poäng);

                    window.dispose();

                    Window gameWindow = new Window();
                    GameWindow gw = gameWindow.getGameWindow();
                    gameWindow.add(gw);
                    gameWindow.setVisible(true);

                    sleep();
                    gw.question.setText("How old are you?");

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sleep(){
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Rond reciveRond(){
        Object temp;
        Rond rond;
        while((temp = dataFromServer.readObject()) != null){
            rond = (Rond)temp;
    }
        return rond;
    }
}

