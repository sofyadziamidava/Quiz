package Server;

public class Rond {

    Question question1;
    Question question2;

    public Rond() {
        String[] a = new String[]{"a","b","c","d"};
        this.question1 = new Question("1", a);
        this.question2 = new Question("b", a);
    }
}
