package Server;

import shared.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class Database {

    private final ArrayList<ArrayList<Question>> categories = new ArrayList<>();
    private ArrayList<Question> natur = new ArrayList<>();
    private ArrayList<Question> vetenskap = new ArrayList<>();
    private ArrayList<Question> kultur = new ArrayList<>();
    private ArrayList<Question> samhälle = new ArrayList<>();
    private ArrayList<Question> djur = new ArrayList<>();

    ArrayList<Integer> alreadySelectedRonds = new ArrayList<>();

    public Database() {
        natur.add(new Question("Vilket annat namn har blomman hästhovsört?",
                Arrays.asList("Tussilago","Lupin","Maskros","Tusensköna")));
        natur.add(new Question("Vad livnär sig blåvalen på?",
                Arrays.asList("Plankton och krill","Korallrev och tång","Fisk och bläckfisk","Hajar och Rockor")));

        vetenskap.add(new Question("Vilket av dessa är ett primtal?",
                Arrays.asList("97","27","35","57")));
        vetenskap.add(new Question("Vilket nummer har det kemiska ämnet koppar i det periodiska systemet?",
                Arrays.asList("29","9","19","16")));

        kultur.add(new Question("Vem har skrivit 'Hamlet'?",
                Arrays.asList("William Shakespeare","Homeros","Mark Twain","H.C. Andersen")));
        kultur.add(new Question("Varifrån kommer 80-tals bandet Alphaville?",
                Arrays.asList("Tyskland","USA","England","Sverige")));

        samhälle.add(new Question("Hur många monarkier existerade i Europa under år 2010?",
                Arrays.asList("12","10","11","8")));
        samhälle.add(new Question("Vem var sedan 2009 ständig sekreterare i Svenska Akademin?",
                Arrays.asList("Peter Englund","Sture Allén","Horace Engdahl","Torgny Lindgren")));

        djur.add(new Question("Var kan man beskåda en vild anakonda?",
                Arrays.asList("Sydamerika","Afrika","Asien","Australien")));
        djur.add(new Question("Vilken art är Sveriges minsta fågel?",
                Arrays.asList("Kungsfågel","Blåmes","Sävsångare","Tofsmes")));

        categories.add(natur);
        categories.add(vetenskap);
        categories.add(kultur);
        categories.add(samhälle);
        categories.add(djur);
    }


    public ArrayList<Question> createRond() {
        Random rand = new Random();
        int upperbound = 5;
        int int_random = rand.nextInt(upperbound);

        while (alreadySelectedRonds.contains(int_random)) {
            int_random = rand.nextInt(upperbound);
        }

        return categories.get(int_random);
    }



        /*if (category.equalsIgnoreCase("natur")) {
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
    }*/


}
