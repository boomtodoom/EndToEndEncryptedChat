import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ListenSocCraft extends Thread{
  private ServerSocket servSoc;
  private ArrayList<Socket> socketArray;
  private ArrayList<Send> sendArray;

  public ArrayList<Socket> getSocketArray(){
    return socketArray;
  }
  public ArrayList<Send> getSendArray(){
    return sendArray;
  }

  public ListenSocCraft(ServerSocket servSoc, ArrayList<Socket> arrList, ArrayList<Send> sendArray ){
    this.socketArray = arrList;
    this.servSoc = servSoc;

  }

  public void run(){
    while(true){
      try {
        Socket newSoc = servSoc.accept();
        Send newSender = new Send(newSoc);
        Receive reciver = new Receive(newSoc);


      } catch (IOException e) {
        e.printStackTrace();
      }


    }
  }
}
