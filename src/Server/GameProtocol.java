package Server;

import java.io.IOException;

public class GameProtocol {
    //public static final int INIT = 0;
    public static final int NEWROND = 1;
    public static final int WAITINGFORRESULTS = 2;
    private static final int ENDRESULT = 3;
    public int state = NEWROND;

    private Game game;

    public GameProtocol(Game game) {
        this.game = game;
        }

    public void gameProcess(String[] input) throws IOException, ClassNotFoundException {

        /*if (state == INIT) {
            game.getPlayer1().setName(input[0]);
            game.getPlayer2().setName(input[1]);
            game.getPlayer1().send(game.getPlayer2().name);
            game.getPlayer2().send(game.getPlayer1().name);
            state = NEWROND;
        }*/
        if (state == NEWROND) {   // -> null,null
            game.sendRounds();          // Rond
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS) {
            game.updatePoints(input);    // should add new points to players
            if (game.getCurrentRound() == game.getNrOfRounds() - 1) {
                game.sendAllOpponentResultsToClient();   // int[]
                //game.sendingOpponentResultToClients();
                game.sendOpponentPlayer();
                state = ENDRESULT;
            } else {
                game.sendingOpponentResultToClients();  // int
                game.sendOpponentPlayer();
                state = NEWROND;
            }
        } else if (state == ENDRESULT) {
            game.ends();
        }
    }


}

