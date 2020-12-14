import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFrequencies;

    public WordFrequencies(){
        /*
        The constructor assigns to the object two new ArrayLists
        that are used to count unique words
         */
        myWords = new ArrayList<String>();
        myFrequencies = new ArrayList<Integer>();
    }

    public void findUnique() {
        // We create a new FileResource to select a file to analyze
        FileResource resource = new FileResource();
        myWords.clear();
        myFrequencies.clear();
        // .words() is used to split all the words in a given file
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            // if a word is not present in the ArrayList myWords, we add it with value 1
            if (index == -1) {
                myWords.add(s);
                myFrequencies.add(1);
            }
            // if a word is already in myWords, we increase its value on myFrequencies
            else {
                int value = myFrequencies.get(index);
                myFrequencies.set(index, value + 1);
            }
        }
    }

    public int indexOfMax() {
        // This method finds the word that occurs the most and returns its index
        int max = 0;
        for (int i = 0; i < myFrequencies.size(); i++) {
            if ( myFrequencies.get(i) > max ) {
                max = myFrequencies.indexOf(i);;
            }
        }
        return max;
    }


    public void tester() {
        // We print out all the unique words and their occurrences
        findUnique();
        System.out.println("# Unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++ ) {
            System.out.println(myFrequencies.get(i) + "\t" + myWords.get(i));
        }
        // We print out the word that occurs the most and its number of occurrences
        System.out.println("The word that occurs the most is: " + myWords.get(indexOfMax()) + " and its count is " + myFrequencies.get(indexOfMax()));
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
