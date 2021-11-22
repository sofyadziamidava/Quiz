package Server;

import java.io.IOException;

public class GameProtocol {
    public static final int NEWROND = 0;
    public static final int WAITINGFORRESULTS = 1;
    private static final int ENDRESULT = 2;
    public int state = NEWROND;

    private Game game;


    public GameProtocol(Game game) {
        this.game = game;
        }

    public void gameProcess(String[] input) throws IOException, ClassNotFoundException {

        if (state == NEWROND) {   // -> null,null
            System.out.println("First sate - send rond");
            game.sendRounds();          // Rond
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS) {
            System.out.println("updating points in waiting for results");
            game.updatePoints(input);    // should add new points to players
            if (game.getCurrentRound() == game.getNrOfRounds() - 1) {
                System.out.println("Last sate - sending final results");
                game.sendAllOpponentResultsToClient();   // int[]
                state = ENDRESULT;
            } else {
                System.out.println("In gameProtocol before sending results to clients");
                game.sendingOpponentResultToClients();  // int
                state = NEWROND;
            }
        } else if (state == ENDRESULT) {
            System.out.println("Game ends");
            game.ends();
        }
        System.out.println("End of gameProcess method");
    }


}

