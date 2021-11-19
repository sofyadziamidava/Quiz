package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Rond implements Serializable {

    ArrayList<Question> questionList;

    public Rond(ArrayList<Question> questions) {

        this.questionList = questions;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
}
