import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFile {
    // We create a private variable to store a HashMap that maps a word to an ArrayList of filenames
    private HashMap<String,ArrayList> map;

    public WordsInFile(){
    // We write a constructor to initialize the HashMap variable
        map = new HashMap<String, ArrayList>();
    }

    private void addWordsFromFile(File f){
        /*
        This method adds all the words from f into the map.
        If a word is not in the map, then we create a new ArrayList of type String with this word,
        and have the word map to this ArrayList.
        If a word is already in the map, then we add the current filename to its ArrayList,
        unless the filename is already in the ArrayList
         */
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if ( !map.containsKey(word) ){
                ArrayList<String> fileList = new ArrayList<>();
                fileList.add(f.getName());
                map.put(word, fileList);
            }
            else {
                ArrayList<String> fileList = new ArrayList<>();
                fileList = map.get(word);
                if ( !fileList.contains(f.getName()) ){
                    fileList.add(f.getName());
                }
            }

        }
    }


    public void buildWordFileMap(){
        /*
        This method first clears the map, and then uses a DirectoryResource to select a group of files.
         For each file, it puts all of its words into the map by calling the method addWordsFromFile
         */
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    public int maxNumber(){
        /*
        This method returns the maximum number of files any word appears in,
         considering all words from a group of files
         */
        int max = 0;
        for (String word : map.keySet()){
            int count = map.get(word).size();
            if ( count > max){
                max = count;
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number){
        // This method returns an ArrayList of words that appear in exactly number files
        ArrayList<String> words = new ArrayList<>();
        for (String word : map.keySet()){
            if ( map.get(word).size() == number ){
                words.add(word);
            }
        }
        return words;
    }

    public void printFilesIn(String word){
        // This method prints the names of the files this word appears in, one filename per line
        for (String s : map.keySet()){
            if ( s.equals(word) ){
                ArrayList<String> toPrint = map.get(s);
                for (int i = 0; i < toPrint.size(); i++) {
                    System.out.println(toPrint.get(i));
                }
            }
        }
    }

    public void tester(){
        // We test the methods for WordsInFile Class
        String word = "cats";
        buildWordFileMap();
        System.out.println("Maximum number of files a word appears in: " + maxNumber());
        System.out.println();
        System.out.println("Words that appear the most in different files: " + wordsInNumFiles(maxNumber()));
        System.out.println();
        System.out.println("Files that contain the word " + word + ": ");
        printFilesIn(word);
        System.out.println();
        for (String s : map.keySet()){
            System.out.println(s + " " + map.get(s));
        }

    }

    public static void main(String[] args) {
        WordsInFile wif = new WordsInFile();
        wif.tester();
    }

}
