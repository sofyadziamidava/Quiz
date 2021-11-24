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
                    sleep(3000);
                    clientProtocol.handleNewRond(obj);
                    String pointsToSend = String.valueOf(clientProtocol.getPointsPerRondPlayer());
                    dataToServer.writeObject(pointsToSend);

                } else if (obj instanceof Integer) {
                    int opponentScore = (int) obj;
                    clientProtocol.addToScoreTableOpponent(opponentScore);
                    clientProtocol.resultsWindow(opponentScore);
                    dataToServer.writeObject(clientProtocol.waitForContinue());

                } else if (obj instanceof Boolean) {
                    clientProtocol.sendListToOpponentTable();
                    clientProtocol.sendListToPlayerTable();
                    clientProtocol.closeGame();
                    System.out.println("Player total score: " + player.getScore());
                } else if (obj instanceof String){
                    String fromServer = (String)obj;
                    if(fromServer.equals("OPPONENT DISCONNECTED")){
                        System.out.println("Opponent disconnetced");
                        clientProtocol.displayDisconnectWindow();
                        break;
                    }
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

