package Server;

import shared.Question;
import shared.Rond;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Database {

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Integer> alreadySelectedCategories = new ArrayList<>();

    public Database() {
        loadCategories();
    }

    public void loadCategories() {
        try {
            final List<String> lines = Files.readAllLines(Paths.get("src/Server/questionsQuiz.txt"), StandardCharsets.UTF_8);
            for (String line:lines
            ) {
                if (line.contains("#")) {
                    categories.add(new Category(line.substring(1)));
                }
                else if (line.contains("?")) {
                    categories.get(categories.size()-1).addQuestion(new Question(line));
                }
                else if (line.contains(",")) {
                    Category c = categories.get(categories.size()-1);
                    c.getLatestQuestion().addAnswers(Arrays.asList(line.split(",")));
                }
            }
        }
        catch (IOException e) {
            System.out.println("could not open file");
        }
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
        rond.addQuestions(c.generateQuestions(nrOfQuestions));
        return rond;
    }


}
