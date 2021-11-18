package Server;

import java.io.Serializable;

public class Question implements Serializable {

    private String question;
    private String[] answers;

    public Question(String question, String[] answers){
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }
}
