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
    private Player player;

    private String question;
    private List<String> answers;
    private String correctAnswer;
    private final int FIRST_ELEMENT = 0;
    public static boolean waitingForNextRound = true;
    public static String clientName;
    public static String opponentName;

    List<Integer> playerScoreTable;
    List<Integer> opponentScoreTable;

    private int pointsPerRondPlayer;

    public ClientProtocol(Window window, Player player){
        this.window = window;
        this.player = player;
        playerScoreTable = new ArrayList<>();
        opponentScoreTable = new ArrayList<>();
    }

    public String waitForContinue() {
        while (waitingForNextRound) {
            ClientNetwork.sleep(500);
        }
        return "a";                             ///////  a returneras, anropas från clientNetwork
    }

    public void handleNewRond(Object o){
        waitingForNextRound = true;
        pointsPerRondPlayer = 0;
        Rond newRond = (Rond)o;
        List<Question> questionList = newRond.getQuestionList();

        for(Question question : questionList){
            playRound(question);
        }
        displayWaitingWindow();
        player.increasePoint(pointsPerRondPlayer);
        addScoreToPlayerTable(pointsPerRondPlayer);

    }

    public void resultsWindow(int opponentResult) {
        player.increaseOpponentScore(opponentResult);
        window.getContentPane().removeAll();
        this.resultsWindow = new ResultsWindow(pointsPerRondPlayer, opponentResult, player, false);
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
        this.resultsWindow = new ResultsWindow(pointsPerRondPlayer, player, true);
        window.add(resultsWindow);
        window.setVisible(true);
    }

    public int getPointsPerRondPlayer() {
        return pointsPerRondPlayer;
    }

    public void increasePoint() {
       pointsPerRondPlayer++;
    }

    private void addScoreToPlayerTable(int pointsPerRond) {
        this.playerScoreTable.add(pointsPerRond);
    }

    public void addToScoreTableOpponent(int opponentScore) {
        this.opponentScoreTable.add(opponentScore);
    }

    public void sendListToOpponentTable() {
        player.addToScoreTableOpponent(opponentScoreTable);
    }

    public void sendListToPlayerTable() {
        player.addToScoreTablePlayer(playerScoreTable);
    }
}
