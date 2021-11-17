package Server;

import java.io.IOException;

public class Game extends Thread{

    Player[] players = new Player[2];

    public Game(Player player1, Player player2) {
        this.players[0] = player1;
        this.players[1] = player2;
    }

    public void run() {

        //TODO: STATES:
        // * spelet startar -> server skickar första ronden
        // * resultat kommer in -> skickar till båda klienter
        // * sista resultat kommer in -> skickar alla resultat till båda klienter och frågar om nytt spel

        GameProtocol gameProtocol = new GameProtocol(players);
        gameProtocol.gameProcess();
    }




}
