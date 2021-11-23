package Client;

import Client.GUI.Window;
import shared.Rond;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientNetwork {

    Window window;
    Player player;

    public ClientNetwork(Window window, Player player) {
        this.window = window;
        this.player = player;
        connectToServer();
    }

    public void connectToServer() {
        int serverPort = 42424;
        String serverHost = "127.0.0.1";
        ClientProtocol clientProtocol = new ClientProtocol(window, player);

        try (Socket clientSocket = new Socket(serverHost, serverPort);
             ObjectOutputStream dataToServer = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream dataFromServer = new ObjectInputStream(clientSocket.getInputStream());
        ) {
            Object obj;
            while ((obj = dataFromServer.readObject()) != null) {

                if (obj instanceof Rond) {
                    System.out.println("round received ");
                    sleep(3000);
                    clientProtocol.handleNewRond(obj);
                    String pointsToSend = String.valueOf(ClientProtocol.getPointsPerRond());
                    System.out.println("sending " + pointsToSend + " points from client to server");
                    System.out.println("Total score player: " + player.getScore());
                    dataToServer.writeObject(pointsToSend);
                }

                else if (obj instanceof Integer) {
                    int opponentScore = (int)obj;
                    System.out.println("OpponentScore from server: " + opponentScore);
                    System.out.println("Opponent total score: " + player.getOpponentScore());
                    clientProtocol.resultsWindow(opponentScore);
                    dataToServer.writeObject(clientProtocol.waitForContinue());
                    System.out.println("Size of list should end at size 3: " + player.getScoreTablePlayer().size());
                }

                else if (obj instanceof int[]) {
                    int[] results = (int[])obj;
                    clientProtocol.resultsWindow(results[0]);
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

