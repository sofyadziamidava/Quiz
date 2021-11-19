package Client;

import Client.GUI.Window;
import shared.Rond;

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
        ClientProtocol clientProtocol = new ClientProtocol(window);

        try (Socket clientSocket = new Socket(serverHost, serverPort);
             ObjectOutputStream dataToServer = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream dataFromServer = new ObjectInputStream(clientSocket.getInputStream());
        ) {
            Object obj;
            while ((obj = dataFromServer.readObject()) != null) {
                if (obj instanceof Rond) {
                    sleep();
                    clientProtocol.handleNewRond(obj);
                }
                else if (obj instanceof Integer) {

                }
                else if (obj instanceof Array) {

                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

