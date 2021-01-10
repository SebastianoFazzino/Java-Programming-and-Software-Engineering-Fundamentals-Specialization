import java.io.File;
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
        FileResource fr = new FileResource("athens_keyflute.txt");
        String message = fr.asString();
        FileResource dictionary = new FileResource("Dictionaries/English");
        HashSet<String> dictionaryWords = readDictionary(dictionary);
        String decrypted = breakForLanguage(message, dictionaryWords);
        System.out.println("Decrypted Message:");
        System.out.println(decrypted);

        // old version method

        //int[] keys = tryKeyLength(message, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(keys);
        //String decrypted = vc.decrypt(message);
        //System.out.println("Decrypted Message:");
        //System.out.println(decrypted);
     }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for (String line : fr.lines()){
            words.add(line.toLowerCase());
        }
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int count = 0;
        for (String word : words){
            if (dictionary.contains(word.toLowerCase())) {
                count ++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int keyMax;
        int wordCount = 0;
        String decrypted = "";
        if (encrypted.length() < 100){
            keyMax = encrypted.length();
        }
        else {
            keyMax = 100;
        }
        for (int i = 1; i <= keyMax ; i++) {
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String toDecrypt = vc.decrypt(encrypted);
            int wordsFound = countWords(toDecrypt, dictionary);
            if ( wordsFound > wordCount ) {
                wordCount = wordsFound;
                decrypted = toDecrypt;
                System.out.println("Words found: " + wordCount);
                System.out.println("With key: "+ i);
            }
        }
        return decrypted;
    }

}
