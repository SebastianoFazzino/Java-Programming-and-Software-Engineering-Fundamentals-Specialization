
import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        // Given an argument char, this function returns true if the char is a vowel and false if the char is not a vowel
        String vowels = "AEIOU";
        int test = vowels.indexOf(Character.toUpperCase(ch));
        if ( test != -1 ) {
            return true;
        }
        return false;
    }
    
    
    public String replaceVowels(String phrase, char ch) {
        // This function takes a string and a char as argument and replaces all the vowels in the string with the character char
        StringBuilder outputPhrase = new StringBuilder(phrase);
        for (int i = 0; i < outputPhrase.length(); i++) {
             char currChar = outputPhrase.charAt(i);
             if ( isVowel(currChar) ) {
                 outputPhrase.setCharAt(i, ch);
             }
        }
        return outputPhrase.toString();
    }
       
    
    public String emphasize(String phrase, char ch) {
        /*
         * This method takes a string and a char as parameters, if the char is found in the string at an odd index, that char is replaced
         * by a '+', if it has an even index it's replaced by '*'
         */
        StringBuilder outputPhrase = new StringBuilder(phrase);
        int startIndex = 0;
        for (int i = 0; i < outputPhrase.length(); i++) {
             char currChar = outputPhrase.charAt(i);
             if (Character.toUpperCase(currChar) == Character.toUpperCase(ch) ) {
                 if ( i % 2 == 0 ) {
                     outputPhrase.setCharAt(i, '*');
                 }
                 if ( i % 2 == 1 ) {
                     outputPhrase.setCharAt(i, '+');
                 }
             }
        }
        return outputPhrase.toString();
    }
    
    
    public void tests() {
        //We test WordPlay methods
        
        System.out.println(isVowel('a'));
        System.out.println(isVowel('v'));
        System.out.println(isVowel('I'));
        System.out.println(isVowel('b'));
        
        String[] phrases = {"Banana", "Hello world", "Java, Python, JavaScript", "House", "Apple", "Computer Programming"};
        
        for (int i = 0; i < phrases.length; i++) {
          System.out.println("Orignal phrase: " + phrases[i] + ";  Modified phrase: " + replaceVowels(phrases[i], '*'));
          
          System.out.println("Orignal phrase: " + phrases[i] + ";  Modified phrase: " + emphasize(phrases[i], 'o'));
        }
        
    }
    
    
     public static void main (String[] args) {
        WordPlay wp = new WordPlay();
        wp.tests();
    }
}

