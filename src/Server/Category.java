package Server;

import shared.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Category {

    String category;
    List<Question> questions;

    public Category(String category) {
        this.category = category;
        this.questions = new ArrayList<>();
    }

    public Question getLatestQuestion() {
        return questions.get(questions.size()-1);
    }

    public void addQuestion(Question q) {
        this.questions.add(q);
    }

    public List<Question> generateQuestions(int nrOfQuestions) {
        List<Question> questionsForRond = new ArrayList<>();
        for (Integer i : getRandomIndexNumbers(nrOfQuestions)
        ) {
            questionsForRond.add(questions.get(i));
        }
        return questionsForRond;
    }

    public List<Integer> getRandomIndexNumbers(int nrOfQuestions) {
        List<Integer> randomIndexes = new ArrayList<>();
        Random rand = new Random();
        int upperbound = questions.size();
        int int_random = rand.nextInt(upperbound);
        while (nrOfQuestions > 0) {
            while (randomIndexes.contains(int_random)) {
                int_random = rand.nextInt(upperbound);
            }
            randomIndexes.add(int_random);
            nrOfQuestions--;
        }
        return randomIndexes;
    }

    public String getCategory() {
        return this.category;
    }


}
