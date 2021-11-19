package Client;

import Client.GUI.GameWindow;
import Client.GUI.Window;
import shared.Question;
import shared.Rond;

import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Window window;
    Window gameWindow;
    GameWindow gamePanel;

    private String question;
    private List<String> answers;
    private String correctAnswer;

    public ClientProtocol(Window window){
        this.window = window;
    }

    public void handleNewRond(Object o){
        Rond newRond = (Rond)o;
        List<Question> questionList = newRond.getQuestionList();

        for(Question currentQuestion : questionList){
            playRound(currentQuestion);
        }

        //send points to server
    }

    private void playRound(Question currentQuestion) {
        unpackCurrentQuestion(currentQuestion);
        createGameWindowFromCurrentQuestion(question, answers, correctAnswer);
        playCurrentQuestion();
    }

    private void unpackCurrentQuestion(Question q) {
        this.question = q.getQuestion();
        this.answers = q.getAnswers();
        this.correctAnswer = answers.get(0);
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
            ClientNetwork.sleep();
            if(gamePanel.isButtonPressed()){
                System.out.println("Inside if-scope");
                break;
            }
            checkTimer = gamePanel.getTimerLabel().getText().equals("0");
        }
        System.out.println("out of while loop");
        this.gameWindow.dispose();
    }
}
