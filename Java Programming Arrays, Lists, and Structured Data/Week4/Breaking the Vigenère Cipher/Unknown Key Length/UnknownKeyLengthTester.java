import edu.duke.FileResource;

import java.util.HashSet;

public class UnknownKeyLengthTester {

    public void VigenereBreakerTest(){
        FileResource fr = new FileResource("Dictionaries/English");
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> words = vb.readDictionary(fr);
        for (String word : words) {
            System.out.println(word);
        }
        System.out.println(words.size());

        int i = vb.countWords("today is a great day there is a big shiny sun and no clouds", words);
        System.out.println(i);
        vb.breakVigenere();
    }

    public static void main(String[] args) {
        UnknownKeyLengthTester ult = new UnknownKeyLengthTester();
        ult.VigenereBreakerTest();
    }
}
