package Server;

import shared.Rond;

import java.io.*;
import java.net.Socket;

public class Game extends Thread {
    Socket socket;
    Socket socket2;
    Player player1;
    Player player2;

    public Game(Socket socket, Socket socket2){
        this.socket = socket;
        this.socket2 = socket2;
        System.out.println("Client connected");
    }

    @Override
    public void run() {
        try(ObjectOutputStream dataToFirstPlayer = new ObjectOutputStream(socket.getOutputStream());
            ObjectOutputStream dataToSecondPlayer = new ObjectOutputStream(socket2.getOutputStream());
            ObjectInputStream dataFromServer = new ObjectInputStream(socket.getInputStream());)
        {
            System.out.println("check point");
            //outputStream.println("What old man do you never see in the summer?");
            Rond rond = new Rond();
            dataToFirstPlayer.writeObject(rond);
            dataToSecondPlayer.writeObject(rond);
            while (true){
               Object o = dataFromServer.readObject();
            }


        }  catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
