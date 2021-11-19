package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GameProtocol {
    public static final int NEWROND = 0;
    public static final int WAITINGFORRESULTS = 1;
    private static final int ENDRESULT = 2;
    public int state = NEWROND;

    private Game game;


    public GameProtocol(Game game) {
        this.game = game;
        }

    public void loadData() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("Server/gameData.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameProcess(String[] input) throws IOException, ClassNotFoundException {

        if (state == NEWROND) {
            System.out.println("First sate - send rond");
            game.sendRounds();          // Rond
            state = WAITINGFORRESULTS;
        } else if (state == WAITINGFORRESULTS) {
            System.out.println("Second sate - waiting for results");
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
            game.interrupt();
        }
        System.out.println("End of gameProcess method");
    }


}

