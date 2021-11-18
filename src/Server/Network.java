package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Network implements Serializable {

    public static ObjectOutputStream getOutStream(Socket socket) throws IOException {
        return new ObjectOutputStream(socket.getOutputStream());
    }

    public static ObjectInputStream getInStream(Socket socket) throws IOException {
        return new ObjectInputStream(socket.getInputStream());
    }
}
