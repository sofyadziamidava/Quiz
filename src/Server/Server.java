package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(){
        int port = 42424;
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            while(true){
                Socket playerSocket1 = serverSocket.accept();
                Socket playerSocket2 = serverSocket.accept();
                Game game = new Game(playerSocket1, playerSocket2);
                game.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server server = new Server();
    }
}

