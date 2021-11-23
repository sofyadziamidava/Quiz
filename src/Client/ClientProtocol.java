package Client;

import Client.GUI.*;
import Client.GUI.GameWindow;
import shared.Question;
import shared.Rond;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    private Window window;
    private WaitingWindow waitingWindow;
    private GameWindow gamePanel;
    private ResultsWindow resultsWindow;
    private ResultsWindowEnd resultsWindowEnd;
    private Player player;

    private String question;
    private List<String> answers;
    private String correctAnswer;
    private final int FIRST_ELEMENT = 0;
    public static boolean waitingForNextRound = true;
    public static int opponentScoreTotal;
    public static String clientName;

    List<Integer> playerScoreTable;
    List<Integer> opponentScoreTable = new ArrayList<>();

    private int pointsPerRond;

    public ClientProtocol(Window window, Player player){
        this.window = window;
        this.player = player;
    }

    public void addOpponentScore(int newScore) {
        opponentScoreTotal += newScore;
    }

    public String waitForContinue() {
        while (waitingForNextRound) {
            ClientNetwork.sleep(500);
        }
        return "a";
    }

    public void handleNewRond(Object o){
        playerScoreTable = new ArrayList<>();
        waitingForNextRound = true;
        pointsPerRond = 0;
        Rond newRond = (Rond)o;
        List<Question> questionList = newRond.getQuestionList();

        for(Question question : questionList){
            playRound(question);
        }
        displayWaitingWindow();
        player.increasePoint(pointsPerRond);
        sendListToPlayerTable(playerScoreTable);
        System.out.println("Size of list (2): " + playerScoreTable.size());
    }

    private void sendListToPlayerTable(List<Integer> playerScoreTable) {
        player.addToScoreTablePlayer(playerScoreTable);
    }

    public void resultsWindow(int opponentResult) {
        player.increaseOpponentScore(opponentResult);
        window.getContentPane().removeAll();
        this.resultsWindow = new ResultsWindow(pointsPerRond, opponentResult, player);
        window.add(resultsWindow);

        window.setVisible(true);
    }

    public void displayWaitingWindow(){
        window.getContentPane().removeAll();
        this.waitingWindow = new WaitingWindow();
        window.add(waitingWindow);

        window.setVisible(true);
    }

    private void playRound(Question currentQuestion) {
        unpackCurrentQuestion(currentQuestion);
        createGameWindowFromCurrentQuestion(question, answers, correctAnswer);
        playCurrentQuestion();
        addScoreToPlayerTable(pointsPerRond);
    }

    private void addScoreToPlayerTable(int pointsPerRond) {
        playerScoreTable.add(pointsPerRond);
    }

    private void unpackCurrentQuestion(Question question) {
        this.question = question.getQuestion();
        this.answers = question.getAnswers();
        this.correctAnswer = answers.get(FIRST_ELEMENT);
        Collections.shuffle(answers);
    }

    private void createGameWindowFromCurrentQuestion(String question, List<String> alternatives, String correctAnswer) {
        window.getContentPane().removeAll();

        this.gamePanel = new GameWindow(this);
        this.gamePanel.displayQuestion(question);
        this.gamePanel.displayButtons(alternatives);
        this.gamePanel.setCorrectAnswer(correctAnswer);
        this.window.add(gamePanel);

        window.setVisible(true);
    }

    private void playCurrentQuestion() {
        boolean checkTimer = gamePanel.getTimerLabel().getText().equals("0");
        while(!checkTimer){
            ClientNetwork.sleep(1000);
            if(gamePanel.isButtonPressed()){
                break;
            }
            checkTimer = gamePanel.getTimerLabel().getText().equals("0");
        }
        ClientNetwork.sleep(1000);
    }

    public void closeGame() {
        createEndResultWindow();
    }

    private void createEndResultWindow() {
        window.getContentPane().removeAll();
        this.resultsWindowEnd = new ResultsWindowEnd(player);
        window.add(resultsWindowEnd);
        window.setVisible(true);
    }

    public int getPointsPerRond() {
        return pointsPerRond;
    }

    public void increasePoint() {
       pointsPerRond++;
    }
}
