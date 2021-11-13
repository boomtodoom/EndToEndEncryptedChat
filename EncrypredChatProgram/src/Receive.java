import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
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
  private Queue<String> messageQueue = new LinkedList<>();

  /**
  * Constructior taks a socket
   * */
  public Receive(Socket sock) throws IOException {
    clientSocket = sock;
    input = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
  }

  /**
  * Getter grabs socket queue
   */
  public Queue<String> getQueue(){
    return messageQueue;
  }

  private void clearQueue(){
    messageQueue.clear();
  }
  /**
  * Method runs repetedly due to loop. It listens for a message and will save it in the stack
   */
  public void run() {
    int msgCount=0;
    while (true) {

      try {

        currentMessage = input.readUTF();
        if(!currentMessage.isEmpty()){
          msgCount=1;
          if(msgCount==1){
            while(currentMessage!="END:DATA:SEND"){
              currentMessage=input.readUTF();
              String[] splitMsg = currentMessage.split(" ");
              Socket sock = new Socket(InetAddress.getByName(splitMsg[0]),Integer.valueOf(splitMsg[1]));
            }
          }
          messageQueue.add(currentMessage);
          currentMessage=null;
          System.out.println("Message recd");
        }

      } catch (IOException e) {
        e.printStackTrace();
      }


    }

  }


}


