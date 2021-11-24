package Server;

import shared.Rond;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Game extends Thread {

    Database db;
    int nrOfRounds;
    int nrOfQuestionsPerRound;
    int currentRound;
    PlayerStream[] playerStreams = new PlayerStream[2];
    boolean gameOn = true;

    public Game(PlayerStream ps1, PlayerStream ps2) {
        this.db = new Database();
        this.playerStreams[0] = ps1;
        this.playerStreams[1] = ps2;
        currentRound = 0;
    }

    public PlayerStream getPlayerStream1() {
        return playerStreams[0];
    }

    public PlayerStream getPlayerStream2() {
        return playerStreams[1];
    }

    public void setNrOfRounds(int nrOfRounds) {
        this.nrOfRounds = nrOfRounds;
    }

    public void setNrOfQuestionsPerRound(int nrOfQuestionsPerRound) {
        this.nrOfQuestionsPerRound = nrOfQuestionsPerRound;
    }

    public int getNrOfRounds() {
        return nrOfRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void ends() {
        gameOn = false;
        this.interrupt();
    }

    public void sendRounds() {
        Rond rond = db.createRond(nrOfQuestionsPerRound);
        for (PlayerStream playerStream : playerStreams) {
            try {
                playerStream.send(rond);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("could not send round to player");
            }
        }
    }

    public void updateRound() {
        currentRound++;
    }

    public void sendingOpponentResultToClients(String[] input) {
        try {
            playerStreams[0].send(Integer.parseInt(input[1]));
            playerStreams[1].send(Integer.parseInt(input[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEndOfGame() {
        try {
            playerStreams[0].send(true);
            playerStreams[1].send(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("src/Server/gameData.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setNrOfRounds(Integer.parseInt(p.getProperty("nrOfRounds")));
        this.setNrOfQuestionsPerRound(Integer.parseInt(p.getProperty("nrOfQuestionsPerRound")));
    }

    public void sendNames(String[] input) {
        try {
            playerStreams[0].send(input[1]);
            playerStreams[1].send(input[0]);
        }
        catch (IOException e) {
            System.out.println("could not send name");
        }
    }

    public void run() {

        this.loadData();
        GameProtocol protocol = new GameProtocol(this);
        String[] input = new String[2];

        try {
            input[0] = getPlayerStream1().receive();
            input[1] = getPlayerStream2().receive();
            protocol.gameProcess(input);  // skickar namn till den andra spelaren

            while (gameOn) {
                String player1Input = getPlayerStream1().receive();
                String player2Input = getPlayerStream2().receive();
                if (player1Input != null && player2Input != null) {
                    input[0] = player1Input;
                    input[1] = player2Input;
                    protocol.gameProcess(input);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

