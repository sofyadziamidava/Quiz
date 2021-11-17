package Client;

public class ClientProtocol {
    private final static int RECIVEROND = 0;
    private final static int SENDRESULT = 1;
    private final static int GETOPPONENTSRESULT = 2;
    private final static int GETFINALRESULT = 3;

    private int state = RECIVEROND;

    public void clientProtocol(Object input){

        if(state == RECIVEROND){
            Rond newRond = (Rond) input;
            state = SENDRESULT;
        } else if(state == SENDRESULT){

        } else if(state == GETOPPONENTSRESULT){

        } else if (state == GETFINALRESULT){

        }
    }



}
