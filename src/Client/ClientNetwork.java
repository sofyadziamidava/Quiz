package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNetwork {

    GameWindow gameWindow;

    public ClientNetwork() {
        gameWindow = new GameWindow();
        connectToServer();
    }

    public void connectToServer() {
        int serverPort = 42424;
        String serverHost = "127.0.0.1";

        try(Socket clientSocket = new Socket(serverHost, serverPort);
            BufferedReader dataFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter dataToServer = new PrintWriter(clientSocket.getOutputStream(), true);)
        {
            System.out.println("Connection established...");
            String messageFromServer;
            while((messageFromServer = dataFromServer.readLine()) != null){
                gameWindow.getMessageFromServer(messageFromServer);
                System.out.println("Data from server: " + messageFromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
