package ClientSide;

import Client.ClientNetwork;
import ClientSide.GUI.ContainerNew;
import ClientSide.GUI.StartWindowNew;

public class ClientSide {

    ContainerNew containerNew;
    StartWindowNew sw;
    boolean ready;

    public ClientSide() {
        containerNew = new ContainerNew();
        sw = new StartWindowNew();
        containerNew.add(sw);
        containerNew.setVisible(true);
        //ClientNetwork clientNetwork = new ClientNetwork(containerNew);


        System.out.println("Before while");
        run();

    }

    private void run() {
        System.out.println("Boolean 1: " + sw.getReady());
        while(true){
            System.out.println("Ready: " + ready);
            if(sw.getReady()){
                System.out.println("working");
                break;
            }
        }
        System.out.println("Boolean 2: " + sw.getReady());
        System.out.println("Breakingpoint");
    }


    public static void main(String[] args) {
        ClientSide client = new ClientSide();
    }
}
