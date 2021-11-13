public class ChatMessage {
    private static int userHashCode;
    private static byte[] message;
    private static boolean messageIsEncrypted;

    ChatMessage(int userHashCode, byte[] message){
        this.userHashCode = userHashCode;
        this.message = message;
    }

    public static void setMessageIsEncrypted(boolean messageIsEncrypted) {
        ChatMessage.messageIsEncrypted = messageIsEncrypted;
    }

    public static boolean getMessageIsEncrypted() {
        return messageIsEncrypted;
    }
}
