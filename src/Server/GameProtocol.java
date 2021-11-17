package Server;

import java.io.IOException;

public class GameProtocol {
    public static final int NEWROND = 0;
    public static final int WAITINGFORRESULTS = 1;
    public static final int SENDINGRESULTS = 2;
    private static final int ENDRESULT = 3;
    public int state = NEWROND;

    Game game;

    public GameProtocol(Game game) {
        this.game = game;
    }

    public void gameProcess()  {

        if (state == NEWROND) {
            game.sendRounds();
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS){
            game.waitingForAnswers();
            state = SENDINGRESULTS;
            }
         else if (state == SENDINGRESULTS) {
             game.sendingOpponentResultToClients();
             if (game.getCurrentRound() == game.getNrOfRounds()-1) {
                 state = ENDRESULT;
             }
        } else if(state == ENDRESULT){
            game.sendAllOpponentResultsToClient();
        }
    }




}
