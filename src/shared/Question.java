package shared;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String question;
    private List<String> answers;

    public Question(String question) {
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


}
