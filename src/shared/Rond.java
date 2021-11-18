package shared;

import java.io.Serializable;

public class Rond implements Serializable {
    Question question1;
    Question question2;

    public Rond() {
        String[] a = new String[]{"19","25","48","6"};
        String[] b = new String[]{"Stockholm","Oslo","Malmö","6"};
        this.question1 = new Question("Hur gammal är du?", a);
        this.question2 = new Question("Vad är Sveriges huvudstad?", b);
    }

    public Question getQuestion1() {
        return question1;
    }

    public Question getQuestion2() {
        return question2;
    }
}
