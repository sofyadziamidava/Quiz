package Server;

import shared.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Database {

    private final ArrayList<ArrayList<Question>> categories = new ArrayList<>();
    private ArrayList<Question> natur = new ArrayList<>();
    private ArrayList<Question> vetenskap = new ArrayList<>();
    private ArrayList<Question> kultur = new ArrayList<>();
    private ArrayList<Question> samhälle = new ArrayList<>();
    private ArrayList<Question> djur = new ArrayList<>();

    public Database() {
        natur.add(new Question("a", Arrays.asList("a","b","c","d")));
        natur.add(new Question("a", Arrays.asList("a","b","c","d")));

        vetenskap.add(new Question("a", Arrays.asList("a","b","c","d")));
        vetenskap.add(new Question("a", Arrays.asList("a","b","c","d")));

        kultur.add(new Question("a", Arrays.asList("a","b","c","d")));
        kultur.add(new Question("a", Arrays.asList("a","b","c","d")));

        samhälle.add(new Question("a", Arrays.asList("a","b","c","d")));
        samhälle.add(new Question("a", Arrays.asList("a","b","c","d")));

        djur.add(new Question("a", Arrays.asList("a","b","c","d")));
        djur.add(new Question("a", Arrays.asList("a","b","c","d")));

        categories.add(natur);
        categories.add(vetenskap);
        categories.add(kultur);
        categories.add(samhälle);
        categories.add(djur);
    }

    public ArrayList<Question> createRond(String category){
        if (category.equalsIgnoreCase("natur")) {
            return categories.get(0);
        }
        else if (category.equalsIgnoreCase("vetenskap")) {
            return categories.get(1);
        }
        else if (category.equalsIgnoreCase("kultur")) {
            return categories.get(2);
        }
        else if (category.equalsIgnoreCase("samhälle")) {
            return categories.get(3);
        }
        else if (category.equalsIgnoreCase("djur")) {
            return categories.get(4);
        }
        return null;
    }




}
