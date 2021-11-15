package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public static void main(String[] args){
        int port = 42424;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while(true){
                Socket clientSocket = serverSocket.accept();
                Game game = new Game(clientSocket);
                game.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
