
import edu.duke.*;

public class CeasarCipher {
    public String encrypt(String input, int key) {
        // This method takes a String input and given a key, it shifts the input letters. The output is an encrypted String
        StringBuilder outputPhrase = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < outputPhrase.length(); i++) {
            char currChar = outputPhrase.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            if ( index != -1 && Character.isUpperCase(currChar)) {
                char newChar = shifted.charAt(index);
                outputPhrase.setCharAt(i, newChar);
            }
            if ( index != -1 && Character.isLowerCase(currChar)) {
                char newChar = shifted.charAt(index);
                outputPhrase.setCharAt(i, Character.toLowerCase(newChar));
            }
        }
        return outputPhrase.toString();
    }
    
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        /* 
        * This method takes a String and two keys as parameters. Using encrypt method,it converts
        * letters with even index using key1 and letters with odd index using key2
        */
        StringBuilder phrase = new StringBuilder(input);
        String outputPhrase = "";
        for ( int i = 0; i < phrase.length(); i++ ) {
            char currChar = phrase.charAt(i);
            if ( i % 2 == 0) {
                String output = encrypt(Character.toString(currChar), key1);
                outputPhrase += output;
            }
            else {
                String output = encrypt(Character.toString(currChar), key2);
                outputPhrase += output;
            }
        }
        return outputPhrase;
    }
    
    
    public void tests() {
        // We test encrypt method with a series of phrases and keys
        String[] phrase = {"Java is awesome", "Python is great too", "Hello world!", "THis Phrase is Used for TesTIng puRposes!!!"};
        int[] key = {11, 17, 9, 8};
        for ( int i = 0; i < phrase.length; i++ ) {
            String encrypted = encrypt(phrase[i], key[i]);
            System.out.println("Encrypted phrase: " + encrypted);
            System.out.println("Original phrase: " + encrypt(encrypted, 26 - key[i]));
            System.out.println();
        }
    }
    
    
    public void testCeasar() {
        // We test encrypt method with an external txt file
        int key = 11;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    
    public void testEncryptTwoKeys() {
        // We test encryptTwoKeys method
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
        System.out.println(encryptTwoKeys("First Legion", 17, 23));
    }
    
    
     public static void main (String[] args) {
        CeasarCipher cc = new CeasarCipher();
        cc.tests();
        cc.testCeasar();
        cc.testEncryptTwoKeys();
    }
}
