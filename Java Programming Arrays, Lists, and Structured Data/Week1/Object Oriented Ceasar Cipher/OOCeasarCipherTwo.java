
import edu.duke.*;


public class OOCeasarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int dkey1;
    private int dkey2;
    
    public OOCeasarCipherTwo(int key1, int key2) {
        /*
         *  This constructor assigns a string alphabet, two string shifted alphabet and 
         *  two integers dkeys used for decrypting
         */
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        dkey1 = 26 - key1;
        dkey2 = 26 - key2;
    }
    
    
    public String encrypt(String input) {
        // This method encrypt a given String input, using two encrypting keys
        StringBuilder outputPhrase = new StringBuilder(input);
        for ( int i = 0; i < input.length(); i++ ) {
            char currChar = outputPhrase.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            if ( index != -1 && Character.isUpperCase(currChar)) {
                if ( i % 2 == 0 ) {
                    char newChar = shiftedAlphabet1.charAt(index);
                    outputPhrase.setCharAt(i, newChar);
                }
                if (i % 2 == 1 ) {
                    char newChar = shiftedAlphabet2.charAt(index);
                    outputPhrase.setCharAt(i, newChar);
                }
            }
            if ( index != -1 && Character.isLowerCase(currChar)) {
                if ( i % 2 == 0 ) {
                    char newChar = shiftedAlphabet1.charAt(index);
                    outputPhrase.setCharAt(i, Character.toLowerCase(newChar));
                }
                if ( i % 2 == 1 ) {
                    char newChar = shiftedAlphabet2.charAt(index);
                    outputPhrase.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return outputPhrase.toString();
    }
    
    
    public String decrypt(String input) {
        // Using two decrypting keys, this method call encrypt method to decrypt a given String input
        OOCeasarCipherTwo cct = new OOCeasarCipherTwo(dkey1, dkey2);
        String outputPhrase = cct.encrypt(input);
        return outputPhrase;
    }
    
}
