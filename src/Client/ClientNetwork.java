package Client;

import Client.GUI.Container;
import ClientSide.GUI.ContainerNew;
import ClientSide.GUI.GameWindow;
import ClientSide.GUI.StartWindowNew;

import java.io.*;
import java.net.Socket;

public class ClientNetwork {

    ContainerNew containerNew;

    public ClientNetwork(ContainerNew containerNew) {
        this.containerNew = containerNew;
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


                    containerNew.dispose();

                    ContainerNew gameWindow = new ContainerNew();
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
