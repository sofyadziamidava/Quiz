package Server;

import shared.Rond;

import java.io.IOException;

public class Game extends Thread {

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
        currentRound = 0;
        nrOfRounds = 1;
    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public int getNrOfRounds() {
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
        for (Player player : players) {
            try {
                player.send(rond);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("could not send round to player");
            }
        }
    }

 /*   public boolean gotAnswersFromBothClients(String[] input) {
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
        return true;
    }*/

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

        GameProtocol protocol = new GameProtocol(this);
        String player1Input = null;
        String player2Input = null;
        String[] input = new String[2];
        try {
            input[0] = null;
            protocol.gameProcess(input);

            while (true) {
                player1Input = getPlayer1().receive();
                player2Input = getPlayer2().receive();
                if (player1Input != null && player2Input != null) {
                    input[0] = player1Input;
                    input[1] = player2Input;
                    protocol.gameProcess(input);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*for (currentRound = 0; currentRound < nrOfRounds; currentRound++) {
            GameProtocol protocol = new GameProtocol(this);
            try {
                protocol.gameProcess();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }


}

