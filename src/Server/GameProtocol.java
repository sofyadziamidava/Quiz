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

    public void gameProcess() throws IOException, ClassNotFoundException {

        if (state == NEWROND) {
            game.getPlayer1().send("NEWROND");
            game.getPlayer2().send("NEWROND");
            //game.sendRounds();
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS){
            System.out.println(game.getPlayer1().receive());
            System.out.println(game.getPlayer2().receive());
            game.getPlayer1().send("WAITINGFORRESULTS");
            game.getPlayer2().send("WAITINGFORRESULTS");
            //game.waitingForAnswers();
            state = SENDINGRESULTS;
            }
         else if (state == SENDINGRESULTS) {
            System.out.println(game.getPlayer1().receive());
            System.out.println(game.getPlayer2().receive());
            game.getPlayer1().send("SENDINGRESULTS");
            game.getPlayer2().send("SENDINGRESULTS");
             //game.sendingOpponentResultToClients();
             if (game.getCurrentRound() == game.getNrOfRounds()-1) {
                 state = ENDRESULT;
             }
        } else if(state == ENDRESULT){
            System.out.println(game.getPlayer1().receive());
            System.out.println(game.getPlayer2().receive());
            game.getPlayer1().send("ENDRESULT");
            game.getPlayer2().send("ENDRESULT");
            //game.sendAllOpponentResultsToClient();
        }
    }




}
