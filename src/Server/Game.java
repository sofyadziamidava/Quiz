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
    Player[] players = new Player[2];
    int[] player1SumPoints;
    int[] player2SumPoints;
    boolean gameOn = true;

    public Game(Player player1, Player player2) {
        this.db = new Database();
        this.players[0] = player1;
        this.players[1] = player2;
        currentRound = 0;
    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public void setPlayersResultHolder(){
        this.player1SumPoints = new int[this.getNrOfRounds()];
        this.player2SumPoints = new int[this.getNrOfRounds()];
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

    public void updatePoints(String[] input) {
        this.player1SumPoints[currentRound] = Integer.parseInt(input[0]);
        this.player2SumPoints[currentRound] = Integer.parseInt(input[1]);
    }

    public void sendRounds() {
        Rond rond = new Rond(db.createRond());
        for (Player player : players) {
            try {
                player.send(rond);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("could not send round to player");
            }
        }
    }

    public void sendingOpponentResultToClients() {
        try {
            players[0].send(player2SumPoints[currentRound]);
            players[1].send(player1SumPoints[currentRound]);
            currentRound++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAllOpponentResultsToClient() {
        try {
            players[0].send(player2SumPoints[currentRound]);
            players[1].send(player1SumPoints[currentRound]);
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
        this.setPlayersResultHolder();
    }

    public void run() {

        this.loadData();
        GameProtocol protocol = new GameProtocol(this);
        String[] input = new String[2];
        try {
            protocol.gameProcess(input);   // första gången protokollet kallas skickas string med null,null

            while (gameOn) {  // loopen går igång direkt, ligger och väntar på svar från båda
                String player1Input = getPlayer1().receive();
                String player2Input = getPlayer2().receive();
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

