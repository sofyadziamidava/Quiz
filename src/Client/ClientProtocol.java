package Client;

import Client.GUI.Window;
import Server.Rond;

public class ClientProtocol {

    Window window;


    private final static int RECIVEROND = 0;
    private final static int SENDRESULT = 1;
    private final static int GETOPPONENTSRESULT = 2;
    private final static int GETFINALRESULT = 3;

    private int state = RECIVEROND;

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
        String firstQuestion = newRond.getQuestion1().getQuestion();
        String secondQuestion = newRond.getQuestion2().getQuestion();
        String[] answersToFirstQuestion = newRond.getQuestion1().getAnswers();
        String[] answersToSecondQuestion = newRond.getQuestion2().getAnswers();
        String correctAnswser1 = answersToFirstQuestion[0];
        String correctAnswser2 = answersToSecondQuestion[0];
        System.out.printf("first question: %s%ncorrect answer: %s%n",firstQuestion,correctAnswser1);
    }



}
