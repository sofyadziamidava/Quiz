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
            ObjectOutputStream dataToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream dataFromServer = new ObjectInputStream(clientSocket.getInputStream());
            )
        {

            String messageFromServer;
            while((messageFromServer = (String)dataFromServer.readObject()) != null){
                if(messageFromServer.equals("NEWROND")){
                    System.out.println(messageFromServer);
                    dataToServer.writeObject("a");


                    sleep();
                    window.dispose();

                    Window gameWindow = new Window();
                    GameWindow gw = gameWindow.getGameWindow();
                    gameWindow.add(gw);
                    gameWindow.setVisible(true);

                    sleep();
                    gw.question.setText("How old are you?");

                }
                else if (messageFromServer.equals("WAITINGFORRESULTS")) {
                    System.out.println(messageFromServer);
                    dataToServer.writeObject("b");

                }
                else if (messageFromServer.equals("SENDINGRESULTS")) {
                    System.out.println(messageFromServer);
                    dataToServer.writeObject("c");
                }
                else if (messageFromServer.equals("ENDRESULT")) {
                    System.out.println(messageFromServer);
                    dataToServer.writeObject("d");

                }
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
}

