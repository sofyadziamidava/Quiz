package Client;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private int totalScore;
    private int opponentScore;
    List<List<Integer>> scoreTablePlayer;
    List<Integer> scoreTableOpponent;



    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
        scoreTablePlayer = new ArrayList<>();
        scoreTableOpponent = new ArrayList<>();
    }

    public void addToScoreTablePlayer(List<Integer> list){
        scoreTablePlayer.add(list);
    }

    public List<List<Integer>> getScoreTablePlayer() {
        return scoreTablePlayer;
    }

    public void increaseOpponentScore(int points) {
        this.opponentScore += points;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void increasePoint(int points){
        totalScore += points;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return totalScore;
    }

    public void addToScoreTableOpponent(List<Integer> list){
        scoreTableOpponent = list;
    }

    public List<Integer> getScoreTableOpponent() {
        return scoreTableOpponent;
    }
}
