package Custom;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;


public class ConvertMD5 {

    public static String convertPassToMD5(String password) {
        MessageDigest convert = null;

        try {

            convert = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(ConvertMD5.class.getName()).log(Level.SEVERE, null, e);
        }
        
        convert.update(password.getBytes());
        byte [] passwordByte = convert.digest();
        return DatatypeConverter.printHexBinary(passwordByte);
    }

}
