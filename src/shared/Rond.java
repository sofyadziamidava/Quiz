package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rond implements Serializable {
    List<Question> questionList = new ArrayList<>();

    public Rond() {
        List<String> answers = Arrays.asList("19","25","48","6");
        List<String> b = Arrays.asList("Stockholm","Oslo","Malmö","Warszawa");
        Question question1 = new Question("Hur gammal är du?", answers);
        Question question2 = new Question("Vad är Sveriges huvudstad?", b);
        questionList.add(question1);
        questionList.add(question2);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
