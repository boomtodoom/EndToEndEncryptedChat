import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class BackEnd {
  private ArrayList<Socket> sockArray = new ArrayList<>();
  private ArrayList<Send> senderArray = new ArrayList<>();

  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println("Please enter the ip you wish to connect to: ");
    String ip = scan.nextLine();


  }
}
