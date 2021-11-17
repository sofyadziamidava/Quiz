import Server.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//TODO: decide what information player objects needs to hold
// -> eller är det här bara en mellanhand, som skickar data mellan spelet och användaren?

public class Player {

    ObjectInputStream in;
    ObjectOutputStream out;

    public Player(Socket socket) throws IOException {

        this.in = Network.getInStream(socket);
        this.out = Network.getOutStream(socket);

    }

    public void send(Object obj) throws IOException {
        out.writeObject(obj);
    }

    public String receive() throws IOException, ClassNotFoundException {
        String input = (String)in.readObject();
        return input;
    }


}

