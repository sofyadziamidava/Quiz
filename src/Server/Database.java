package Server;

import shared.Question;
import shared.Rond;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class Database {

    // you can't play more rounds than there are categories -> infinite loop in getRandomCategory()

    private ArrayList<Category> categories = new ArrayList<>();

    private ArrayList<Question> q1 = new ArrayList<>();
    private ArrayList<Question> q2 = new ArrayList<>();
    private ArrayList<Question> q3 = new ArrayList<>();
    private ArrayList<Question> q4 = new ArrayList<>();

    ArrayList<Integer> alreadySelectedCategories = new ArrayList<>();

    public Database() {
        categories.add(new Category("a", q1));
        categories.add(new Category("a", q2));
        categories.add(new Category("a", q3));
        categories.add(new Category("a", q4));
    }

    public void loadCategories() {

    }

    public Category getRandomCategory() {
        Random rand = new Random();
        int upperbound = categories.size();
        int int_random = rand.nextInt(upperbound);

        while (alreadySelectedCategories.contains(int_random)) {
            int_random = rand.nextInt(upperbound);
        }
        alreadySelectedCategories.add(int_random);
        return categories.get(int_random);
    }

    public Rond createRond(int nrOfQuestions) {
        Category c = getRandomCategory();
        Rond rond = new Rond(c.getCategory());
        rond.addQuestions(c.getQuestions(nrOfQuestions));
        return rond;
    }




}


