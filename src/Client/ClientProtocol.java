package Client;

import Client.GUI.GameWindow;
import Client.GUI.Window;
import shared.Question;
import shared.Rond;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Window window;
    Window gameWindow;
    GameWindow gamePanel;

    private String question;
    private String[] answers;
    private String correctAnswer;

    public ClientProtocol(Window window){
        this.window = window;
    }

    public void handleNewRond(Object o){
        Rond newRond = (Rond)o;
        List<Question> questionList = newRond.getQuestionList();

        for(Question q : questionList){
            playRound(q);
        }

        //send points to server
    }

    private void playRound(Question q) {
        unpackQuestion(q);
        List<String> alternatives = Arrays.asList(answers);
        Collections.shuffle(alternatives);
        createGameWindow(question, alternatives, correctAnswer);

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

    private void unpackQuestion(Question q) {
        this.question = q.getQuestion();
        this.answers = q.getAnswers();
        this.correctAnswer = answers[0];
    }

    private void createGameWindow(String question, List<String> alternatives, String correctAnswer) {
        window.dispose();
        this.gameWindow = new Window();
        this.gamePanel = gameWindow.getGameWindow();
        gameWindow.add(gamePanel);
        gameWindow.setVisible(true);

        this.gamePanel.displayQuestion(question);
        this.gamePanel.displayButtons(alternatives);
        this.gamePanel.setCorrectAnswer(correctAnswer);
    }




    public void sendPointsToServer(int points){

    }

    public void reciveRondsResult(){

    }



}
