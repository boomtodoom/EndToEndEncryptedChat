import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Send {
  private String message;
  private Socket clientSocket;
  private DataOutputStream output;
  //public key here

  /**
   *Constructs the class taking a socket as an input and initialising an output stream
   * @param sock
   * Socket input that is used to initialise the output stream
   */
  public Send(Socket sock) throws IOException {
    clientSocket=sock;
    output = new DataOutputStream(clientSocket.getOutputStream());
  }

  /**
   * Sends a message along the data output stream.
   * @param msg
   * String inpput that is the message to be sent.
   * @throws IOException
   * Throws an error if the output fails
   */
  void send(String msg) throws IOException {
    message=msg;

    output.writeUTF(message);
  }
}
