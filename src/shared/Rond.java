package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Rond implements Serializable {
    Question question1;
    Question question2;

    public Rond(ArrayList<Question> q) {
        this.question1 = q.get(0);
        this.question2 = q.get(1);
    }

    public Question getQuestion1() {
        return question1;
    }

    public Question getQuestion2() {
        return question2;
    }
}
