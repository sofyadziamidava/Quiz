package shared;

import java.io.Serializable;
import java.util.List;

public class Rond implements Serializable {

    String category;
    List<Question> questionList;

    public Rond(String category) {
        this.category = category;
    }

    public void addQuestions(List q) {
        this.questionList = q;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }


}
