package Client;

import Client.GUI.Window;
import shared.Rond;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

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
                    sleep(3000);
                    clientProtocol.handleNewRond(obj);
                    System.out.println("round received ");
                    String pointsToSend = String.valueOf(ClientProtocol.getPointsPerRond());
                    System.out.println("sending points from client to server");
                    dataToServer.writeObject(pointsToSend);
                }
                else if (obj instanceof Integer) {
                    System.out.println("Opponents points: " + obj);
                    waitForContinue();
                    dataToServer.writeObject("a");
                }
                else if (obj instanceof int[]) {
                    System.out.println("Client recived an array");
                    int[] results = (int[])obj;
                    System.out.println(Arrays.toString(results));
                    for (int result: results) {
                        System.out.println(result);
                    }
                    dataToServer.writeObject("b");
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

