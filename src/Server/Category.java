package Server;

import shared.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Category {

    String category;
    List<Question> questions;

    public Category(String category, List q) {
        this.category = category;
        this.questions = q;
    }

    public void addQuestion(Question q) {
        this.questions.add(q);
    }

    public List<Question> getQuestions(int nrOfQuestions) {
        List<Question> q = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        Random rand = new Random();
        int upperbound = questions.size();
        int int_random = rand.nextInt(upperbound);
        while (nrOfQuestions > 0) {
            while (indexes.contains(int_random)) {
                int_random = rand.nextInt(upperbound);
            }
            indexes.add(int_random);
            nrOfQuestions--;
        }
        for (Integer i:indexes
             ) {
            q.add(questions.get(i));
        }
        return q;
    }

    public String getCategory() {
        return category;
    }


}
