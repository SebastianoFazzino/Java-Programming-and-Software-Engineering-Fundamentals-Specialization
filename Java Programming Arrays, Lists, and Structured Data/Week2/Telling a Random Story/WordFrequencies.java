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

    public void tester() {
        // We print out all the unique words and their occurrences
        findUnique();
        System.out.println("# Unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++ ) {
            System.out.println(myFrequencies.get(i) + "\t" + myWords.get(i));
        }
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}

