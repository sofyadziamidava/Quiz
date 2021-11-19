package Client;

import Client.GUI.GameWindow;
import Client.GUI.Window;
import Server.Game;
import shared.Rond;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Window window;
    GameWindow gamePanel;

    private String question1;
    private String Question2;
    private String[] answers1;
    private String[] answers2;

    private final static int RECIVEROND = 0;
    private final static int SENDRESULT = 1;
    private final static int GETOPPONENTSRESULT = 2;
    private final static int GETFINALRESULT = 3;

    private int state = RECIVEROND;

    public ClientProtocol(Window window){
        this.window = window;
    }

    public Object handleInput(Object input){
        Object output = 2;
        if(state == RECIVEROND){
            output = 1;
            state = SENDRESULT;
        } else if(state == SENDRESULT){

        } else if(state == GETOPPONENTSRESULT){

        } else if (state == GETFINALRESULT){

        }
        return output;
    }

    public void handleNewRond(Object o){
        Rond newRond = (Rond)o;
        question1 = newRond.getQuestion1().getQuestion();
        Question2 = newRond.getQuestion2().getQuestion();
        answers1 = newRond.getQuestion1().getAnswers();
        answers2 = newRond.getQuestion2().getAnswers();
        displayRond();
    }

    public void displayRond(){
        String correctAnswer1 = answers1[0];
        String correctAnswer2 = answers2[0];
        List<String> alternatives1 = Arrays.asList(answers1);
        List<String> alternatives2 = Arrays.asList(answers2);
        Collections.shuffle(alternatives2);

        createGameWindow(question1, alternatives1, correctAnswer1);
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

    }


    private void createGameWindow(String question, List<String> alternatives1, String correctAnswer1) {

        window.dispose();

        Window gameWindow = new Window();
        gamePanel = gameWindow.getGameWindow();
        gameWindow.add(gamePanel);
        gameWindow.setVisible(true);

        gamePanel.displayQuestion(question);
        gamePanel.displayButtons(alternatives1);
        gamePanel.setCorrectAnswer(correctAnswer1);

        Collections.shuffle(alternatives1);
    }

    public void sendPointsToServer(int points){

    }

    public void reciveRondsResult(){

    }



}
