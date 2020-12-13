
import edu.duke.*;


public class TestCeasarCipher {
    
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
    
    
    public String breakCeasarCipher(String input) {
        // Using countLetters and indexOfMax helper methods, this mehod find the key the decrypt the encrypted message
        int dkey = getKey(input);
        OOCeasarCipher breakCeasar = new OOCeasarCipher(dkey);
        String decrypted = breakCeasar.decrypt(input);
        return decrypted;
    }
    
    
    private int indexOfMax(int[] counts){
        // This helper method given an integer array, returns the index with the highest value
        int maxIndex = 0;
        for(int i=0; i < counts.length; i++){
            if (counts[i] > counts[maxIndex]){
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
    
    
    public void simpleTest() {
        // We test OOCeasarCipher methods creating new objects
        FileResource fr = new FileResource("test.txt");
        String message = fr.asString();
        OOCeasarCipher test = new OOCeasarCipher(18);     
        String encrypted = test.encrypt(message);      
        System.out.println("*** Encrypted message ***");
        System.out.println(encrypted);
        System.out.println("*** Decrypted message ***");
        System.out.println(test.decrypt(encrypted));
        System.out.println();
        
        FileResource fr2 = new FileResource("test2.txt");
        String toBreak = fr2.asString();
        TestCeasarCipher test2 = new TestCeasarCipher(); 
        System.out.println("*** Testing breakCeasarCipher method *** ");
        System.out.println(test2.breakCeasarCipher(toBreak));
        
    }
}
