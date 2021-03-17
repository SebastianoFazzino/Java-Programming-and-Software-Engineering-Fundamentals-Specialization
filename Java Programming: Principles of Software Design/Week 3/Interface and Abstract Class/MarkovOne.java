import java.util.*;

public class MarkovOne extends AbstractMarkovModel{

    public MarkovOne() {
        myRandom = new Random();
        n = 1;
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() -1);
        String key = myText.substring(index, index +1);
        sb.append(key);
        for (int i = 0; i < numChars -1; i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
}
