package Server;

import java.io.IOException;

public class Game extends Thread{

    int nrOfRounds;
    int currentRound;
    Player[] players = new Player[2];
    int currentPlayer;
    int[] player1SumPoints = new int[nrOfRounds];
    int[] player2SumPoints = new int[nrOfRounds];

    public Game(Player player1, Player player2) {
        this.players[0] = player1;
        this.players[1] = player2;
        currentPlayer = 0;

    }

    public int getNrOfRounds(){
        return nrOfRounds;
    }
    public int getCurrentRound() {
        return currentRound;
    }

    /*
    Creating a round and sending it to both clients
     */
    public void sendRounds() {
        Rond rond = new Rond();
        for (Player player:players) {
            try {
                player.send(rond);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("could not send round to player");
            }
        }
    }

    public void waitingForAnswers() {
        int answerFromClient = 0;
        while(answerFromClient < 2) {
            String nrOfCorrectAnswers;
            try {
                if((nrOfCorrectAnswers = players[0].receive()) != null  ) {
                    player1SumPoints[currentRound] = Integer.parseInt(nrOfCorrectAnswers);
                    answerFromClient++;
                }
                if((nrOfCorrectAnswers = players[1].receive()) != null  ) {
                    player2SumPoints[currentRound] = Integer.parseInt(nrOfCorrectAnswers);
                    answerFromClient ++;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendingOpponentResultToClients() {
        try {
            players[0].send(player2SumPoints[currentRound]);
            players[1].send(player1SumPoints[currentRound]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAllOpponentResultsToClient() {
        try {
            players[0].send(player2SumPoints);
            players[1].send(player1SumPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        for (currentRound = 0; currentRound < nrOfRounds; currentRound++) {
            GameProtocol protocol = new GameProtocol(this);
            protocol.gameProcess();
        }

        //TODO: how to solve for creating a new game after played???

    }



}

