package Client;

import Client.GUI.StartWindow;
import Client.GUI.Window;

public class Client {

 Window window;
 StartWindow startWindow;

 public Client(){
  window = new Window();
  startWindow = new StartWindow();
  window.add(startWindow);
  window.setVisible(true);
  ClientNetwork clientNetwork = new ClientNetwork(window);
 }

 public static void main(String[] args) {
  new Client();
 }

}
