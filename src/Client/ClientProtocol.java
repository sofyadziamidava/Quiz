package Client;

import Client.GUI.GameWindow;
import Client.GUI.ResultsWindow;
import Client.GUI.WaitingWindow;
import Client.GUI.Window;
import shared.Question;
import shared.Rond;

import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Window window;
    Window gameWindow;
    WaitingWindow waitingWindow;
    GameWindow gamePanel;
    ResultsWindow resultsWindow;
    Player player;

    private String question;
    private List<String> answers;
    private String correctAnswer;
    private final int FIRST_ELEMENT = 0;
    public static boolean waitingForNextRound = true;
    public static int clientScoreTotal;
    public static int opponentScoreTotal;
    public static String clientName;


    private static int pointsPerRond;

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
        waitingForNextRound = true;
        pointsPerRond = 0;
        Rond newRond = (Rond)o;
        List<Question> questionList = newRond.getQuestionList();

        for(Question question : questionList){
            playRound(question);
        }
        displayWaitingWindow();
        player.increasePoint(pointsPerRond);
    }

    public void resultsWindow(int opponentResult) {
        window.dispose();
        this.window = new Window();
        this.resultsWindow = new ResultsWindow(pointsPerRond, opponentResult, player);
        window.add(resultsWindow);
        this.window.setVisible(true);

    }

    public void displayWaitingWindow(){
        this.window = new Window();
        this.waitingWindow = new WaitingWindow();
        window.add(waitingWindow);
        window.setSize(400,300);
        window.setLocationRelativeTo(null);
        this.window.setVisible(true);
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
        window.dispose();
        this.gameWindow = new Window();
        this.gamePanel = gameWindow.getGameWindow();
        this.gameWindow.add(gamePanel);
        this.gameWindow.setVisible(true);

        this.gamePanel.displayQuestion(question);
        this.gamePanel.displayButtons(alternatives);
        this.gamePanel.setCorrectAnswer(correctAnswer);
    }

    private void playCurrentQuestion() {
        boolean checkTimer = gamePanel.getTimerLabel().getText().equals("0");
        while(!checkTimer){
            ClientNetwork.sleep(1000);
            if(gamePanel.isButtonPressed()){
                //System.out.println("Inside if-scope");
                break;
            }
            checkTimer = gamePanel.getTimerLabel().getText().equals("0");
        }
        //System.out.println("out of while loop");
        ClientNetwork.sleep(1000);
        this.gameWindow.dispose();
    }

    public static int getPointsPerRond() {
        return pointsPerRond;
    }

    public static void increasePoint() {
       pointsPerRond++;
    }
}
