package Client;

import Client.GUI.Container;
import Client.GUI.StartWindow;

public class Client {

    public Client() {
        //ClientNetwork clientNetwork = new ClientNetwork();
    }

    public static void main(String[] args) {

        StartWindow startWindow = new Container().getStartWindow();
        Client client = new Client();

    }
}
