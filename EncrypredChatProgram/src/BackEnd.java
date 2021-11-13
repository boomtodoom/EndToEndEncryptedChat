import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
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
    InetAddress iAddress = InetAddress.getByName(ip);
    System.out.println("Please enter the port you wish to connect to: ");
    int port = scan.nextInt();
    Socket socket = new Socket(iAddress, port);
    System.out.println("Please enter a port you want open");
    int openPort = scan.nextInt();

    ServerSocket sSoc = new ServerSocket(openPort);
    Socket newSoc = sSoc.accept();

    Send initMsg = new Send(socket);
    System.out.println("Please enter your desired username");
    String uName = scan.nextLine();
    initMsg.send("INIT::"+uName); // remove dogshit code
    //Receive list of sockets



  }
}
