import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Stack;
import javax.xml.crypto.Data;

public class Receive extends Thread {
  private Socket clientSocket;
  private DataInputStream input;
  private String currentMessage = null;
  private Stack<String> messageStack;

  public Receive(Socket sock) throws IOException {
    clientSocket = sock;
    input = new DataInputStream(sock.getInputStream());
  }



  public void run() {
    while (true) {

      try {

        currentMessage = input.readUTF();

        if(currentMessage == null){

        }else{
          messageStack.push(currentMessage);
        }



      } catch (IOException e) {
        e.printStackTrace();
      }


    }

  }


}


