package shared;

import java.io.Serializable;
import java.util.List;

public class Rond implements Serializable {
    List<Question> questionList;


    public Rond(List<Question> questions) {

        this.questionList = questions;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
