package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;


public class Player implements Serializable {

    String name;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Player(Socket socket) throws IOException {

        this.out = Network.getOutStream(socket);
        this.in = Network.getInStream(socket);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void send(Object obj) throws IOException {
        out.writeObject(obj);
    }

    public String receive() throws IOException, ClassNotFoundException {
        return (String)in.readObject();
    }


}

