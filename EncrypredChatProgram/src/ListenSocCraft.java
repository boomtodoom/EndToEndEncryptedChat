import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ListenSocCraft extends Thread{
  private ServerSocket servSoc;
  private ArrayList<Socket> socketArray;



  public ListenSocCraft(ServerSocket servSoc, ArrayList<Socket> arrList ){
    this.socketArray = arrList;
    this.servSoc = servSoc;
  }

  public void run(){
    while(true){
      try {
        Socket newSoc = servSoc.accept();

      } catch (IOException e) {
        e.printStackTrace();
      }


    }
  }
}
