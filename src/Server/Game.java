package Server;

public class Game extends Thread{

    int nrOfRounds;
    Player[] players = new Player[2];
    int currentPlayer;
    int[] player1SumPoints = new int[nrOfRounds];
    int[] player2SumPoints = new int[nrOfRounds];

    public Game(Player player1, Player player2) {
        this.players[0] = player1;
        this.players[1] = player2;
        this.currentPlayer = 0;
    }

    public void run() {

        for (int i = 0; i < nrOfRounds; i++) {
            // starta en runda, skicka med spelarna
            // registrera spelarnas poäng per runda
        }
        // skicka slutresultat till användaren

    }



}
