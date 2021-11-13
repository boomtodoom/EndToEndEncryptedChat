public class ChatMessage {
    private static int userHashCode;
    private static byte[] message;

    ChatMessage(int userHashCode, byte[] message){
        this.userHashCode = userHashCode;
        this.message = message;
    }
}
