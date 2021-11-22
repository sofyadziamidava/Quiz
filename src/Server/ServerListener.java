package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {


    public static void main(String[] args){
        int port = 42424;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Game game = new Game(new PlayerStream(serverSocket.accept()), new PlayerStream(serverSocket.accept()));
                game.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
