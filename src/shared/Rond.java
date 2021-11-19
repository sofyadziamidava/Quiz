package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rond implements Serializable {
    List<Question> questionList = new ArrayList<>();

    public Rond() {
        String[] a = new String[]{"19","25","48","6"};
        String[] b = new String[]{"Stockholm","Oslo","Malmö","6"};
        Question question1 = new Question("Hur gammal är du?", a);
        Question question2 = new Question("Vad är Sveriges huvudstad?", b);
        questionList.add(question1);
        questionList.add(question2);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
