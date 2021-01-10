import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder output = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices ) {
            output.append(message.charAt(i));
        }
        return output.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String toDecrypt = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(toDecrypt);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] keys = tryKeyLength(message, 4, 'e');
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(message);
        System.out.println("Decrypted Message:");
        System.out.println(decrypted);
     }
    
}
