package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//TODO: decide what information player objects needs to hold
// -> eller är det här bara en mellanhand, som skickar data mellan spelet och användaren?

public class Player {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Player(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void send(Object obj) throws IOException {
        out.writeObject(obj);
    }

    public String receive() throws IOException, ClassNotFoundException {
        String input = (String)in.readObject();
        return input;
    }


}

