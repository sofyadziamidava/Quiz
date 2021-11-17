package Server;

import java.io.IOException;

public class GameProtocol {
    public static final int NEWROND = 0;
    public static final int WAITINGFORRESULTS = 1;
    public static final int SENDINGRESULTS = 2;
    private static final int ENDRESULT = 3;

    private static final int NUMBEROFRONDS = 1;

    private int state = NEWROND;
    private int currentRond = 0;

    private Rond rond1 = new Rond();
    private Rond rond2 = new Rond();
    private Rond[] ronds = new Rond[]{rond1, rond2};

    private Player players[];

    public GameProtocol(Player[] players) {
        this.players = players;
    }

    private Object[] player1Results = new Object[2];
    private Object[] player2Results = new Object[2];


    public void gameProcess()  {
        ResultThread rtPlayer1 = new ResultThread(players[0]);
        ResultThread rtPlayer2 = new ResultThread(players[1]);
        if (state == NEWROND) {
            try {
                for (Player player : players) {
                    player.send(ronds[currentRond]);
                }
            } catch (IOException e){
                System.out.println("error: " + e.getMessage());
            }
            currentRond++;
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS){
            rtPlayer1.run();
            rtPlayer2.run();
            if(rtPlayer1.getResult() != null && rtPlayer2.getResult() != null) {
                state = SENDINGRESULTS;
            }
        } else if (state == SENDINGRESULTS) {
            try{
            for (Player player : players) {
                player.send(rtPlayer1.getResult());
                player.send(rtPlayer2.getResult());
            }} catch (IOException e){
                System.out.println("error: " + e.getMessage());
            }
            for (int i = 0; i < players.length; i++) {
                player1Results[i] = rtPlayer1.getResult();
                player2Results[i] = rtPlayer2.getResult();
            }
            rtPlayer1.setResult(null);
            rtPlayer2.setResult(null);
            if(currentRond < NUMBEROFRONDS){
                state =NEWROND;
            } else{
                state = ENDRESULT;
            }
        } else if(state == ENDRESULT){
            for (Player player: players) {
                for (int i = 0; i < players.length; i++) {
                    try {
                        player.send(player1Results[i]);
                    } catch (IOException e){
                        System.out.println("error: " + e.getMessage());
                    }
                }
            }
        }
    }



}
