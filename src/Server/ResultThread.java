/*
package Server;

import java.io.IOException;

public class ResultThread extends Thread{
    private Player player;
    private Object result = null;


    public ResultThread(Player player) {
        this.player = player;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public void run() {
        try {
            while (player.receive() != null)
            result = player.receive();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
*/
