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
        FileResource fr = new FileResource();
        String message = fr.asString();
        String[] dictionaries = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String,HashSet<String>> languages = new HashMap<>();
        for (String language : dictionaries) {
            FileResource fr2 = new FileResource("Dictionaries/" + language);
            HashSet<String> words = readDictionary(fr2);
            languages.put(language, words);
        }
        String decrypted = breakForAllLangs(message, languages);
        System.out.println("Decrypted Message:");
        System.out.println(decrypted);


        //FileResource dictionary = new FileResource("Dictionaries/English");
        //HashSet<String> dictionaryWords = readDictionary(dictionary);
        //String decrypted = breakForLanguage(message, dictionaryWords);
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


    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> mostCommon = new HashMap<>();
        int count = 0;
        char output = ' ';
        for (String word : dictionary){
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                if (!mostCommon.containsKey(letter)) {
                    mostCommon.put(letter, 1);
                }
                else {
                    mostCommon.put(letter, mostCommon.get(letter) + 1);
                }
            }
        }
        for (char letter : mostCommon.keySet()){
            if (mostCommon.get(letter) > count) {
                count = mostCommon.get(letter);
                output = letter;
            }
        }
        return output;
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
        char letter = mostCommonCharIn(dictionary);
        for (int i = 1; i <= keyMax ; i++) {
            int[] keys = tryKeyLength(encrypted, i, letter);
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


    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages) {
        String output = "";
        int count = 0;
        for (String dictionary : languages.keySet()) {
            String decrypt = breakForLanguage(encrypted, languages.get(dictionary));
            int wordsFound = countWords(decrypt, languages.get(dictionary));
            if (wordsFound > count){
                count = wordsFound;
                output = decrypt;
                System.out.println("Language detected: " + dictionary);
            }
        }
        return output;
    }

}
