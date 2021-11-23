package Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private final String name;
    private int totalScore;
    private int opponentScore;
    List<Integer> scoreTablePlayer;
    List<Integer> scoreTableOpponent;

    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
        scoreTablePlayer = new ArrayList<>();
        scoreTableOpponent = new ArrayList<>();
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

    public void addToScoreTablePlayer(List<Integer> list){
        this.scoreTablePlayer = list;
    }

    public void addToScoreTableOpponent(List<Integer> list){
        this.scoreTableOpponent = list;
    }

    public List<Integer> getScoreTableOpponent() {
        return scoreTableOpponent;
    }

    public List<Integer> getScoreTablePlayer() {
        System.out.println(Arrays.toString(scoreTablePlayer.toArray()));
        return scoreTablePlayer;
    }
}
