/** 
 * In this file, we use the java standard library to implement the md5 algorithm
 * and finish encrypting the message.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SMd5 {

    public static String encrypt(String message){
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(message.getBytes());
            result = toHexString(bytes);
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return result;
    }

    private static String toHexString(byte[] b)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString();
    }
}
