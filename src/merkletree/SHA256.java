
package merkletree;

import java.security.MessageDigest ;
import java.io.*;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author sujit
 */
public class SHA256 {
    
    /**
     * @param file
     * @return 
     * @throws Exception
     */
    public static byte[] getHashFile(File file) throws Exception {
        byte[] buffer = new byte[1024];
        MessageDigest md;
        try (FileInputStream fis = new FileInputStream(file)) {
            md = MessageDigest.getInstance("SHA-256");
            int numRead;
            do {
                numRead = fis.read(buffer);
                if(numRead > 0) {
                    md.update(buffer, 0, numRead);
                    
                }
            }while(numRead != -1);
        }
        return md.digest();
    }
    
    public static byte[] getHashBytes(byte[] inByte) throws NoSuchAlgorithmException {
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(inByte);
        
        return md.digest();
    }
}
