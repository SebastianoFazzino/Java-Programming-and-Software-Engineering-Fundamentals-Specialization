
import edu.duke.*;

public class WordLength {
    public void countWordLength(FileResource resource, int[] counts) {
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
        int max = 0;
        for ( int i = 0; i < values.length; i++ ) {
            if (values[i] > max) {
                max = i;
            }
        }
        return max;
    }
    
    
    public void test() {
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
        WordLength test = new WordLength();
        test.test();
    }
}
