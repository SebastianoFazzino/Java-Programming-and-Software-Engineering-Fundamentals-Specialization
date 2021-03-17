import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{
    private int markovGrade;

    public MarkovModel(int N) {
        myRandom = new Random();
        markovGrade = N;
        n = N;
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() -markovGrade);
        String key = myText.substring(index, index +markovGrade);
        sb.append(key);
        for (int i = 0; i < numChars -markovGrade; i++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
}
