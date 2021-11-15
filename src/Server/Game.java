package Server;

import java.net.Socket;

public class Game extends Thread{

    Socket socket;
    Player player1;
    Player player2;

    public Game(Socket socket){
        this.socket = socket;
        System.out.println("Connected");
    }

    public Game(Socket socket, Player player1, Player player2){
        this.socket = socket;
        this.player1 = player1;
        this.player2 = player2;
    }
}
