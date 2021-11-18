package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network {

    int port = 42424;
    String ip = "127.0.0.1";
    ObjectInputStream input;
    ObjectOutputStream output;

    public Network(){
        try {
            Socket socket = new Socket(ip, port);
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
