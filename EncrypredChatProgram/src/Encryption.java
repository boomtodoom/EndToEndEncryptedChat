import java.security.*;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {

    protected static PrivateKey personalPrKey;
    protected static PublicKey personalPbKey;
    protected static List<PublicKey> listOfPublicKeys = new ArrayList<PublicKey>();

    //generates public and private key
    void generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(4096); //keysize of 4096 more secure
        KeyPair pair = generator.genKeyPair();
        personalPbKey = pair.getPublic();
        personalPrKey = pair.getPrivate();
    }

    PublicKey getPersonalPublicKey() {
        return personalPbKey;
    }

    PrivateKey getPersonalPrivateKey() {
        return personalPrKey;
    }

    void storePublicKey(PublicKey pbkey) {
        listOfPublicKeys.add(pbkey);
    }

    void storePublicKeys(List<PublicKey> pbkeys) {
        for (PublicKey pbk : pbkeys) {
            listOfPublicKeys.add(pbk);
        }
    }
    // WARNING: How does the userID come into play here?
    // How does user know what keys to take out once a user leaves?

    boolean removePublicKey(PublicKey pbkey) {
        if (!listOfPublicKeys.isEmpty()) {
            //continue to remove
            listOfPublicKeys.remove(pbkey);
            return true;
        }
        return false;
    }

    //@param encodedMessage:
    //              normal String now turned byte Array is a parameter
    byte[] encryptMessage(byte[] encodedMessage, PublicKey pbkey) throws
            NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if ((personalPbKey == null) || (personalPrKey == null)) {
            generateKeyPair();
        }

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pbkey);
        return cipher.doFinal(encodedMessage);
    }

    byte[] decryptMessage(byte[] encodedMessage, PublicKey prkey) throws
            NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prkey);
        return cipher.doFinal(encodedMessage);
    }
}
