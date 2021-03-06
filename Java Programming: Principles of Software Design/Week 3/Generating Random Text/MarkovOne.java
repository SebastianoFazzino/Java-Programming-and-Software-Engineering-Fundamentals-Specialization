import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key){
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

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() -1);
        String key = myText.substring(index, index +1);
        sb.append(key);
        for (int i = 0; i < numChars -1; i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
}
