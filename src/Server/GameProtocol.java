package Server;

import java.io.IOException;

public class GameProtocol {
    public static final int NEWROND = 0;
    public static final int WAITINGFORRESULTS = 1;
    private static final int ENDRESULT = 2;
    public int state = NEWROND;

    Game game;

    public GameProtocol(Game game) {
        this.game = game;
    }

    public void gameProcess(String[] input) throws IOException, ClassNotFoundException {

        if (state == NEWROND) {
            game.sendRounds();          // Rond
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS) {
            if (game.getCurrentRound() == game.getNrOfRounds() - 1) {
                game.sendAllOpponentResultsToClient();   // int[]
                state = ENDRESULT;
            } else {
                game.sendingOpponentResultToClients();  // int
                state = NEWROND;
            }
        } else if (state == ENDRESULT) {
            game.interrupt();
        }
    }


}

