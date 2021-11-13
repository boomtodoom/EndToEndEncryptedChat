import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Stack;
import javax.xml.crypto.Data;


/**
 * class is a thread that is run for each connection that is made.
 * It listens for incoming messages and saves them in a socket
 */

public class Receive extends Thread {
  private Socket clientSocket;
  private DataInputStream input;
  private String currentMessage = null;
  private Stack<String> messageStack;

  /**
  * Constructior taks a socket
   * */
  public Receive(Socket sock) throws IOException {
    clientSocket = sock;
    input = new DataInputStream(sock.getInputStream());
  }

  /**
  * Getter grabs socket stack
   */
  public Stack<String> getStack(){
    return messageStack;
  }


  /**
  * Method runs repetedly due to loop. It listens for a message and will save it in the stack
   */
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


