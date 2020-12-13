
import edu.duke.*;


public class TestCipherTwo {
    
    private int[] countLetters(String phrase) {
        //This method counts the number of occurences of every letter of the alphabet in a given sentence "message" 
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < phrase.length(); i++){
           char ch = Character.toLowerCase(phrase.charAt(i));
           int index = alphabet.indexOf(ch);
           if(index != -1){
               counts[index] += 1;
           }
        }
        return counts;
    }
    
    
    private String halfOfString(String phrase, int start) {
        // This helper method, given a string a start index, return a new String that is every other character from message starting with the start position
        StringBuilder halfPhrase = new StringBuilder();
        for (int i = start; i < phrase.length(); i += 2 ) {
            halfPhrase.append(phrase.charAt(i));
        }
        return halfPhrase.toString();
    }
    
     
    private int indexOfMax(int[] vals){
        // This helper method given an integer array, returns the index with the highest value
        int maxIndex = 0;
        for(int i=0; i < vals.length; i++){
            if (vals[i] > vals[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    
    private int getKey(String s) {
        // This helper method takes a string as parameter and, assuming that 'e' is the most
        // common letter, it finds the key to decrypt the message
        int dkey;
        int[] counts = countLetters(s);
        int maxIndex = indexOfMax(counts);
        if (maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
            return dkey;
        }    
        
        dkey = (4 - maxIndex) % 26;
        if(dkey < 0) {
           dkey *= -1;
        }
        return dkey;
    }
    
    
    public String breakCaesarCipher(String input) {
        /*
         * This method takes a String as parameter, it splits it in two different strings using
         * halfOfString method, then it finds decrypting keys for both strings using getKey method.
         * At the it creates a new OOCeasarCipherTwo object with the two decrypting keys as argument
         * and the input string is decrypted using OOCeasarCipherTwo decrypt method
         */
        String halfOne = halfOfString(input, 0);
        String halfTwo = halfOfString(input, 1);
        int keyOne = getKey(halfOne); 
        int keyTwo = getKey(halfTwo);
        OOCeasarCipherTwo cct = new OOCeasarCipherTwo(keyOne, keyTwo);
        String decrypted = cct.decrypt(input);
        return decrypted;
    }
    
    
    public void simpleTest() {
        // We test OOCeasarCiphertwo methods creating new objects
        FileResource fr = new FileResource("test.txt");
        String message = fr.asString();
        OOCeasarCipherTwo cct = new OOCeasarCipherTwo(22, 8);
        String encrypted = cct.encrypt(message);      
        System.out.println("*** Encrypted message ***");
        System.out.println(encrypted);
        System.out.println("*** Decrypted message ***");
        System.out.println(cct.decrypt(encrypted));
        System.out.println();
       
        TestCipherTwo test = new TestCipherTwo();
        System.out.println("*** Encrypted message *** ");
        System.out.println(cct.encrypt(message));  
        System.out.println("*** Decrypted message ***");
        System.out.println(test.breakCaesarCipher(message));
    }
}
