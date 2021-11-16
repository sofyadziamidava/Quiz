package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {

    //TODO: make sure player references can be filled with new player objects
    // -> replace with waiting states?
    public static void main(String[] args){
        int port = 42424;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Player player1 = new Player(serverSocket.accept());
                Player player2 = new Player(serverSocket.accept());
                Game game = new Game(player1, player2);
                game.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
