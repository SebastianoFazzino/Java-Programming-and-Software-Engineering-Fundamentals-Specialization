import java.util.*;

public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index +1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println("Following words given the keys " + key1 + " " + key2 + ": " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1 || start >= myText.length -1){
                break;
            }
            int nextIndex = start +2;
            if (nextIndex >= myText.length) {
                break;
            }
            String nextWord = myText[nextIndex];
            follows.add(nextWord);
            pos = start +1;
        }
        return follows;
    }

    private int indexOf(String[] words, String key1, String key2, int start){
        for (int i = start; i < words.length -1; i++) {
            if (words[i].equals(key1) && words[i +1].equals(key2)) {
                //System.out.println("key1: " + key1 + " key2: " + key2);
                return i;
            }
        }
        return -1;
    }
}
