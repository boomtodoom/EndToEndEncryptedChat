import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.xml.crypto.Data;

public class Receive {
  private Socket clientSocket;
  private DataInputStream input;
  private String message = null;

  public Receive(Socket sock) throws IOException {
    clientSocket = sock;
    input = new DataInputStream(sock.getInputStream());
  }

  String getMessage() throws IOException {
    message=input.readUTF();
    return message;
  }
}
