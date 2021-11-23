package shared;

import java.io.Serializable;
import java.util.ArrayList;


public class Player implements Serializable {

    private String name;
    private ArrayList<ArrayList<Integer>> rounds = new ArrayList<ArrayList<Integer>>();

    public Player(){
        System.out.println("Player gay");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addRounds(){
        rounds.add(new ArrayList<>());
    }


    public void addPoints(int round, int points){
        rounds.get(round-1).add(points);
    }

    public ArrayList<Integer> getCurrentRoundList(){
        return rounds.get(rounds.size()-1);
    }

    public ArrayList<Integer> getRoundList(int round){
        return rounds.get(round);
    }

    public int getTotalForAllRounds(){
        int total = 0;
        for(int i = 0; i<rounds.size(); ++i){
            for(int j = 0; j<rounds.get(i).size(); ++j){
                total += rounds.get(i).get(j);
            }
        }
        return total;
    }

    public int getTotalForRound(int round){
        ArrayList<Integer> pickedRound = rounds.get(round);
        int total = 0;
        for (int i = 0; i<pickedRound.size(); ++i){
            total+=pickedRound.get(i);
        }
        return total;
    }

    public int getPointForSpecificQuestion(int round, int question){
        int point;
        point = rounds.get(round-1).get(question);
        return point;
    }


}

