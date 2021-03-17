
import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int n;
    protected int seed;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int s) { seed = s; };
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        // we initialize start as 0
        int start = 0;
        // while we find the key in the string myText
        while(myText.indexOf(key, start) != -1){
            // indexOfKey is equal to the position of the key, starting from start
            int indexOfKey = myText.indexOf(key, start);
            // followingChar is the position of the character following the key in myText
            int followingChar = indexOfKey + key.length();
            // if followingChar is out of myText range, we break the loop
            if (followingChar >= myText.length()) break;
            // we assign the char at position followingChar to the variable ch
            char ch = myText.charAt(followingChar);
            // we convert ch to String
            String s = Character.toString(ch);
            // we add s to follows ArrayList
            follows.add(s);
            // we update start position to be equal to followingChar
            start = followingChar;
        }
        return follows;
    }


    public String toString(){
        return "MarkovModel of order " + n;
    }
}
