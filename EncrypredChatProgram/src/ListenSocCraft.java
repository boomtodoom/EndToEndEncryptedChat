import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import java.util.ArrayList;

/**
 * Thread class runs and constantly listens on chosen port for incoming clients
 * When they join the socket is created, using this socket a new sender and receiver.
 */

public class ListenSocCraft extends Thread{
  private ServerSocket servSoc;
  private ArrayList<Socket> socketArray;
  private ArrayList<Send> sendArray;
  private ArrayList<Receive> recArray;


  /**
   *Socket array getter
   * @return
   */
  public ArrayList<Socket> getSocketArray(){
    return socketArray;
  }

  /**
   * Sender array getter
   * @return
   */
  public ArrayList<Send> getSendArray(){
    return sendArray;
  }

  /**
   * Receiver array getter
   * @return
   */
  public ArrayList<Receive> getRecArray(){
    return recArray;
  }

  /**
   * Constructor that takes in a server socket and two array lists , one holding sockets and another holding Senders
   * @param servSoc
   * @param arrList
   * @param sendArray
   */
  public ListenSocCraft(ServerSocket servSoc, ArrayList<Socket> arrList, ArrayList<Send> sendArray, ArrayList<Receive> receiverArr ){ // perhaps need to guve it a recuver array as well??
    this.socketArray = arrList;
    this.servSoc = servSoc;
    this.sendArray = sendArray;
    this.recArray=receiverArr;
  }

  /**
   * Waits on serverSoc.accept() untill a new connection.
   * Creats a new sender and reciver with new socket
   * adds sender, reciver and socket to arrays
   */
  public void run(){
    while(true){
      try {
        Socket newSoc = servSoc.accept();
        socketArray.add(newSoc);
        Send newSender = new Send(newSoc);
        sendArray.add(newSender);
        Receive receiver = new Receive(newSoc);
        recArray.add(receiver);
        receiver.start();
        DataOutputStream out = new DataOutputStream(newSoc.getOutputStream());
        for(int i=0; i<socketArray.size();i++){
          out.writeUTF(socketArray.get(i).getInetAddress().toString()+" "+socketArray.get(i).getPort());
        }
        out.writeUTF("END:DATA:SEND");

      } catch (IOException e) {
        e.printStackTrace();
      }


    }
  }
}
