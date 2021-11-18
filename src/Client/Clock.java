package Client;
import javax.swing.*;

public class Clock extends Thread {

    private int timeLeft = 15;
    JLabel timer;


    public Clock(JLabel timer){
        this.timer = timer;
    }

    public void run() {
        //när tid blir 0 ändra state?
        while (timeLeft > -1) {
            try {
                timer.setText("" + timeLeft);
                Thread.sleep(1000);
                --timeLeft;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


