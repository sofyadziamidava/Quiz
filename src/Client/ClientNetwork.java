package Client;

import Client.GUI.GameWindow;
import Client.GUI.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            BufferedReader dataFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter dataToServer = new PrintWriter(clientSocket.getOutputStream(), true);)
        {

            String messageFromServer;
            while((messageFromServer = dataFromServer.readLine()) != null){
                if(messageFromServer.equals("Paket")){


                    window.dispose();

                    Window gameWindow = new Window();
                    GameWindow gw = gameWindow.getGameWindow();
                    gameWindow.add(gw);
                    gameWindow.setVisible(true);

                    sleep();
                    gw.question.setText("How old are you?");

                }
            }
        } catch (IOException e) {
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
}

