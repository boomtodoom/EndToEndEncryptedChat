import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class BackEnd {
  private ArrayList<Socket> sockArray = new ArrayList<>();
  private ArrayList<Send> senderArray = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Please enter the ip you wish to connect to: ");
    String ip = scan.nextLine();
    System.out.println("Please enter the port you wish to connect to: ");
    int port = scan.nextInt();
    Socket socket = new Socket(ip,port);
    Send initMsg = new Send(socket);
    System.out.println("Please enter your desired username");
    String uName = scan.nextLine();
    initMsg.send("INIT::"+uName);
    //Receive list of sockets



  }
}
