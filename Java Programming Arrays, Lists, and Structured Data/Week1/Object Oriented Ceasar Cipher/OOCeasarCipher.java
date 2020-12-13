
import edu.duke.*;


public class OOCeasarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int dkey;
    
    public OOCeasarCipher(int key) {
        // Given a key argument, the constructor assigns alphabet and shifted alphabet to OOCeasarCipher objects
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        dkey = 26 - key;
    }
    
    
    public String encrypt(String input) {
        // This method is used to encrypt messages using a key and an alphabet with shifted letters
        StringBuilder outputPhrase = new StringBuilder(input);
        for ( int i = 0; i < input.length(); i++ ) {
            char currChar = outputPhrase.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            if ( index != -1 && Character.isUpperCase(currChar)) {
                char newChar = shiftedAlphabet.charAt(index);
                outputPhrase.setCharAt(i, newChar);
            }
            if ( index != -1 && Character.isLowerCase(currChar)) {
                char newChar = shiftedAlphabet.charAt(index);
                outputPhrase.setCharAt(i, Character.toLowerCase(newChar));
            }
        }
        return outputPhrase.toString();
    }
    
    
    public String decrypt(String input) {
        // This method is used to encrypt messages using a key
        OOCeasarCipher cc = new OOCeasarCipher(dkey);
        String outputPhrase = cc.encrypt(input);
        return outputPhrase;
    }
}
