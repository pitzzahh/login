import lib.utilities.SecurityUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        try{
//            SecretKey key = SecurityUtil.AES.generateKey();
//            SecurityUtil.AES.storeToKeyStore(key, "admin", "newPin.keystore"); // storing credentials to a file
            SecretKey key = SecurityUtil.AES.loadFromKeyStore("admin", "src\\files\\accounts\\user\\admin's Folder\\credentials\\password.keystore"); // dotLoading credentials from a file
            Cipher cipher;
            cipher = Cipher.getInstance("AES");
            byte[] encryptedData = SecurityUtil.AES.encrypt("admin", key, cipher);
            assert encryptedData != null;
            String encryptedString = new String(encryptedData);
            String decryptedString = SecurityUtil.AES.decrypt(encryptedData, key, cipher);
            System.out.println("ENCRYPTED: " + encryptedString);
            System.out.println("DECRYPTED: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
