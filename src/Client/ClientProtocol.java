package Client;

import Client.GUI.*;
import shared.Question;
import shared.Rond;

import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Window window;
    Window gameWindow;
    WaitingWindow waitingWindow;
    GameWindow gamePanel;
    ResultWindowNew resultsWindow;
    Player player;

    private String question;
    private List<String> answers;
    private String correctAnswer;
    private final int FIRST_ELEMENT = 0;
    public static boolean waitingForNextRound = true;

    private static int pointsPerRond;

    public ClientProtocol(Window window, Player player){
        this.window = window;
        this.player = player;
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
        //send points to server
    }

    public void resultsWindow(int opponentResult) {
        window.dispose();
        this.window = new Window(player);
        ResultWindowNew resultsWindow = new ResultWindowNew(player);
        window.add(resultsWindow);
        this.window.setVisible(true);

    }

    public void displayWaitingWindow(){
        this.window = new Window(player);
        this.waitingWindow = new WaitingWindow();
        window.add(waitingWindow);
        window.setSize(400,300);
        window.setLocationRelativeTo(null);
        this.window.setVisible(true);
    }


    private void playRound(Question currentQuestion) {
        unpackCurrentQuestion(currentQuestion);
        createGameWindowFromCurrentQuestion(question, answers, correctAnswer, player);
        playCurrentQuestion();
    }

    private void unpackCurrentQuestion(Question question) {
        this.question = question.getQuestion();
        this.answers = question.getAnswers();
        this.correctAnswer = answers.get(FIRST_ELEMENT);
        Collections.shuffle(answers);
    }

    private void createGameWindowFromCurrentQuestion(String question, List<String> alternatives, String correctAnswer,
                                                     Player player) {
        window.dispose();
        this.gameWindow = new Window(player);
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
