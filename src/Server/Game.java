package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game extends Thread{

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
        try(BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
            PrintWriter outputStream2 = new PrintWriter(socket2.getOutputStream(), true))
        {
            //outputStream.println("What old man do you never see in the summer?");
            String paketet = "Paket";
            outputStream.println(paketet);
            outputStream2.println(paketet);
            while(true){
                String dataFromClient = inputStream.readLine();
                System.out.println("Data from Client: " + dataFromClient);
            }

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
