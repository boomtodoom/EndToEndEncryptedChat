import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class BackEnd {
  private ArrayList<Socket> sockArray = new ArrayList<>();
  private ArrayList<Send> senderArray = new ArrayList<>();
  private ArrayList<Receive> receiverArray = new ArrayList<>();
  private Queue<String> messageQueue = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    BackEnd back = new BackEnd();
    back.initialise();
  }

  public void initialise() throws IOException {
    /**
     * Gets the port and ip of the client to connect to and then creates a socket
     */
    Scanner scan = new Scanner(System.in);
    System.out.println("Do you want to join a server that already exists(y/n)? ");
    String choice = scan.nextLine().toLowerCase(Locale.ROOT);
    if(choice.equals("y")) {
      System.out.println("Please enter the ip you wish to connect to: ");
      String ip = scan.nextLine();
      InetAddress iAddress = InetAddress.getByName(ip);
      System.out.println("Please enter the port you wish to connect to: ");
      int port = scan.nextInt();
      Socket socket = new Socket(iAddress, port);
      senderArray.add(new Send(socket));
      Receive rec = new Receive(socket);
      rec.start();
      receiverArray.add(rec);

      sockArray.add(socket);
    } else {
      System.out.println("Starting chatroom...");
    }

    /**
     * Starts a thread that creates new sockets every time a user connects to this client
     */
    System.out.println("Please enter a port you want open");
    int openPort = scan.nextInt();
    ServerSocket sSoc = new ServerSocket(openPort);
    ListenSocCraft lSoc = new ListenSocCraft(sSoc,sockArray,senderArray,receiverArray); //add server socket
    lSoc.start();
    //Receive rec = new Receive(sSoc);

    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        sockArray = lSoc.getSocketArray();
        senderArray =  lSoc.getSendArray();
        receiverArray =  lSoc.getRecArray();
        //messageQueue = rec.getQueue();

        for(Receive receiver:receiverArray){
          for(String msg:receiver.getQueue()){
            System.out.println(msg);
            receiver.getQueue().remove(msg);

          }
        }
      }
    },1000,1000);

    /**
     * Loops infinitely checking for the users message and then sending them to all connected clients
     */
    while(true){
      System.out.println("Please enter the message: ");
      String message = scan.nextLine();
      System.out.println(senderArray.size());
      System.out.println("Sending: "+message);
      for(int i=0; i<senderArray.size();i++){
        senderArray.get(i).send(message);
      }
    }
    /**
     *
     */
  }
}
