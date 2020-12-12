
import edu.duke.*;

public class WordLength {
    public void countWordLength(FileResource resource, int[] counts) {
        /*
         * This method takes a file resource and an int array as arguments, it compute the length of
         * all the words in resource, storing these values in the array counts.
         */
        for ( String word : resource.words() ) {
            int wordLength = word.length();
            if (!Character.isLetter(word.charAt(0))) {
               wordLength -= 1;
            }
            if (!Character.isLetter(word.charAt(wordLength - 1))) {
               wordLength -= 1;
            }
            if (wordLength < 0) {
                continue;
            }
            counts[wordLength] += 1;
        }
    }
    
    
    public int indexOfMax(int[] values) {
        /*
         * This method finds the most common word length, given an array of integers 
         * representing word length in a given file
         */
        int max = 0;
        for ( int i = 0; i < values.length; i++ ) {
            if (values[i] > max) {
                max = i;
            }
        }
        return max;
    }
    
    
    public void test() {
        /*
         * We create a new FileResource and an int array to pass as argument of countWordLength;
         * we print out all the values in counts and then the most common word length
         */
        FileResource test = new FileResource();
        int[] counts = new int[31];
        countWordLength(test, counts);
        for (int i = 0; i < 31; i ++){
            if (counts[i] != 0){
                System.out.println(counts[i] + " Words of length " + i + " found");
            }
        }
        System.out.println();
        System.out.println("The most common word length is " + indexOfMax(counts));
    }
    
    
    public static void main(String[] args) {
        WordLength wl = new WordLength();
        wl.test();
    }
}
