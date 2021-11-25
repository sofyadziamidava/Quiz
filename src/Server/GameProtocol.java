package Server;

import java.io.IOException;

public class GameProtocol {
    public static final int FIRSTROND = 0;
    public static final int NEWROND = 1;
    public static final int WAITINGFORRESULTS = 2;
    public int state = FIRSTROND;

    private Game game;

    public GameProtocol(Game game) {
        this.game = game;
    }

    public void gameProcess(String[] input) throws IOException, ClassNotFoundException {

        if (state == FIRSTROND) {
            game.sendNames(input);
            game.sendRounds();
            state = WAITINGFORRESULTS;
        } else if (state == NEWROND) {
            game.sendRounds();
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS) {
            game.sendingOpponentResultToClients(input);
            if (game.getCurrentRound() == game.getNrOfRounds() - 1) {
                game.sendEndOfGame();
                game.ends();
            } else {
                state = NEWROND;
            }
            game.updateRound();
        }
    }


}

