package Server;

import java.io.Serializable;

public class Rond implements Serializable {
    Question question1;
    Question question2;

    public Rond() {
        String[] a = new String[]{"a","b","c","d"};
        this.question1 = new Question("1", a);
        this.question2 = new Question("b", a);
    }

    public Question getQuestion1() {
        return question1;
    }

    public Question getQuestion2() {
        return question2;
    }
}
