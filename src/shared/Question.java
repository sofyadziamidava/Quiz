package shared;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String question;
    private List<String> answers;

    public Question(String question, List<String> answers){
        this.question = question;
        this.answers = answers;
    }
    public Question(String question){
        this.question = question;

    }

    public void addAnswers(List a) {
        this.answers = a;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return this.answers;
    }

    public String toString() {
        String s = null;
        for (String a: answers
             ) {
            s = s + a;
        }
        return s;
    }

}
