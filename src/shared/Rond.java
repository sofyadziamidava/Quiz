package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rond implements Serializable {
    ArrayList<Question> questionList;


    public Rond(ArrayList<Question> questions) {

        this.questionList = questions;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
}
