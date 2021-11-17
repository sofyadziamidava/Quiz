package Client;

import java.time.Duration;
import java.time.Instant;

public class Clock{

    Instant start;

    public Clock(){}

    public void timeStart() {
        this.start = Instant.now();
    }

    public String elapsedTime(){
        Instant now = Instant.now();
        if (Duration.between(start, now) == null){
            return "0";
        }
        else {
            return Long.toString(Duration.between(start, now).toSeconds());
        }
    }
}

