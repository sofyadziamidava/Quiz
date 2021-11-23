package Client;

public class Player {

    private final String name;
    private int totalScore;
    private int opponentScore;

    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
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
}
