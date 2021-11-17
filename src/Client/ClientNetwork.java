package Client;

import Client.GUI.GameWindow;
import Client.GUI.Window;
import Server.Rond;

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

        try (Socket clientSocket = new Socket(serverHost, serverPort);
             ObjectInputStream dataFromServer = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream dataToServer = new ObjectOutputStream(clientSocket.getOutputStream());) {
            //Få en Rond objekt från servern
            //Lägga info från ronden i window
            //Skicka resultat till servern när spelaren är klar
            //Få opponentens resultat, visa den
            //Ta emot en Rond igen

            ClientProtocol clientProtocol = new ClientProtocol(window);

            Object o;
            while ((o = dataFromServer.readObject()) != null) {
                clientProtocol.handleNewRond(o);

            }
        } catch(IOException |
    ClassNotFoundException e)

    {
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

